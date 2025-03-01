package com.inventorymanagement.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductForm {

    private Integer productId;
   
    private String productName;
    
    private String productNumber;
    
    private Double productPrice;
    
    private int productQuantity;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receivedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    
    private String stockStatus;
    
    private Integer categoryId;
}
