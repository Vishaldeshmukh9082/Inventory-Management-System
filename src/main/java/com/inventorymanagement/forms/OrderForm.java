package com.inventorymanagement.forms;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderForm {

    private Integer orderId;
    private String customerId;
    private String  orderProductName;
    private Integer orderProductQuantity;
    private Double orderProductPrice;

    private Date orderDate;
    private Double totalAmount;
    private String orderStatus;

}
