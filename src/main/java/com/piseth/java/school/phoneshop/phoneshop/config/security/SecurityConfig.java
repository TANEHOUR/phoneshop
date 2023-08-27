package com.piseth.java.school.phoneshop.phoneshop.config.security;

import com.piseth.java.school.phoneshop.phoneshop.jwt.JwtLoginFilter;
import com.piseth.java.school.phoneshop.phoneshop.jwt.TokenVerifyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/", "index.html", "css/**", "js/**").permitAll()
/*//                .antMatchers("/brands").hasRole("SALE")
//                .antMatchers("/models").hasRole(RoleEnum.SALE.name())
//                .antMatchers(HttpMethod.POST, "/brands").hasAnyAuthority("brand:write")
//                .antMatchers(HttpMethod.POST, "/brands").hasAnyAuthority(BRAND_WRITE.getDescription())
//                .antMatchers(HttpMethod.GET, "/brands").hasAnyAuthority(BRAND_READ.getDescription())*/
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    /*@Bean
    @Override
    protected UserDetailsService userDetailsService() {
//        User user1 = new User("dara",passwordEncoder.encode("dara123"), Collections.emptyList());

        UserDetails user1 = User.builder()
                .username("dara")
                .password(passwordEncoder.encode("dara123"))
//                .roles("SALE") //ROLE_SALE
                .authorities(RoleEnum.SALE.grantedAuthority())
                .build();

        UserDetails user2 = User.builder()
                .username("thida")
                .password(passwordEncoder.encode("thida123"))
//                .roles("ADMIN") //ROLE_ADMIN
                .authorities(RoleEnum.ADMIN.grantedAuthority())
                .build();

        UserDetailsService userDetails = new InMemoryUserDetailsManager(user1, user2);
        return userDetails;
    }*/
}
