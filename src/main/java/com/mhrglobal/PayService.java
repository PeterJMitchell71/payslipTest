package com.mhrglobal;

import com.mhrglobal.payment.BankPaymentService;
import com.mhrglobal.payment.PaymentService;
import com.mhrglobal.print.PayslipPrintService;
import com.mhrglobal.print.PrintService;

//Implement a Pay Service that makes use of the payment and printing services
public class PayService {

    PaymentService cardPaymentService = new BankPaymentService();
    PrintService paperPrintService = new PayslipPrintService();

}