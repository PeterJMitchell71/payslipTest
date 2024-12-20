package com.mhrglobal.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Do not change
public class PayslipPrintService implements PrintService {

    Logger logger = LoggerFactory.getLogger(PayslipPrintService.class);

    @Override
    public void requestPrinting(String role, String BasePay, String Overtime, String Total) {
        //No need to implement
        logger.info("Print called, Role: {}, BasePay: £{}, Overtime: £{}, Total: £{}", role, BasePay, Overtime, Total);
    }
}
