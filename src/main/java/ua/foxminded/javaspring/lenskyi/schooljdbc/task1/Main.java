package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.CoursesDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.GroupsDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentsCoursesDao;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentsDao;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static final String EXIT = "exit";
    public static final String UNDERSCORE = "_";

    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        runApp(input);
        input.close();
    }
    public static void runApp(Scanner input) throws SQLException {
        CoursesDao coursesTable = new CoursesDao();
        GroupsDao groupsTable = new GroupsDao();
        StudentsDao studentsTable = new StudentsDao();
        StudentsCoursesDao studentsCoursesTable = new StudentsCoursesDao();
        coursesTable.createTable();
        coursesTable.populateCoursesTable();
        groupsTable.createTable();
        groupsTable.populateTable();
        studentsTable.createTable();
        studentsTable.populateTable();
        studentsCoursesTable.createTable();
        studentsCoursesTable.populateTable();
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
}
