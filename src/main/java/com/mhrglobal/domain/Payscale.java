package com.mhrglobal.domain;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum Payscale {
        ENGINEER(new BigDecimal("5.00"), 35),
        MANAGER(new BigDecimal("6.00"), 25),
        DIRECTOR(new BigDecimal("7.00"), 0);

        private BigDecimal hourlyRate;
        private int overtimeRate;

        Payscale(BigDecimal hourlyRate, int overtimeRate) {
            this.hourlyRate = hourlyRate;
            this.overtimeRate = overtimeRate;
        }
}
