package com.inventorymanagement.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{

     //@Query("SELECT p FROM Product p  JOIN  p.order o  WHERE o.orderId =:orderId")

     //List<Product>  getAllProductByOrderId(@Param (value ="orderId") Integer orderId);


    //  @Query("SELECT p FROM Product p  JOIN  p.category c  WHERE c.categoryId =:categoryId")
    List<Product> getAllProductByCategory(@Param (value = "categoryId") Integer categoryId);

         @Query("SELECT p FROM Product p   WHERE p.productId =:productId")
        Product getProductById(@Param (value = "productId") String productId);
    
    // @Query("SELECT p FROM Product p  JOIN  p.brand b  WHERE b.brandId=:brandId")
    // List<Product> getAllProductByBrand(@Param (value = "brandId") Integer brandId);
    
    // @Query("SELECT p FROM Product p  JOIN  p.inventory inv  WHERE inv.inventoryId=:inventoryId")
    // List<Product> getAllProductByInventory(@Param (value = "inventoryId") Integer inventoryId);
    @Query("SELECT p FROM Product p WHERE p.productName LIKE :productName%")
    List<Product> getProductbyProductName(@Param("productName") String productName);
    

   
    @Query("SELECT p FROM Product p   WHERE p.productNumber=:productNumber ")
    Product getProductbyProductNumner(@Param (value = "productNumber")String productNumber);
   
    @Query("SELECT p FROM Product p    WHERE p.stockStatus=:stockStatus")
    List<Product> getAllProductByStockStatus(@Param (value = "stockStatus") String stockStatus);

}
