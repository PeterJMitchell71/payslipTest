package com.mhrglobal.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.mhrglobal.domain.Payscale.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PayscalesTest {

    @Test
    public void engineer_provides_correct_values() {
        assertThat(ENGINEER.getHourlyRate()).isEqualTo(new BigDecimal("5.00"));
        assertThat(ENGINEER.getOvertimeRate()).isEqualTo(35);
    }

    @Test
    public void manager_provides_correct_values() {
        assertThat(MANAGER.getHourlyRate()).isEqualTo(new BigDecimal("6.00"));
        assertThat(MANAGER.getOvertimeRate()).isEqualTo(25);
    }

    @Test
    public void director_provides_correct_values() {
        assertThat(DIRECTOR.getHourlyRate()).isEqualTo(new BigDecimal("7.00"));
        assertThat(DIRECTOR.getOvertimeRate()).isEqualTo(0);
    }
}
