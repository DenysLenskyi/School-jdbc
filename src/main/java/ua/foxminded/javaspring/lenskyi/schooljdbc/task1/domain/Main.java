package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        DatabaseProperties properties = new DatabaseProperties();
        CourseDao coursesTable = new CourseDao();
        StudentCourseDao studentCourseTable = new StudentCourseDao();
        TableCreate tableCreate = new TableCreate();
        TablePopulate tablePopulate = new TablePopulate();
        tableCreate.createNewTables();
        tablePopulate.populateTables();
        System.out.println(coursesTable.findCourseById("7"));
        studentCourseTable.addStudentToCourse(2, "History");
        studentCourseTable.removeStudentFromCourse(2, "History");
        System.out.println(studentCourseTable.getStudentsEnrolledToCourse("History"));
        //Scanner input = new Scanner(System.in);
        // Main main = new Main();
        //main.runApp(input);
        // input.close();
    }

    /*
    public void runApp(Scanner input) throws SQLException {
        System.out.println(studentsTable.getGroupWithAmountOfStudents(15));
        System.out.println(studentsCoursesTable.getStudentsEnrolledToCourse("History"));
        studentsTable.addNewStudent(10,"Hello", "World");
        studentsTable.deleteStudentById(1);
        studentsCoursesTable.addStudentToCourse(2, "History");
        studentsCoursesTable.removeStudentFromCourse(9, "Math");
        System.out.println("Available commands:");
        System.out.println(EXIT + " - quit APP");
        System.out.println("find_course_<id> - prints course by <id>");
        System.out.println("Type command:");
        String inputText = input.nextLine();
        if (inputText.equals(EXIT)) {
            System.exit(0);
        } else if (inputText.substring(0,11).equals("find_course")) {
            coursesTable.findById(getIdFromString(inputText));
            runApp(input);
        } else {
            System.out.println("Unknown command...");
            runApp(input);
        }


    }

    private static String getIdFromString(String str) {
        String[] command = str.split(UNDERSCORE);
        return command[2];
    }

     */
}
