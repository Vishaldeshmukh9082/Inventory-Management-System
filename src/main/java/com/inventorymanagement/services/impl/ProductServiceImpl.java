package com.inventorymanagement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inventorymanagement.entities.Product;
import com.inventorymanagement.repositories.CategoryRepo;
import com.inventorymanagement.repositories.ProductRepo;
import com.inventorymanagement.services.ProductService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.File;
import java.nio.file.Path;

import javax.imageio.ImageIO;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;
    // @Autowired
    // private OrderRepository orderRepo;
    @Autowired
    private CategoryRepo catRepo;
    @Value("${upload.dir}")
    private String uploadDir; 

    @Override
    public Product saveProduct(Integer categoryId, Product product) {

        Product prod = null;
        
        // Supplier sup =null;
        // Category cat =null;

        try {

            // cat = catRepo.getById(categoryId);
            // Date date = new Date();
            // product.setRecievedDate(date);
            // //product.setCategory(cat);
            // product.setStockStatus("un-stocked");
            prod = productRepo.save(product);

        } catch (Exception e) {
            prod = null;
        }
        return prod;
    }

    @Override
    public void deleteProduct(Integer orderProductId) {
        try {
            productRepo.deleteById(orderProductId);
        } catch (Exception e) {
        }

    }

    // @Override
    // public Product editProduct(Integer productId, Product product) {
    //     Product prod = null;
    //     try {
    //         Date date = new Date();
    //         prod = productRepo.getById(productId);
    //         prod.setProductNumber(product.getProductNumber());
    //         prod.setProductName(product.getProductName());
    //         prod.setProductQuantity(product.getProductQuantity());
    //         prod.setProductPrice(product.getProductPrice());
    //         prod.setRecievedDate(date);
    //         prod.setExpiryDate(product.getExpiryDate());
    //         prod = productRepo.save(prod);
    //     } catch (Exception e) {
    //         prod = null;
    //     }
    //     return prod;
    // }

    @Override
    public List<Product> listAllProduct() {
        List<Product> listPro = productRepo.findAll();

        // try {
        // listPro = productRepo.findAll();
        // } catch (Exception e) {
        // listPro =null;
        // }
        return listPro;
    }

    // @Override
    // public List<Product> listAllProductById(Integer orderId) {
    // List<Product> listPro =null;
    // try {
    // listPro = productRepo.getAllProductByOrderId(orderId);
    // } catch (Exception e) {
    // listPro =null;
    // }
    // return listPro;
    // }

    @Override
    public List<Product> listAllProductByCategoryId(Integer categoryId) {
        List<Product> listPro = null;
        try {
            listPro = productRepo.getAllProductByCategory(categoryId);
        } catch (Exception e) {
            listPro = null;
        }
        return listPro;
    }

    @Override
    public Product getProductByProductNumber(String productNumber) {
        Product pro = null;
        try {
            pro = productRepo.getProductbyProductNumner(productNumber);
        } catch (Exception e) {
            pro = null;
        }
        return pro;
    }

    @Override
    public List<Product> listAllProductByProductStockStatus(String stockStatus) {
        List<Product> listPro = null;
        try {
            listPro = productRepo.getAllProductByStockStatus(stockStatus);
        } catch (Exception e) {
            listPro = null;
        }
        return listPro;

    }

    @Override
    public Product getProductByProductId(String productId) {
        // TODO Auto-generated method stub
        Product listPro = null;
        try {
            listPro = productRepo.getProductById(productId);
        } catch (Exception e) {
            listPro = null;
        }
        return listPro;
    }

    @Override
    public List<Product> getProductByProductName(String productName) {
        // TODO Auto-generated method stub
        List<Product> products = productRepo.getProductbyProductName(productName);

        return products;
    }

    // public void saveProductWithImage(Product product, MultipartFile imageFile) throws IOException {
    //     if (imageFile != null && !imageFile.isEmpty()) {
    //         // Create the upload directory if it doesn't exist
    //         Path path = Paths.get(uploadDir);
    //         if (!Files.exists(path)) {
    //             Files.createDirectories(path);
    //         }

    //         // Save the file with a unique name
    //         String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
    //         Path filePath = path.resolve(fileName);
    //         imageFile.transferTo(filePath.toFile());

    //         // Save the file path to the product entity
    //         product.setProductImagePath(filePath.toString());
    //     }
    //     // Save product with file path
    //     productRepo.save(product);
    // }
    public void saveProductWithImage(Product product, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            // Create the upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath); // Create the directory if it doesn't exist
            }
    
            // Generate a unique file name to avoid conflicts
            String originalFileName = imageFile.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = System.currentTimeMillis() + fileExtension;
    
            // Resolve the full path of the file
            Path filePath = uploadPath.resolve(fileName);
    
            // Save the file to the upload directory
            imageFile.transferTo(filePath.toFile());
    
            // Store only the relative path in the database for portability
            product.setProductImagePath("/uploaded-images/" + fileName);
        }
    
        // Save the product entity
        productRepo.save(product);
    }
    

    @Override
    public Product editProduct(Integer productId, Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editProduct'");
    }



}
