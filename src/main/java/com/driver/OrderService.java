package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public String addOrdertoDB(Order order){
        String message = orderRepository.SaveOrder(order);
        return message;
    }
    public String addPartnerIdtoDB(String partnerId){
        String message = orderRepository.SavePartnerId(partnerId);
        return message;
    }
    public String addpartnerpair(String orderId,String partnerId){
        String message = orderRepository.addOrderPArtnerPair(orderId,partnerId);
        return message;
    }
    public Order getOrderFromRepo(String orderId){
        Order order = orderRepository.getITFROMDB(orderId);
        return order;
    }
    public DeliveryPartner getPartnerbyId(String partnerId){
        DeliveryPartner deliveryPartner = orderRepository.getpatnerFromDb(partnerId);
        return deliveryPartner;
    }
    public int CountOfOrders(String partnerId){
        int count = orderRepository.getCountFromDb(partnerId);
        return count;
    }
    public List<String> getListOforders(String partnerId){
        List<String> list = orderRepository.getListOfAll(partnerId);
        return list;
    }
    public List<String> getAllOrders(){
        List<String> list = orderRepository.getAll();
        return list;
    }
    public int getAllUnassigned(){
        return orderRepository.allunassigned();
    }
    public int getCount(String time,String partnerId){
        return orderRepository.getAllLeftOrders(time,partnerId);
    }
    public String getLastTime(String partnerId){
        return orderRepository.getLastTime(partnerId);
    }
    public String deletePartner(String partnerId){
        return orderRepository.deleTePartner(partnerId);
    }
    public String deleteOrder(String orderId){
        return orderRepository.deleTOrder(orderId);
    }
}
