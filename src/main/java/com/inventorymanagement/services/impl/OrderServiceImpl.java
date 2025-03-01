package com.inventorymanagement.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorymanagement.entities.Order;
import com.inventorymanagement.repositories.OrderRepo;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepo orderRepo;

    public void addOrder(Order order){
        orderRepo.save(order);
    }


    public List<Object[]> getOrdersBetweenDates(Date fromDate, Date toDate) {
        return orderRepo.findOrdersByDateRange(fromDate, toDate);

    }

}
