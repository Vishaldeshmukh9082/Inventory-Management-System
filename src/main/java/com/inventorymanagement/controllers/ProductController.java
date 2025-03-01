package com.inventorymanagement.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inventorymanagement.entities.Product;
import com.inventorymanagement.forms.ProductForm;
import com.inventorymanagement.services.ProductService;

import java.io.IOException;
import java.util.*;

@Controller
public class ProductController {
    @Autowired
    private ProductService prodService;


    @PostMapping("/add-product")
    public String createProduct(@ModelAttribute ProductForm productForm,@RequestParam("file") MultipartFile imageFile) throws IOException {

         Product product=Product.builder()
         .category(productForm.getCategoryId())
         .productName(productForm.getProductName())
         .productNumber(productForm.getProductNumber())
         .expiryDate(productForm.getExpiryDate())
         .recievedDate(productForm.getReceivedDate())
         .productPrice(productForm.getProductPrice())
         .productQuantity(productForm.getProductQuantity())
         .stockStatus(productForm.getStockStatus())

         .build();
        // .categoryName(categoryForm.getCategoryName())
        // .categoryDescription(categoryForm.getCategoryDescription())
        // .build();

        
        prodService.saveProductWithImage(product, imageFile);
        // prodService.saveProduct(productForm.getCategoryId(),product);
        //HttpHeaders headers = new HttpHeaders(null);
        //headers.add("Responded", "CategoryController");
        //return ResponseEntity.accepted().headers(headers).body(savedCat);
        return "redirect:admin/home";
    }



    @PostMapping("/products/{supplierId}/{suppliedProductId}/{categoryId}/{brandId}")
    public ResponseEntity<Product> saveProduct(@PathVariable Integer categoryId, @RequestBody Product ProData) {
        Product product = prodService.saveProduct(categoryId, ProData);
        return ResponseEntity.ok().body(product);
    }



    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product proData) {

        Product product = prodService.editProduct(productId, proData);

        return ResponseEntity.ok().body(product);
    }
    


    @GetMapping("/products")
    public List<Product> getAllProducts() {

        List<Product> listPro = prodService.listAllProduct();
        return listPro;
    }

    @GetMapping("/products/un-stocked")
    public ResponseEntity<List<Product>> getAllUnStockedProducts() {

        List<Product> listPro = prodService.listAllProductByProductStockStatus("un-stocked");
        return ResponseEntity.ok().body(listPro);
    }

    @GetMapping("/products/stocked")
    public ResponseEntity<List<Product>> getAllStockedProducts() {

        List<Product> listPro = prodService.listAllProductByProductStockStatus("stocked");
        return ResponseEntity.ok().body(listPro);
    }

    // @GetMapping("/products/{orderId}")
    // public ResponseEntity<List<Product>> getAllProductsByCategory(@PathVariable Integer orderId) {

    //     List<Product> listPro = prodService.listAllProductById(orderId);
    //     return ResponseEntity.ok().body(listPro);
    // }

    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity<List<Product>> getAllProductsByOrderId(@PathVariable Integer categoryId) {

        List<Product> listPro = prodService.listAllProductByCategoryId(categoryId);
        return ResponseEntity.ok().body(listPro);
    }
    // @GetMapping("/products/brand/{brandId}")
    // public ResponseEntity<List<Product>> getAllProductsByBrandId(@PathVariable
    // Integer brandId){

    // List<Product> listPro = prodService.listAllProductByBrandId(brandId);
    // return ResponseEntity.ok().body(listPro);
    // }

    @GetMapping("/products/product-number/{productNumber}")
    public ResponseEntity<Product> getAllProductsByProductNumber(@PathVariable String productNumber) {

        Product pro = prodService.getProductByProductNumber(productNumber);
        return ResponseEntity.ok().body(pro);
    }

    @RequestMapping("/products/delete/{productId}")
    public String getCategoryByName(@PathVariable  Integer productId){
          
        prodService.deleteProduct(productId);
        
       //  Map<String,Boolean> response = new HashMap<>();
       //   response.put("deleted", Boolean.TRUE);
       //    if(response.getValue()){
   
       //    }
          return "admin/home";
    
   }
    // public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer productId) {
    //     prodService.deleteProduct(productId);
    //     Map<String, Boolean> response = new HashMap<>();
    //     response.put("deleted", Boolean.TRUE);
    //     return ResponseEntity.ok(response);
    // }

    // @PutMapping("/products/add-product-inventory/{productId}/{inventoryCode}")
    // public ResponseEntity<Product> addProductToInventory(@PathVariable Integer
    // productId,
    // @PathVariable String inventoryCode){
    // System.out.println(productId + "this " + inventoryCode);
    // Product pro = prodService.addProductToInventory(productId, inventoryCode);
    // return ResponseEntity.ok().body(pro);
    // }
}
