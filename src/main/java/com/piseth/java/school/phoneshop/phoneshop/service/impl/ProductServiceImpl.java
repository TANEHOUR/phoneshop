package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.ProductImportHistory;
import com.piseth.java.school.phoneshop.phoneshop.exception.ApiException;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.mapper.ProductMapper;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ProductService;
import liquibase.pro.packaged.I;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductImportHistoryRepository productImportHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {

        String name = "%s %s"
                .formatted(product.getModel().getName(), product.getColor().getName());
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public Product getByModelIdandColorId(Long modelId, Long colorId) {
        String text = "Product with model Id %s and color id %d was not found";
        return productRepository.findByModelIdAndColorId(modelId, colorId)
                .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, text.formatted(modelId, colorId)));
    }

    @Override
    public void importProduct(ProductImportDTO productImportDTO) {
        // update available product unit
        //productRepository.getById(productImportDTO.getProductId());
        Product product = getById(productImportDTO.getProductId());
        //Integer availableUnit = product.getAvailableUnit() + productImportDTO.getImportUnit();
        Integer availableUnit = 0;
        if (product.getAvailableUnit() != null){
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + productImportDTO.getImportUnit());
        productRepository.save(product);

        //save product import history
        ProductImportHistory productImportHistory = productMapper.toProductImportHistory(productImportDTO, product);
        productImportHistoryRepository.save(productImportHistory);

    }

    @Override
    public void setSalePrice(Long productId, BigDecimal price) {
        Product product = getById(productId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void validateStock(Long productId, Integer numberOfUnit) {

    }

    @Override
    public Map<Integer, String> uploadProduct(MultipartFile file) {
        Map<Integer, String> map = new HashMap<>();
        try {
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheet("products");
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // @TODO Improve
            while (rowIterator.hasNext()) {
                Integer rowNo = 0;
                try {
                    Row row = rowIterator.next();
                    int cellIndex = 0;

                    Cell cellNo = row.getCell(cellIndex);
                    rowNo = (int) cellNo.getNumericCellValue();

                    Cell cellModelId = row.getCell(cellIndex++);
                    Long modelId = (long) cellModelId.getNumericCellValue();

                    Cell cellColorId = row.getCell(cellIndex++);
                    Long colorId = (long) cellColorId.getNumericCellValue();

                    Cell cellImportPrice = row.getCell(cellIndex++);
                    double importPrice = cellImportPrice.getNumericCellValue();
                    if (importPrice < 0){
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0");
                    }

                    Cell cellImportUnit = row.getCell(cellIndex++);
                    Integer importUnit = (int) cellImportUnit.getNumericCellValue();

                    Cell cellImportDate = row.getCell(cellIndex++);
                    LocalDateTime importDate = cellImportDate.getLocalDateTimeCellValue();

                    Product product = getByModelIdandColorId(modelId, colorId);

                    Integer availableUnit = 0;
                    if (product.getAvailableUnit() != null) {
                        availableUnit = product.getAvailableUnit();
                    }
                    product.setAvailableUnit(availableUnit + importUnit);
                    productRepository.save(product);

                    ProductImportHistory importHistory = new ProductImportHistory();
                    importHistory.setDateImport(importDate);
                    importHistory.setImportUnit(importUnit);
                    importHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
                    importHistory.setProduct(product);
                    productImportHistoryRepository.save(importHistory);
                } catch (Exception e) {
                    map.put(rowNo, e.getMessage());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
