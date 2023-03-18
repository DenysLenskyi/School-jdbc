package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.*;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.StudentCourse;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.RandomDataCreator;

import java.util.List;

public class PopulateTablesCommand implements Command {

    CourseDao courseTable = new CourseDao();
    GroupDao groupsTable = new GroupDao();
    StudentDao studentTable = new StudentDao();
    StudentCourseDao studentCourseTable = new StudentCourseDao();

    @Override
    public void execute(CommandHolder ch) {
        List<Course> courses = RandomDataCreator.getCoursesFromResources();
        courseTable.addCourses(courses);
        List<Group> groups = RandomDataCreator.generateGroups(10);
        groupsTable.addGroups(groups);
        List<Student> students = RandomDataCreator.generateStudents(200);
        studentTable.addStudents(students);
        List<StudentCourse> studentCourses = RandomDataCreator.generateStudentCourseRelation(200);
        studentCourseTable.addStudentCourses(studentCourses);
    }
}