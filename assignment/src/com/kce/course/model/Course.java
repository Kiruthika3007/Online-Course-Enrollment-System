package com.kce.course.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Course {
	private String courseId;
	private String title;
	private Instructor instructor;
	private int capacity;
	private BigDecimal fee;

	public Course(String courseId, String title, Instructor instructor, int capacity, BigDecimal fee) {
		this.courseId = courseId;
		this.title = title;
		this.instructor = instructor;
		this.capacity = capacity;
		this.fee = fee;
	}

	public String getCourseId() {
		return courseId;
		}
	public String getTitle() {
		return title;
		}
	public Instructor getInstructor() {
		return instructor;
		}
	public int getCapacity() {
		return capacity;
		}
	public BigDecimal getFee() {
		return fee;
		}

	public void setTitle(String title) {
		this.title = title;
		}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
		}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
		}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
		}

	public boolean isAvailable() {
		return capacity > 0;
		}

	public boolean reduceCapacity() {
		if (capacity > 0) {
			capacity--;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return courseId + " - " + title + "  Instructor: " + instructor.getName()
		       + "  Seats: " + capacity + "  Fee: " + fee;
		}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Course)) return false;
		Course c = (Course) o;
		return Objects.equals(courseId, c.courseId);
		}

	@Override
	public int hashCode() {
		return Objects.hash(courseId);
		}
	}
