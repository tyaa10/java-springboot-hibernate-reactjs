package org.tyaa.demo.springboot.simplespa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tyaa.demo.springboot.simplespa.dao.PaymentHibernateDAO;
import org.tyaa.demo.springboot.simplespa.entity.Payment;
import org.tyaa.demo.springboot.simplespa.model.PaymentModel;
import org.tyaa.demo.springboot.simplespa.model.PaymentResponseModel;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentHibernateDAO dao;

    public PaymentResponseModel pay(Payment payment) {
        dao.save(payment);
        PaymentResponseModel response =
                PaymentResponseModel.builder()
                .status("success")
                .message("Payment successfull with amount : " + payment.getAmount())
                .build();
        return response;
    }

    public PaymentResponseModel getTx(String vendor) {
        List<Payment> payments = dao.findByVendor(vendor);
        List<PaymentModel> paymentModels = payments.stream().map((p) ->
            PaymentModel.builder()
                .id(p.getId())
                .transactionId(p.getTransactionId())
                .vendor(p.getVendor())
                .paymentDateString((new SimpleDateFormat("dd/mm/yyyy HH:mm:ss a")).format(p.getPaymentDate()))
                .amount(p.getAmount())
                .build()
        ).collect(Collectors.toList());
        return PaymentResponseModel.builder()
            .status("success")
            .payments(paymentModels)
            .build();
    }
}
