package com.mhrglobal;

import com.mhrglobal.payment.PaymentService;
import com.mhrglobal.print.PrintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class PayServiceTest {
    @Mock
    PrintService printService;
    @Mock
    PaymentService paymentService;
    @InjectMocks
    PayService payService = new PayService();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void engineer_gets_correct_pay_under_60_hours() {
        ArgumentCaptor<String> role = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> base = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> overtime = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> total = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Float> paymentTotal = ArgumentCaptor.forClass(Float.class);

        payService.handlePayRequest("engineer", 21, 20);

        verify(printService).requestPrinting(role.capture(), base.capture(), overtime.capture(), total.capture());
        verify(paymentService).requestPayment(paymentTotal.capture());

        assertThat(role.getValue()).isEqualTo("engineer");
        assertThat(base.getValue()).isEqualTo("100.00");
        assertThat(overtime.getValue()).isEqualTo("6.75");
        assertThat(total.getValue()).isEqualTo("106.75");
        assertThat(paymentTotal.getValue()).isEqualTo(106.75f);
    }

    @Test
    public void manager_gets_correct_pay_under_60_hours() {
        ArgumentCaptor<String> role = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> base = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> overtime = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> total = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Float> paymentTotal = ArgumentCaptor.forClass(Float.class);

        payService.handlePayRequest("manager", 21, 20);

        verify(printService).requestPrinting(role.capture(), base.capture(), overtime.capture(), total.capture());
        verify(paymentService).requestPayment(paymentTotal.capture());

        assertThat(role.getValue()).isEqualTo("manager");
        assertThat(base.getValue()).isEqualTo("120.00");
        assertThat(overtime.getValue()).isEqualTo("7.50");
        assertThat(total.getValue()).isEqualTo("127.50");
        assertThat(paymentTotal.getValue()).isEqualTo(127.50f);
    }

    @Test
    public void director_gets_correct_pay_under_60_hours() {
        ArgumentCaptor<String> role = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> base = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> overtime = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> total = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Float> paymentTotal = ArgumentCaptor.forClass(Float.class);

        payService.handlePayRequest("director", 21, 20);

        verify(printService).requestPrinting(role.capture(), base.capture(), overtime.capture(), total.capture());
        verify(paymentService).requestPayment(paymentTotal.capture());

        assertThat(role.getValue()).isEqualTo("director");
        assertThat(base.getValue()).isEqualTo("140.00");
        assertThat(overtime.getValue()).isEqualTo("0.00");
        assertThat(total.getValue()).isEqualTo("140.00");
        assertThat(paymentTotal.getValue()).isEqualTo(140.00f);
    }

    @Test
    public void overtime_caps_at_60_hours() {
        ArgumentCaptor<String> role = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> base = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> overtime = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> total = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Float> paymentTotal = ArgumentCaptor.forClass(Float.class);

        payService.handlePayRequest("engineer", 65, 20);

        verify(printService).requestPrinting(role.capture(), base.capture(), overtime.capture(), total.capture());
        verify(paymentService).requestPayment(paymentTotal.capture());

        assertThat(role.getValue()).isEqualTo("engineer");
        assertThat(base.getValue()).isEqualTo("100.00");
        assertThat(overtime.getValue()).isEqualTo("270.00");
        assertThat(total.getValue()).isEqualTo("370.00");
        assertThat(paymentTotal.getValue()).isEqualTo(370.00f);
    }

    @Test
    public void worker_under_hours_gets_correct_pay() {
        ArgumentCaptor<String> role = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> base = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> overtime = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> total = ArgumentCaptor.forClass(String.class);

        ArgumentCaptor<Float> paymentTotal = ArgumentCaptor.forClass(Float.class);

        payService.handlePayRequest("engineer", 15, 20);

        verify(printService).requestPrinting(role.capture(), base.capture(), overtime.capture(), total.capture());
        verify(paymentService).requestPayment(paymentTotal.capture());

        assertThat(role.getValue()).isEqualTo("engineer");
        assertThat(base.getValue()).isEqualTo("75.00");
        assertThat(overtime.getValue()).isEqualTo("0.00");
        assertThat(total.getValue()).isEqualTo("75.00");
        assertThat(paymentTotal.getValue()).isEqualTo(75.00f);
    }

    @Test
    public void service_handles_unknown_role() {
        payService.handlePayRequest("wheeltapper", 20, 20);
        verify(printService, never()).requestPrinting(anyString(), anyString(), anyString(), anyString());
        verify(paymentService, never()).requestPayment(anyFloat());
    }
}
