package com.kce.course.model;

import java.time.LocalDateTime;

public class Receipt {
    private Enrollment enrollment;
    private Payment payment;
    private LocalDateTime issuedAt;

    public Receipt(Enrollment enrollment, Payment payment) {
        this.enrollment = enrollment;
        this.payment = payment;
        this.issuedAt = LocalDateTime.now();
    }

    public Enrollment getEnrollment() { return enrollment; }
    public Payment getPayment() { return payment; }

    @Override
    public String toString() {
        return "Receipt for Enrollment " + enrollment.getEnrollmentId() +
                "\nStudent: " + enrollment.getStudent().getName() +
                "\nCourse: " + enrollment.getCourse().getTitle() +
                "\nAmount: " + payment.getAmount() +
                "\nMethod: " + payment.getMethod() +
                "\nSuccess: " + payment.isSuccess() +
                "\nIssued: " + issuedAt;
    }
}
