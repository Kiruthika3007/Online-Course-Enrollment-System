package com.kce.course.model;

public class Instructor {
    private String instructorId;
    private String name;
    private String email;

    public Instructor(String instructorId, String name, String email) {
        this.instructorId = instructorId;
        this.name = name;
        this.email = email;
    }

    public String getInstructorId() { return instructorId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return name + " (ID:" + instructorId + ", " + email + ")";
    }
}
