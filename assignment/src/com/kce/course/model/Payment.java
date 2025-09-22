package com.kce.course.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private BigDecimal amount;
    private String method;
    private boolean success;
    private LocalDateTime time;

    public Payment(BigDecimal amount, String method, boolean success) {
        this.amount = amount;
        this.method = method;
        this.success = success;
        this.time = LocalDateTime.now();
    }

    public BigDecimal getAmount() { return amount; }
    public String getMethod() { return method; }
    public boolean isSuccess() { return success; }
    public LocalDateTime getTime() { return time; }
}
