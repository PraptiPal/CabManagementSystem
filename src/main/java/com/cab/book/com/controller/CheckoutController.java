package com.cab.book.com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cab.book.com.entity.Order;
import com.cab.book.com.entity.User;
import com.cab.book.com.payload.OrderDto;
import com.cab.book.com.payload.OrderResponseDto;
import com.cab.book.com.payload.OrderStatus;
import com.cab.book.com.payload.PayPalAppContextDto;
import com.cab.book.com.payload.PaymentLandingPage;
import com.cab.book.com.repository.OrderRepository;
import com.cab.book.com.repository.UserRepository;
import com.cab.book.com.util.PayPalHttpClient;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/payment")
@Slf4j
public class CheckoutController {

    private final PayPalHttpClient payPalHttpClient;
    private final OrderRepository orderRepository;
    
    @Autowired private UserRepository userRepository;

    @Autowired
    public CheckoutController(PayPalHttpClient payPalHttpClient, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.payPalHttpClient = payPalHttpClient;
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderResponseDto> checkout(@RequestBody OrderDto orderDto, @PathVariable Integer userId) throws Exception {
        var appContext = new PayPalAppContextDto();
        appContext.setReturnUrl("http://localhost:9090/checkout/success");
        appContext.setBrandName("My brand");
        appContext.setLandingPage(PaymentLandingPage.BILLING);
        orderDto.setApplicationContext(appContext);
        var orderResponse = payPalHttpClient.createOrder(orderDto);

        var entity = new Order();
        entity.setPaypalOrderId(orderResponse.getId());
        entity.setPaypalOrderStatus(orderResponse.getStatus().toString());
        User user = userRepository.findById(userId).orElseThrow();
        entity.setUserP(user);
        var out = orderRepository.save(entity);
        String i=orderResponse.getId();
        log.info("Saved order: {}", out);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping(value = "/success")
    public ResponseEntity<String> paymentSuccess(HttpServletRequest request) {
        var orderId = request.getParameter("token");
        var out = orderRepository.findByPaypalOrderId(orderId);
        out.setPaypalOrderStatus(OrderStatus.APPROVED.toString());
        orderRepository.save(out);
        return ResponseEntity.ok().build();//.body("Payment success");
    }

}