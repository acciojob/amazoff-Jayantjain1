package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> orderHashMap = new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap = new HashMap<>();
    HashMap<String, List<String>> stringListHashMap = new HashMap<>();
    public String addOrdertoDatabase(Order order){
        String key = order.getId();
        orderHashMap.put(key,order);
        return "New order added successfully";
    }
    public String addPartnerToDataBase(String partnerId){
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId,partner);
        return "New delivery partner added successfully";
    }
    public void addPartnerandOrderToDataBase(String orderId,String partnerId){
        if(orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId)){
            List<String> listofOrder = new ArrayList<>();
            if(stringListHashMap.containsKey(partnerId)){
                stringListHashMap.get(partnerId).add(partnerId);
            }
            else{
                listofOrder.add(orderId);
                stringListHashMap.put(partnerId,listofOrder);
            }
        }
    }
    public Order getOrderFromDB(String orderId){
//        return orderHashMap.get(orderId);
        for(String  key: orderHashMap.keySet()){
            if(orderId.equals(key)){
                return orderHashMap.get(key);
            }
        }
        return null;
    }
    public DeliveryPartner getDeliveryFromDB(String partnerId){
        if(deliveryPartnerHashMap.containsKey(partnerId)){
            return deliveryPartnerHashMap.get(partnerId);
        }
        return null;
    }
    public int getCountcorrespondingtopartnerId(String partnerId){
        int count = 0;
        if(stringListHashMap.containsKey(partnerId)){
            List<String> countOrderList = stringListHashMap.get(partnerId);
            for(int i=0;i<countOrderList.size();i++){
                count++;
            }
            return count;
        }
        return count;
    }
    public List<String> getList(String partnerId){
        return stringListHashMap.get(partnerId);
    }
    public List<String> getAllOrders(){
        List<String> orderList = new ArrayList<>();
        for(String key: stringListHashMap.keySet()){
            List<String> newList = stringListHashMap.get(key);
            for(int i=0;i<newList.size();i++){
                orderList.add(newList.get(i));
            }
        }
        return orderList;
    }
}
