// File: com/kce/course/service/CourseService.java
package com.kce.course.service;

import com.kce.course.model.Course;
import com.kce.course.model.Instructor;

import java.math.BigDecimal;
import java.util.*;

public class CourseService {
    private Map<String, Course> courses = new LinkedHashMap<>();

    public CourseService() {
        // Optionally pre-populate some sample courses
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseId(), course);
    }

    public Optional<Course> findById(String courseId) {
        return Optional.ofNullable(courses.get(courseId));
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses.values());
    }

    public boolean exists(String courseId) {
        return courses.containsKey(courseId);
    }
}
