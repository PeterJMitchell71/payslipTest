package com.mhrglobal.print;

import org.junit.jupiter.api.Test;

class PaperPrintServiceTest {

    @Test
    public void testPrint() {
        PrintService payslipPrintService = new PayslipPrintService();
        payslipPrintService.requestPrinting("Engineer", "100", "50", "150");
        payslipPrintService.requestPrinting("Manager", "120","0", "120");
    }

}