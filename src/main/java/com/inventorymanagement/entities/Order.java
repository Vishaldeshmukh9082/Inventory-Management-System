package com.inventorymanagement.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_table")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;
    // @Column(name = "order_name")
    // private String orderName;
    // @Column(name = "order_type")
    // private String orderType;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "order_product_name")
    private String  orderProductName;
    @Column(name = "order_product_quantity")
    private Integer orderProductQuantity;
     @Column(name= "order_product_price")
     private Double orderProductPrice;

     @Column(name="total_amount")
     private Double totalAmount;

     @Temporal(TemporalType.DATE)
     @Column(name="order_date")
     private Date orderDate;

   @Column(name = "order_status")
   private String orderStatus;

    

}
