package com.inventorymanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventorymanagement.entities.Category;
import com.inventorymanagement.entities.Order;
import com.inventorymanagement.entities.Product;
import com.inventorymanagement.entities.User;
import com.inventorymanagement.forms.CategoryForm;
import com.inventorymanagement.forms.ProductForm;
import com.inventorymanagement.repositories.OrderRepo;
import com.inventorymanagement.services.CategoryService;
import com.inventorymanagement.services.ProductService;
import com.inventorymanagement.services.impl.OrderServiceImpl;
import com.inventorymanagement.services.impl.UserServiceImpl;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserServiceImpl UserServiceImpl;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/home")
    public String requestMethodName(Model model) {
        List<User> userlist = UserServiceImpl.getUsers();
        Integer orderCounts = orderRepo.getSumTotalProfit();
        model.addAttribute("orderCounts", orderCounts);
        // System.out.println(orderCounts.get(0));
        System.out.println(orderCounts);
      
        //System.out.println(orderCounts.);

        Integer usercount = 0;
        for (User u : userlist) {
            if (u.getRoleList().contains("ROLE_USER")) {
                usercount++;
            }
        }
        model.addAttribute("usercount", usercount);
        return "admin/home";
    }

    @GetMapping(value = "/get-categorieslist")
    public String ListAllCategories(Model model) {
        List<Category> catList = categoryService.ListAllCategory();

        model.addAttribute("category", catList);
        return "admin/categorypage";
    }

    @RequestMapping(value = "/get-orders")
    public String ListAllOrder(Model model) {
        List<Order> orderlist = orderRepo.getOrder();

        model.addAttribute("orderlist", orderlist);
        return "admin/orderpage";
    }

    @RequestMapping(value = "/add-categorypage")
    public String AddCategories(Model model) {
        CategoryForm categoryForm = new CategoryForm();
        model.addAttribute("categoryForm", categoryForm);
        return "admin/addcategory";
    }

    @RequestMapping(value = "/report")
    public String ReportRequest(Model model) {
        List<Object[]> orderCounts = orderRepo.getReport();
        model.addAttribute("orderCounts", orderCounts);
        return "admin/report";
    }

    @RequestMapping(value = "/add-productpage")
    public String AddProductPage(Model model) {
        List<Category> categories = categoryService.ListAllCategory();
        model.addAttribute("categories", categories);

        ProductForm productForm = new ProductForm();
        model.addAttribute("productForm", productForm);
        return "admin/addproduct";
    }

    @RequestMapping(value = "/profile")
    public String profile1() {
        return "admin/profile";
    }

    @GetMapping(value = "/productslist")
    public String getAllProducts(Model model) {
        List<Product> products = productService.listAllProduct();
        model.addAttribute("products", products);
        System.out.println(products);
        return "admin/products";
    }

}
