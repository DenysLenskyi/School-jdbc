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

    private static int numGroups = 10;
    private static int numStudents = 200;

    @Override
    public void execute(CommandHolder ch) {
        List<Course> courses = RandomDataCreator.getCoursesFromResources();
        CourseDao.getCourseDao().addCourses(courses);
        List<Group> groups = RandomDataCreator.generateGroups(numGroups);
        GroupDao.getGroupDao().addGroups(groups);
        List<Student> students = RandomDataCreator.generateStudents(numStudents);
        StudentDao.getStudentDao().addStudents(students);
        List<StudentCourse> studentCourses = RandomDataCreator.generateStudentCourseRelation(numStudents);
        StudentCourseDao.getStudentCourseDao().addStudentCourses(studentCourses);
    }
}