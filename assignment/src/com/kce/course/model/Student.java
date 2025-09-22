package com.kce.course.model;

import java.math.BigDecimal;

public class Student {
    private String studentId;
    private String name;
    private String email;

    public Student(String studentId, String name, String email) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getFeeForCourse(Course course) {
        return course.getFee();
    }

    @Override
    public String toString() {
        return name + " (ID:" + studentId + ", " + email + ")";
    }
}
