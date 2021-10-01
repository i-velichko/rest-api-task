package com.epam.esm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ivan Velichko
 * @date 01.10.2021 16:05
 */

@Configuration
@ComponentScan(basePackages = "com.epam.esm")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {

}
