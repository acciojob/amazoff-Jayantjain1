package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public String addOrdertoDB(Order order){
        String message = orderRepository.addOrdertoDatabase(order);
        return message;
    }
    public String addPartertoDB(String partnerId){
        String message = orderRepository.addPartnerToDataBase(partnerId);
        return message;
    }
    public void addOrderpartnerpair(String orderId,String partnerId){
        orderRepository.addPartnerandOrderToDataBase(orderId,partnerId);
    }
    public Order getOrderById(String orderId){
        Order order = orderRepository.getOrderFromDB(orderId);
        return order;
    }
    public DeliveryPartner getdelevireyPartnerFromDB(String partnerId){
        DeliveryPartner deliveryPartner = orderRepository.getDeliveryFromDB(partnerId);
        return deliveryPartner;
    }
    public int getCount(String partnerId){
        int count = orderRepository.getCountcorrespondingtopartnerId(partnerId);
        return count;
    }
    public List<String> getListOfOrders(String partnerId){
        List<String> ordersList = orderRepository.getList(partnerId);
        return ordersList;
    }
    public List<String> getAllOrders(){
        List<String> AllOrdersList = orderRepository.getAllOrders();
        return AllOrdersList;
    }
}
