package com.inventorymanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

    @Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Map /images/** to your uploaded images directory
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/D:/private/Projects/Inventry Management/InventoryManagementSystem/src/main/resources/static/uploaded-images/");
    }
}



