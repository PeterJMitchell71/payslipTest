package com.mhrglobal.payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Do not change
public class BankPaymentService implements PaymentService{

    Logger logger = LoggerFactory.getLogger(BankPaymentService.class);

    @Override
    public boolean requestPayment(float amount) {
        //No need to implement
        logger.info("Bank Payment called, amount: £{}", amount);
        return true;
    }
}
