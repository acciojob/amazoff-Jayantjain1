package com.driver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add-order")
    public ResponseEntity<String> addOrder(@RequestBody() Order order){
        String response = orderService.addOrdertoDB(order);
        return  new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PostMapping("/add-partner/{partnerId}")
    public ResponseEntity<String> addpartner(@PathVariable String partnerId){
        String response = orderService.addPartnerIdtoDB(partnerId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @PutMapping("/add-order-partner-pair")
    public ResponseEntity<String> addOrderPrtnerPair(@RequestParam("orderid") String orderId, @RequestParam("partnerid") String partnerId){
        String reponse = orderService.addpartnerpair(orderId,partnerId);
        return new ResponseEntity<>(reponse,HttpStatus.CREATED);
    }
    @GetMapping("/get-order-orderId/{orderid}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId){
        Order order = orderService.getOrderFromRepo(orderId);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }
    @GetMapping("/get-partner-by-partnerId/{partnerId}")
    public ResponseEntity<DeliveryPartner> getPartnerBypartnerId(@PathVariable String partnerId){
        DeliveryPartner deliveryPartner = orderService.getPartnerbyId(partnerId);
        return new ResponseEntity<>(deliveryPartner,HttpStatus.CREATED);
    }
    @GetMapping("/get-count-of-orders/{partnerId}")
    public ResponseEntity<Integer> getCountOfOrders(@PathVariable String partnerId){
        int count = orderService.CountOfOrders(partnerId);
        return new ResponseEntity<>(count,HttpStatus.CREATED);
    }
    @GetMapping("/get-Orders-by-partnerId/{partnerID}")
    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerID){
        List<String> orders = orderService.getListOforders(partnerID);
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
    @GetMapping("/get-list-of-Allorders")
    public ResponseEntity<List<String>> getListOfAllorders(){
        List<String> order = orderService.getAllOrders();
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }
    @GetMapping("/get-count-unassignedorders")
    public ResponseEntity<Integer> getAllUnassigned(){
        int count = orderService.getAllUnassigned();
        return new ResponseEntity<>(count,HttpStatus.CREATED);
    }
    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerID}")
    public ResponseEntity<Integer> getLeftAftergiven(@PathVariable String time, @PathVariable String partnerID){
        int count = orderService.getCount(time,partnerID);
        return new ResponseEntity<>(count,HttpStatus.CREATED);
    }
    @GetMapping("/get-last-delivery/{partnerID}")
    public ResponseEntity<String> getLastTime(@PathVariable String partnerId){
        String time = orderService.getLastTime(partnerId);
        return new ResponseEntity<>(time,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-partner-by-id/{partnerId}")
    public ResponseEntity<String> DeletePartnerbyId(@PathVariable String partnerId){
        String response = orderService.deletePartner(partnerId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-order-by-orderId/{orderId}")
    public ResponseEntity<String> deleOrderByPartnerID(@PathVariable String orderId){
        String message = orderService.deleteOrder(orderId);
        return new ResponseEntity<>(message,HttpStatus.CREATED);
    }
}



//package com.driver;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("orders")
//public class OrderController {
//
//    @Autowired
//    OrderService orderService;
//
//    @PostMapping("/add-order")
//    public ResponseEntity<String> addOrder(@RequestBody Order order){
//        String response = orderService.addOrdertoDB(order);
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }
//
//    @PostMapping("/add-partner/{partnerId}")
//    public ResponseEntity<String> addPartner(@PathVariable String partnerId){
//        String reponse = orderService.addPartertoDB(partnerId);
//        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/add-order-partner-pair")
//    public ResponseEntity<String> addOrderPartnerPair(@RequestParam String orderId, @RequestParam String partnerId){
//
//        //This is basically assigning that order to that partnerId
//        orderService.addOrderpartnerpair(orderId,partnerId);
//        return new ResponseEntity<>("New order-partner pair added successfully", HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-order-by-id/{orderId}")
//    public ResponseEntity<Order> getOrderById(@PathVariable String orderId){
//
//        Order order= null;
//        order = orderService.getOrderById(orderId);
//        //order should be returned with an orderId.
//
//        return new ResponseEntity<>(order, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-partner-by-id/{partnerId}")
//    public ResponseEntity<DeliveryPartner> getPartnerById(@PathVariable String partnerId){
//
//        DeliveryPartner deliveryPartner = null;
//        deliveryPartner = orderService.getdelevireyPartnerFromDB(partnerId);
//        //deliveryPartner should contain the value given by partnerId
//
//        return new ResponseEntity<>(deliveryPartner, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-order-count-by-partner-id/{partnerId}")
//    public ResponseEntity<Integer> getOrderCountByPartnerId(@PathVariable String partnerId){
//        //orderCount should denote the orders given by a partner-id
//        Integer orderCount = 0;
//        orderCount = orderService.getCount(partnerId);
//        return new ResponseEntity<>(orderCount, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-orders-by-partner-id/{partnerId}")
//    public ResponseEntity<List<String>> getOrdersByPartnerId(@PathVariable String partnerId){
//        //orders should contain a list of orders by PartnerId
//        List<String> orders = null;
//        orders = orderService.getListOfOrders(partnerId);
//        return new ResponseEntity<>(orders, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-all-orders")
//    public ResponseEntity<List<String>> getAllOrders(){
//        //Get all orders
//        List<String> orders = null;
//        orders = orderService.getAllOrders();
//        return new ResponseEntity<>(orders, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-count-of-unassigned-orders")
//    public ResponseEntity<Integer> getCountOfUnassignedOrders(){
//        //Count of orders that have not been assigned to any DeliveryPartner
//        Integer countOfOrders = 0;
//        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-count-of-orders-left-after-given-time/{partnerId}")
//    public ResponseEntity<Integer> getOrdersLeftAfterGivenTimeByPartnerId(@PathVariable String time, @PathVariable String partnerId){
//
//        Integer countOfOrders = 0;
//        //countOfOrders that are left after a particular time of a DeliveryPartner
//        return new ResponseEntity<>(countOfOrders, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/get-last-delivery-time/{partnerId}")
//    public ResponseEntity<String> getLastDeliveryTimeByPartnerId(@PathVariable String partnerId){
//        String time = null;
//
//        //Return the time when that partnerId will deliver his last delivery order.
//
//        return new ResponseEntity<>(time, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete-partner-by-id/{partnerId}")
//    public ResponseEntity<String> deletePartnerById(@PathVariable String partnerId){
//
//        //Delete the partnerId
//        //And push all his assigned orders to unassigned orders.
//
//        return new ResponseEntity<>(partnerId + " removed successfully", HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete-order-by-id/{orderId}")
//    public ResponseEntity<String> deleteOrderById(@PathVariable String orderId){
//
//        //Delete an order and also
//        // remove it from the assigned order of that partnerId
//
//        return new ResponseEntity<>(orderId + " removed successfully", HttpStatus.CREATED);
//    }
//}
