package org.tyaa.demo.springboot.simplespa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tyaa.demo.springboot.simplespa.entity.Payment;
import org.tyaa.demo.springboot.simplespa.model.PaymentResponseModel;
import org.tyaa.demo.springboot.simplespa.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/payNow")
    public PaymentResponseModel payInstant(@RequestBody Payment payment) {
        return service.pay(payment);
    }

    @GetMapping("/getTransactionsByVendor/{vendor}")
    public PaymentResponseModel getTransaction(@PathVariable String vendor) {
        return service.getTx(vendor);
    }
}
