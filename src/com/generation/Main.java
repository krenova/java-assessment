package com.generation;

import com.generation.model.Course;
import com.generation.model.Student;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.*;

public class Main
{

    public static void main( String[] args )
        throws ParseException
    {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner( System.in );

        //FIXME (DONE) ----------------------------------------------
        // - Temporary pre-population of students to student courses
        //   for simulation and testing purposes
//        String[] _studentIds = {"001","002","003"};
//
//        // To generate distinct course numbers
//        List<Integer> numbers = new ArrayList<>(Set.of(1, 2, 3, 4, 5,6,7));
//        Collections.shuffle(numbers);
//        List<Integer> sample = numbers.subList(0, 3);
//        String[] _courseIds = {
//                String.format("INTRO-CS-%d", sample.get(0)),
//                String.format("INTRO-CS-%d", sample.get(1)),
//                String.format("INTRO-CS-%d", sample.get(2))
//        };
//
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println("Student '" + _studentIds[i] + "' enrolled in '" + _courseIds[i] + "'.");
//            }
//        }
//
//        for (int i = 0; i < 3; i++) {
//
//            String _studentId = _studentIds[i];
//
//            for (int j = 0; j < 3; j++) {
//
//                String _courseId = _courseIds[j];
//
//                Student _student = studentService.findStudent( _studentId );
//                Course _course = courseService.getCourse( _courseId );
//
//                courseService.enrollStudent( _courseId, _student );
//                studentService.enrollToCourse( _studentId, _course );
//            }
//        }
        //FIXME (DONE) ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^


        int option = 0;
        do
        {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch ( option )
            {
                case 1:
                    registerStudent( studentService, scanner );
                    break;
                case 2:
                    findStudent( studentService, scanner );
                    break;
                case 3:
                    gradeStudent( studentService, scanner );
                    break;
                case 4:
                    checkStudentCourseStatus( studentService, courseService, scanner );
                    break;
                case 5:
                    checkStudentSubscription( studentService, scanner );
                    break;
                case 6:
                    enrollStudentToCourse( studentService, courseService, scanner );
                    break;
                case 7:
                    showStudentsSummary( studentService, scanner );
                    break;
                case 8:
                    showCoursesSummary( courseService, scanner );
                    break;
                case 9:
                    showPassedCourses( studentService, scanner);
                    break;
            }
        }
        while ( option != 10 );
    }

    private static void checkStudentSubscription(StudentService studentService, Scanner scanner)
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        if (studentService.isSubscribed( studentId )) {
            System.out.printf("Student %s is SUBSCRIBED.%n",studentId);
        } else {
            System.out.printf("Student %s is NOT subscribed.%n",studentId);
        }
    }


    //TODO (DONE) - To test isCourseApproved() functionality
    private static void checkStudentCourseStatus(StudentService studentService, CourseService courseService,
                                                 Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        if(student.isCourseApproved(courseId)) {
            System.out.printf("Student %s's enrollment in '%s' is APPROVED.%n",studentId,courseId);
        } else {
            System.out.printf("Student %s's enrollment in '%s' has NOT been approved.%n",studentId,courseId);
        }

    }

    private static void enrollStudentToCourse( StudentService studentService, CourseService courseService,
                                               Scanner scanner )
    {
        System.out.println( "Insert student ID" );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student == null )
        {
            System.out.println( "Invalid Student ID" );
            return;
        }
        System.out.println( student );
        System.out.println( "Insert course ID" );
        String courseId = scanner.next();
        Course course = courseService.getCourse( courseId );
        if ( course == null )
        {
            System.out.println( "Invalid Course ID" );
            return;
        }
        System.out.println( course );
        courseService.enrollStudent( courseId, student );
        studentService.enrollToCourse( studentId, course );
        System.out.println( "Student with ID: " + studentId + " enrolled successfully to " + courseId );

    }

    private static void showPassedCourses(StudentService studentService, Scanner scanner)
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );

            //TODO (DONE) Show the student courses passed
            studentService.showPassedCourses(student);

        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void showCoursesSummary( CourseService courseService, Scanner scanner )
    {
        courseService.showSummary();
    }

    private static void showStudentsSummary( StudentService studentService, Scanner scanner )
    {
        studentService.showSummary();
    }

    private static void gradeStudent( StudentService studentService, Scanner scanner )
    {
        //TODO (DONE) - How do we grade the student
        // 1. ask for the student id first.
        // 2. find the student first
        // 3. ask for the course id next
        // 4. find whether the student is taking the course
        // 5. what is the grade to assign to the student
        // Our analogy is to find the averages of each module

        System.out.println("Please enter the student's ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);


        if ( student != null ) {
            System.out.printf("Please enter the course ID for student ID: %s%n", studentId);
            String courseId = scanner.next();
            boolean isAttendingCourse = student.isAttendingCourse(courseId);

            if (isAttendingCourse) {
                System.out.println("Please enter the student's score:");
                double score = scanner.nextDouble();
                if (score<0 || score>9)
                    System.out.println("Invalid score entry.");
                else
                    System.out.println(student.setGrade(courseId, score));
            } else {
                System.out.printf("The student (Student ID: %s) is not attending course ID: %s%n", studentId, courseId);
            }

        } else {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }


    }

    private static void findStudent( StudentService studentService, Scanner scanner )
    {
        System.out.println( "Enter student ID: " );
        String studentId = scanner.next();
        Student student = studentService.findStudent( studentId );
        if ( student != null )
        {
            System.out.println( "Student Found: " );
            System.out.println( student );
        }
        else
        {
            System.out.println( "Student with Id = " + studentId + " not found" );
        }
    }

    private static void registerStudent( StudentService studentService, Scanner scanner )
        throws ParseException
    {
        Student student = PrinterHelper.createStudentMenu( scanner );
        studentService.subscribeStudent( student );
    }
}
