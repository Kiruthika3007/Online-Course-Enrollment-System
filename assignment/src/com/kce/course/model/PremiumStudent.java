package com.kce.course.model;

import java.math.BigDecimal;

public class PremiumStudent extends Student {
    private double discountPercent;

    public PremiumStudent(String studentId, String name, String email, double discountPercent) {
        super(studentId, name, email);
        this.discountPercent = discountPercent;
    }

    @Override
    public BigDecimal getFeeForCourse(Course course) {
        BigDecimal fee = course.getFee();
        BigDecimal discount = fee.multiply(BigDecimal.valueOf(discountPercent / 100.0));
        return fee.subtract(discount);
    }

    @Override
    public String toString() {
        return super.toString() + " [Premium " + discountPercent + "% discount]";
    }
}
