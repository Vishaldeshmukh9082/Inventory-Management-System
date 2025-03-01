package com.inventorymanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_number")
    private String productNumber;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "recieved_date")
    private Date recievedDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "stock_status")
    private String stockStatus;

    @Column(name = "category_id")
    private int category;

    @Column(name = "product_image")
    private String productImagePath;  // Store file path

}
