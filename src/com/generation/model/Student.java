package com.generation.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student
        extends Person
        implements Evaluation
{
    private double average;

    private final List<Course> courses = new ArrayList<>();

    private final Map<String, Course> approvedCourses = new HashMap<>();

    private final Map<String, Double> courseGrades = new HashMap<>();

    public Student( String id, String name, String email, Date birthDate )
    {
        super( id, name, email, birthDate );
    }

    public void enrollToCourse( Course course )
    {
        //TODO (DONE) implement this method
        //only if course DOES NOT EXISTS courses
        // then we add the course to `courses`
        if (!courses.contains(course)) {
            courses.add(course);
            registerApprovedCourse(course);
            this.average += (double) course.getCredits() /2;

        } else {
            System.out.printf("Student already enrolled to %s%n", course.getName());
        }
    }

    public String setGrade(String courseId, double score) {
        if(approvedCourses.containsKey(courseId)) {
            if(!courseGrades.containsKey(courseId)) {
                courseGrades.put(courseId, score);
                return String.format("Student's grade for course '%s' recorded as %s.%n", courseId, score);
            } else {
                return String.format("Student's grade for course '%s' has already been graded.%n", courseId);
            }
        } else {
            return String.format("Student has not successfully registered course %s.%n", courseId);
        }
    }

    public void registerApprovedCourse( Course course )
    {
        approvedCourses.put( course.getCode(), course );
    }

    public boolean isCourseApproved( String courseCode )
    {
        //TODO (DONE) implement this method
        return approvedCourses.containsKey(courseCode);
    }

    // CHALLENGE IMPLEMENTATION: Read README.md to find instructions on how to solve.
    public List<Course> findPassedCourses( )
    {
        //TODO (DONE) implement this method
        List<Course> passCourses = new ArrayList<>();

        courseGrades.forEach((courseId, score) -> {
            if (approvedCourses.containsKey(courseId)) {
                Course course = approvedCourses.get(courseId);
                    if (score>=(double)course.getCredits()/2) {
                        passCourses.add(course);
                    }
            }
        });

        return passCourses;
    }

    public boolean isAttendingCourse( String courseCode )
    {
        //TODO (DONE) implement this method
        return approvedCourses.containsKey(courseCode);
    }

    @Override
    public double getAverage()
    {
        return average;
    }

    @Override
    public List<Course> getApprovedCourses()
    {
        //TODO (DONE) implement this method
        //return courses that the student is taking
        List<Course> listedCourses = new ArrayList<>();

        if(!approvedCourses.isEmpty()){
            approvedCourses.forEach((courseId, course)->{
                listedCourses.add(course);
            });
        }

        return listedCourses;
    }

    @Override
    public String toString()
    {
        return "Student {" + super.toString() + "}";
    }
}