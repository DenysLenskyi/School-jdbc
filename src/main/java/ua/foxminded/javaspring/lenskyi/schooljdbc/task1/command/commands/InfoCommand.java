package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;

public class InfoCommand implements Command {

    public static final String INFO = """
            info - prints available commands and how to use them
            exit - exits the app
            find_course --course_id= - prints course's info by course id number (1-10)
            find_groups --num_students= - prints groups with less or equal student's number (max 30)
            find_students_course --course_name= - example: --course_name=History - prints students enrolled to course
            add_student --group_id= --first_name= --last_name= - group id should be 0-10
            delete_student --student_id= - deletes student by student id
            add_student_course --student_id= --course_name= - adds student to course
            delete_student_course --student_id= --course_name= - deletes student from course
            """;

    @Override
    public void execute(CommandHolder commandHolder) {
        System.out.println(INFO);
    }
}