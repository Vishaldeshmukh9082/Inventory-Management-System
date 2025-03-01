package com.inventorymanagement.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventorymanagement.entities.Category;
import com.inventorymanagement.entities.Order;
import com.inventorymanagement.entities.Product;
import com.inventorymanagement.forms.OrderForm;
import com.inventorymanagement.forms.SearchForm;
import com.inventorymanagement.repositories.OrderRepo;
import com.inventorymanagement.services.CategoryService;
import com.inventorymanagement.services.ProductService;
import com.inventorymanagement.services.impl.OrderServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {

    // private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    OrderServiceImpl orderServiceImpl;

    @Autowired
    OrderRepo orderRepo;

    @GetMapping("/")
    public String getMethodName() {
        return "redirect:home";
    }

    @RequestMapping(value = "/home")
    public String Home(Model model) {
        List<Product> productslist = productService.listAllProduct();
        model.addAttribute("productslist", productslist);
        List<Category> categories = categoryService.ListAllCategory();
        model.addAttribute("categories", categories);
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        return "user/home";
    }

    @RequestMapping("/myorders")
    public String myorder(Model model) {
        List<Order> orders = orderRepo.getOrder();
        List<Order> orderlist = new ArrayList<Order>();

        for (Order o : orders) {
            if (!"Delivered".equals(o.getOrderStatus())) {
                orderlist.add(o);
            }
        }
        // getOrderByCustomerId( CustomerId);

        model.addAttribute("orderlist", orderlist);
        return "user/myorders";
    }

    @RequestMapping("/myordershistory")
    public String myorderhistory(Model model) {
        List<Order> orders = orderRepo.getOrder();
        List<Order> orderlist = new ArrayList<Order>();

        for (Order o : orders) {
            if ("Delivered".equals(o.getOrderStatus())) {
                orderlist.add(o);
            }
        }
        model.addAttribute("orderlist", orderlist);

        return "user/myordershistory";
    }

    @RequestMapping("/userprofile")
    public String userprofile() {
        return "user/userprofile";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("productName") String productName, Model model) {
        List<Product> productsList = productService.getProductByProductName(productName);
        System.out.println(productName);

        if (productsList == null || productsList.isEmpty()) {
            return "user/notfoundpage";
        } else {
            model.addAttribute("productslist", productsList);
        }

        return "user/searchpage";
    }

    @GetMapping("/get-product/{productId}")
    public String getProduct(@PathVariable Integer productId, Model model) {
        Product product = productService.getProductByProductId(productId.toString());
        model.addAttribute("product", product);

        return "user/productinfo";

    }

    @GetMapping("/add-order/{productId}")
    public String bookOrder(@PathVariable Integer productId, Model model) {
        Product product = productService.getProductByProductId(productId.toString());
        OrderForm orderForm = new OrderForm();
        orderForm.setOrderProductName(product.getProductName());
        orderForm.setOrderProductPrice(product.getProductPrice());
        orderForm.setOrderStatus("Ordered");

        model.addAttribute("orderForm", orderForm);

        return "user/addorder";

    }

    @PostMapping("/addorder")
    public String addorder(@ModelAttribute OrderForm orderForm) {
        System.out.println(orderForm);
        Order order = Order.builder()
                .customerId(orderForm.getCustomerId())
                .orderProductName(orderForm.getOrderProductName())
                .orderProductPrice(orderForm.getOrderProductPrice())
                .orderDate(orderForm.getOrderDate())
                .orderProductQuantity(orderForm.getOrderProductQuantity())
                .orderStatus("Ordered")
                .totalAmount(orderForm.getTotalAmount())
                .build();

        orderServiceImpl.addOrder(order);

        return "user/myorders";
    }

    @RequestMapping(value = "/get-activeorders/{CustomerId}")
    public String ListAllOrder(@RequestParam String CustomerId, Model model) {
        List<Order> orderlist = orderRepo.getOrder();
        // getOrderByCustomerId( CustomerId);

        model.addAttribute("orderlist", orderlist);
        return "admin/myorders";
    }

}
