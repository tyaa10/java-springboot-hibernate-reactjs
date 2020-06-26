package org.tyaa.demo.springboot.simplespa.model;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class PaymentModel {
    private Long id;
    private String transactionId;
    private String vendor;
    private String paymentDateString;
    private BigDecimal amount;
}
