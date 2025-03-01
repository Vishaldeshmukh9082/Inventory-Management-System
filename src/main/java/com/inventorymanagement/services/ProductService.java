package com.inventorymanagement.services;

import java.io.IOException;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import com.inventorymanagement.entities.Product;

public interface ProductService {


     public Product   saveProduct(Integer categoryId,Product  product);
     public void deleteProduct  ( Integer orderProductId);
     public Product   editProduct  (Integer productId,Product   product);
     public List<Product > listAllProduct  ();
     //public List<Product > listAllProductById  (Integer orderId);
     public List<Product > listAllProductByCategoryId (Integer categoryId);
     //public List<Product > listAllProductByBrandId  (Integer brandId);
     public  List<Product> listAllProductByProductStockStatus(String stockStatus);
    public Product getProductByProductNumber(String productNumber);
    public List<Product> getProductByProductName(String productName);
    public Product getProductByProductId(String productId);
     public void saveProductWithImage(Product product, MultipartFile imageFile) throws IOException;
    //public Product addProductToInventory(Integer productId, String inventoryCode);
    //public  List<Product> listProductByInventory(Integer inventoryId);

}
