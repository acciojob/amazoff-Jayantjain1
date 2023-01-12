package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;

        String hr = deliveryTime.substring(0,2);
        String min = deliveryTime.substring(3,5);
        int hour = Integer.parseInt(hr);
        int minutes = Integer.parseInt(min);
        int totalTime = (hour*60) + minutes;
        this.deliveryTime = totalTime;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return this.id;
    }

    public int getDeliveryTime() {
        return this.deliveryTime;
    }
}


//    public String getId() {
//        return id;
//    }
//
//    public int getDeliveryTime() {
//        return deliveryTime;
//    }
