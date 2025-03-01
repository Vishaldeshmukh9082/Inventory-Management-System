package com.inventorymanagement.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inventorymanagement.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer>{

    @Query("SELECT o.orderProductName, COUNT(o) as orderCount,Sum(o.orderProductQuantity),sum(o.totalAmount) FROM Order o where o.orderStatus='Delivered' GROUP BY o.orderProductName ")
    public List<Object[]> getReport();


    @Query("SELECT o from Order o Order By o.orderDate DESC")
    public List<Order> getOrder();

    @Query("SELECT o from Order o where o.customerId=:customerId Order By o.orderDate DESC")
    public List<Order> getOrderByCustomerId(String customerId);

    @Query("SELECT o.orderProductName, COUNT(o) as orderCount, SUM(o.orderProductQuantity), SUM(o.totalAmount) FROM Order o WHERE o.orderDate BETWEEN :fromDate AND :toDate GROUP BY o.orderProductName")
    List<Object[]> findOrdersByDateRange(Date fromDate, Date toDate);

    @Query("SELECT SUM(o.totalAmount) From Order o where o.orderStatus='Delivered'")
    Integer getSumTotalProfit();
    

    


}
