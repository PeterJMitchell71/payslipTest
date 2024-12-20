package com.mhrglobal.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PayServicePaymentServiceTest {

    @Test
    public void testPayment() {
        PaymentService paymentService = new BankPaymentService();
        assertTrue(paymentService.requestPayment(3f), "Test the bank payment service");
    }

}