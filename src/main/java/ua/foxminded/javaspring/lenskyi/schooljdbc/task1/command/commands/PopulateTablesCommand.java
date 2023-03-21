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

    @Override
    public void execute(CommandHolder ch) {
        List<Course> courses = RandomDataCreator.getCoursesFromResources();
        CourseDao.getCourseDao().addCourses(courses);
        List<Group> groups = RandomDataCreator.generateGroups(10);
        GroupDao.getGroupDao().addGroups(groups);
        List<Student> students = RandomDataCreator.generateStudents(200);
        StudentDao.getStudentDao().addStudents(students);
        List<StudentCourse> studentCourses = RandomDataCreator.generateStudentCourseRelation(200);
        StudentCourseDao.getStudentCourseDao().addStudentCourses(studentCourses);
    }
}