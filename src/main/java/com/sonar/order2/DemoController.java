package com.sonar.order2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
//    Logger logger = LogManager.getLogger(SplunkDemoController.class);
    @Autowired
    private final OrderService orderService;

    @Autowired
    private SplunkService splunkService;


        @RequestMapping("/orders")
        public ResponseEntity<List<Order>> allOrders() {
            // testing
            System.out.println("SplunkDemoController called All orders method called");
            splunkService.sendLog("SplunkDemoController called All orders method called");
            return ResponseEntity.ok(orderService.getAllOrders());
        }

    @RequestMapping("/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(int orderId) {
        splunkService.sendLog("SplunkDemoController called getOrderById method called {}");
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @RequestMapping( path = "/orders/add" , method = RequestMethod.POST)
    public ResponseEntity<Order> addOrder(Order order) {
        splunkService.sendLog("SplunkDemoController called addOrder method called {}" );
            Order addedOrder = orderService.addOrder(order);
        splunkService.sendLog("SplunkDemoController called addOrder method called after {}" );
        return ResponseEntity.ok(addedOrder);
    }

}
