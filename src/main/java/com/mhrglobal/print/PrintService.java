package com.mhrglobal.print;

//Do not change
public interface PrintService {

    /**
     * Enters the payment process
     *
     * @param BasePay  Employee base pay
     * @param Overtime additional overtime worked
     * @param Total    Total amount to be paid
     */
    void requestPrinting(String role, String BasePay, String Overtime, String Total);
}
