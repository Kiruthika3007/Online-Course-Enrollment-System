package com.kce.course.service;

import com.kce.course.model.*;

import java.math.BigDecimal;
import java.util.*;

public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private final Map<String, Receipt> receipts = new HashMap<>();

    public Enrollment createEnrollment(Student s, Course c) {
        Enrollment e = new Enrollment(s, c);
        enrollments.add(e);
        return e;
    }

    public boolean makePaymentAndConfirm(Enrollment e, BigDecimal amount, String method, boolean success) {
        BigDecimal expected = e.getStudent().getFeeForCourse(e.getCourse());
        if (!success || amount.compareTo(expected) < 0) return false;
        if (!e.getCourse().reduceCapacity()) return false;
        e.confirm();
        Payment p = new Payment(amount, method, true);
        Receipt r = new Receipt(e, p);
        receipts.put(e.getEnrollmentId(), r);
        return true;
    }

    public Optional<Receipt> getReceiptForEnrollment(Enrollment e) {
        return Optional.ofNullable(receipts.get(e.getEnrollmentId()));
    }

    public List<Enrollment> listEnrollments() {
        return new ArrayList<>(enrollments);
    }
}
