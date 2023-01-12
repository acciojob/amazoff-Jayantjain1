package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String,Order> orderHashMap = new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerHashMap = new HashMap<>();
    HashMap<String,List<Order>> assigned = new HashMap<>();
    HashMap<String,Order> unassigned = new HashMap<>();

    public String SaveOrder(Order order){
        String key = order.getId();
        orderHashMap.put(key,order);
        unassigned.put(key,order);
        return "Success";
    }
    public String SavePartnerId(String partnerId){
        DeliveryPartner partner = new DeliveryPartner(partnerId);
        deliveryPartnerHashMap.put(partnerId,partner);
        return "success";
    }
    public String addOrderPArtnerPair(String orderId,String partnerId){
        if(orderHashMap.containsKey(orderId) && deliveryPartnerHashMap.containsKey(partnerId)){
            if(assigned.containsKey(partnerId)){
                assigned.get(partnerId).add(orderHashMap.get(orderId));
                unassigned.remove(orderId);
                deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(assigned.get(partnerId).size());
            }
            else{
                List<Order> orderList = new ArrayList<>();
                orderList.add(orderHashMap.get(orderId));
                assigned.put(partnerId,orderList);
                unassigned.remove(orderId);
                deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(1);
            }
        }
        return "success";
    }
    public Order getITFROMDB(String orderId){
        if(orderHashMap.containsKey(orderId)){
            return orderHashMap.get(orderId);
        }
        return null;
    }
    public DeliveryPartner getpatnerFromDb(String partnerId){
        if(deliveryPartnerHashMap.containsKey(partnerId)){
            return deliveryPartnerHashMap.get(partnerId);
        }
        return null;
    }
    public int getCountFromDb(String partnerId){
        return assigned.get(partnerId).size();
    }
    public List<String> getListOfAll(String partnerId){
        List<String> result = new ArrayList<>();
        if(assigned.containsKey(partnerId)){
            List<Order> list = assigned.get(partnerId);
            for(Order itr: list){
                result.add(itr.getId());
            }
        }
        return result;
    }
    public List<String> getAll(){
        List<String> listofOrders = new ArrayList<>();
//        for(String key: orderHashMap.keySet()){
//            listofOrders.add(key);
//        }
//        return listofOrders;
        for(String key: assigned.keySet()){
            List<Order> orders = assigned.get(key);
            for(Order itr: orders){
                listofOrders.add(itr.getId());
            }
        }
        for (String key: unassigned.keySet()){
            listofOrders.add(unassigned.get(key).getId());
        }
        return listofOrders;
    }
    public int allunassigned(){
        return unassigned.size();
    }
    public int getAllLeftOrders(String time,String partnerId){
        String hr = time.substring(0,2);
        String min = time.substring(3,5);
        int totaltime = (Integer.parseInt(hr) * 60) + Integer.parseInt(min);
        int count = 0;
        for(String key: orderHashMap.keySet()){
            if(orderHashMap.get(key).getDeliveryTime() > totaltime){
                count++;
            }
        }
        return count;
    }
    public String getLastTime(String partnerId){
        List<Order> orderListlist = assigned.get(partnerId);
        int max = 0;
        for(Order itr: orderListlist){
            if(itr.getDeliveryTime() > max){
                max = itr.getDeliveryTime();
            }
        }
        String time = Integer.toString(max);
        if(time.length() == 1){
            String str = "00" + ":" + time;
            return str;
        }
        else{
            int hr = Integer.parseInt(time) / 60;
            int min = Integer.parseInt(time) % 60;
            if(Integer.toString(hr).length() == 1){
                return "0"+Integer.toString(hr)+":"+Integer.toString(min);
            }
            else{
                return Integer.toString(hr)+":"+Integer.toString(min);
            }
        }
    }
    public String deleTePartner(String partnerId){
        if(assigned.containsKey(partnerId)){
            List<Order> orders = assigned.get(partnerId);
            for(Order order: orders){
                unassigned.put(partnerId,order);
            }
            assigned.remove(partnerId);
        }
        if(deliveryPartnerHashMap.containsKey(partnerId)){
            deliveryPartnerHashMap.remove(partnerId);
        }
        return "success";
    }
    public String deleTOrder(String orderId){
        if(orderHashMap.containsKey(orderId)){
            orderHashMap.remove(orderId);
        }
        for(String key: assigned.keySet()){
            List<Order> list = assigned.get(key);
            for (Order order : list){
                if(order.getId().equals(orderId)){
                    list.remove(order);
                }
            }
        }
        return "success";
    }
}
