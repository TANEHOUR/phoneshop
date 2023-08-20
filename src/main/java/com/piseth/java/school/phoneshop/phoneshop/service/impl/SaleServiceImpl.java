package com.piseth.java.school.phoneshop.phoneshop.service.impl;

import com.piseth.java.school.phoneshop.phoneshop.dto.ProductSoldDTO;
import com.piseth.java.school.phoneshop.phoneshop.dto.SaleDTO;
import com.piseth.java.school.phoneshop.phoneshop.entities.Product;
import com.piseth.java.school.phoneshop.phoneshop.entities.Sale;
import com.piseth.java.school.phoneshop.phoneshop.entities.SaleDetail;
import com.piseth.java.school.phoneshop.phoneshop.exception.ApiException;
import com.piseth.java.school.phoneshop.phoneshop.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop.phoneshop.repository.ProductRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshop.phoneshop.repository.SaleRepository;
import com.piseth.java.school.phoneshop.phoneshop.service.ProductService;
import com.piseth.java.school.phoneshop.phoneshop.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;


    @Override
    public void sell(SaleDTO saleDTO) {

        // validate
        //validate(saleDTO);

        //save

        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        productIds.forEach(productService::getById);
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        // validate stock
        saleDTO.getProducts()
                .forEach(ps -> {
                    Product product = productMap.get(ps.getProductId());
                    if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough product in stock".formatted(product.getName()));
                    }
                });

        // Sale
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        //SaleDetail
        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(product.getSalePrice());
            saleDetail.setProduct(product);
            saleDetail.setSale(sale);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetailRepository.save(saleDetail);

            //cut stock

            Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
            product.setAvailableUnit(availableUnit);
            productRepository.save(product);
        });
    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale", saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);
        List<Long> productId = saleDetails.stream()
                .map(saleDetail -> saleDetail.getProduct().getId())
                .toList();

        List<Product> products = productRepository.findAllById(productId);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
        saleDetails.forEach(saleDetail -> {
            Product product = productMap.get(saleDetail.getProduct().getId());
            product.setAvailableUnit(product.getAvailableUnit() + saleDetail.getUnit());
            productRepository.save(product);
        });
    }

    private void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSoldDate(saleDTO.getSaleDate());
        saleRepository.save(sale);

        //sale Detail
        saleDTO.getProducts().forEach(ps -> {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(null);
        });
    }

    private void validate(SaleDTO saleDTO) {

        // validate Product
        saleDTO.getProducts().forEach(ps -> {
            Product product = productService.getById(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough product in stock".formatted(product.getName()));
            }
        });
    }

/*    private void validate(SaleDTO saleDTO) { // explain clearly

        // validate Product
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();
        productIds.forEach(productService::getById);
        List<Product> products = productRepository.findAllById(productIds);
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));
//        saleDTO.getProducts().stream()
//                .map(ProductSoldDTO::getProductId)
//                .forEach(productService::getById);

//                .forEach(productId ->{
//                    productService.getById(productId);
//                });

        // validate stock
        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough product in stock".formatted(product.getName()));

            }
        });
    }*/
}

