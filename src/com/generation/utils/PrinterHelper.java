package com.generation.utils;

import com.generation.model.Student;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PrinterHelper
{

    public static void showMainMenu(){
        System.out.println( "|---------------------------------|" );
        System.out.println( "| Welcome to StudentGen           |" );
        System.out.println( "|---------------------------------|" );
        System.out.println( "| Select 1 option:                |" );
        System.out.println( "| . 1 Register Student            |" );
        System.out.println( "| . 2 Find Student                |" );
        System.out.println( "| . 3 Grade Student               |" );
        System.out.println( "| . 4 Check Student Course Status |" );
        System.out.println( "| . 5 Check Student's Subscription|" );
        System.out.println( "| . 6 Enroll Student to Course    |" );
        System.out.println( "| . 7 Show Students Summary       |" );
        System.out.println( "| . 8 Show Courses Summary        |" );
        System.out.println( "| . 9 Show Student Course Passes  |" );
        System.out.println( "| . 10 Exit                       |" );
        System.out.println( "|---------------------------------|" );
    }

    public static Student createStudentMenu( Scanner scanner )
        throws ParseException
    {
        System.out.println( "|-------------------------------------|" );
        System.out.println( "| . 1 Register Student                |" );
        System.out.println( "|-------------------------------------|" );
        System.out.println( "| Enter student name:                 |" );
        String name = scanner.next();
        System.out.println( "| Enter student ID:                   |" );
        String id = scanner.next();
        System.out.println( "| Enter student email:                |" );
        String email = scanner.next();

        //TODO (done) validate date format and catch exception to avoid crash
        DateFormat formatter = new SimpleDateFormat( "MM/dd/yyyy");
        Date birthDate = null;
        boolean isValidDate = false;
        scanner.nextLine(); // buffer residue ("\n") from previous entries needs to be cleared.

        do {
            System.out.println("| Enter student birth date (MM/dd/yyyy) |");
            String inputDate = scanner.nextLine();

            try {
                birthDate = formatter.parse(inputDate);
                System.out.println( "|-------------------------------------|" );
                isValidDate = true;
            } catch (ParseException e) {
                System.out.println("Wrong date format! Use MM/dd/yyyy");
            }
        } while (!isValidDate);

        Student student = new Student( id, name, email, birthDate );
        System.out.println( "Student Successfully Registered! " );
        System.out.println(student);
        return student;
    }

}
