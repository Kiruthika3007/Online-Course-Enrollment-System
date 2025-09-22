package com.kce.course.model;

import java.util.UUID;

public class Enrollment {

    public enum Status {
        PENDING, CONFIRMED
    }

    private String enrollmentId;
    private Student student;
    private Course course;
    private Status status;

    public Enrollment(Student student, Course course) {
        this.enrollmentId = UUID.randomUUID().toString();
        this.student = student;
        this.course = course;
        this.status = Status.PENDING;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Status getStatus() {
        return status;
    }

    public void confirm() {
        this.status = Status.CONFIRMED;
    }
}
