package com.mhrglobal;

import com.mhrglobal.domain.Payscale;
import com.mhrglobal.payment.BankPaymentService;
import com.mhrglobal.payment.PaymentService;
import com.mhrglobal.print.PayslipPrintService;
import com.mhrglobal.print.PrintService;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.mhrglobal.domain.Payscale.*;

//Implement a Pay Service that makes use of the payment and printing services
public class PayService {

    PaymentService cardPaymentService = new BankPaymentService();
    PrintService paperPrintService = new PayslipPrintService();

    public void handlePayRequest(String role, double workedHours, double contractedHours) {
        if (workedHours > 60.0) {
            System.out.println("Worked hours " + workedHours + " exceeds 60 hours.");
        }

        Payscale payscale = getPayscale(role);
        if (payscale == null) {
            System.out.printf("The role %s is not recognised\n", role);
            return;
        }

        String basePay = calculateBasePay(payscale, workedHours, contractedHours);
        String overtimePay = calculateOvertimePay(payscale, workedHours, contractedHours);

        BigDecimal total = new BigDecimal(basePay).add(new BigDecimal(overtimePay));

        paperPrintService.requestPrinting(role, basePay, overtimePay, total.toString());
        cardPaymentService.requestPayment(total.floatValue());
    }

    private Payscale getPayscale(String role) {
        Payscale payscale;

        switch (role.toLowerCase()) {
            case "engineer" -> payscale = ENGINEER;
            case "manager" -> payscale = MANAGER;
            case "director" -> payscale = DIRECTOR;
            default -> payscale = null;
        }

        return payscale;
    }

    private String calculateBasePay(Payscale payscale, double workedHours, double contractedHours) {
        if (workedHours > contractedHours) {
            workedHours = contractedHours;
        }

        return payscale.getHourlyRate().multiply(new BigDecimal(workedHours)).toString();
    }

    private String calculateOvertimePay(Payscale payscale, double workedHours, double contractedHours) {
        if (workedHours >= 60) {
            System.out.println("Worked hours exceeds 60. Setting Cap.");
            workedHours = 60;
        }

        if(workedHours <= contractedHours || payscale.getOvertimeRate() == 0) {
            return "0.00";
        }

        double overtimeHours = workedHours - contractedHours;

        BigDecimal overtimeHourlyRate = payscale.getHourlyRate()
                .multiply(new BigDecimal(payscale.getOvertimeRate())).divide(new BigDecimal(100), RoundingMode.HALF_EVEN)
                .add(payscale.getHourlyRate());
        return overtimeHourlyRate.multiply(new BigDecimal(overtimeHours)).toString();
    }
}