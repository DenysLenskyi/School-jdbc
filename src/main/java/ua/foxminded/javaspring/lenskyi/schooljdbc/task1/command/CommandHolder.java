package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class CommandHolder {

    String commandName;
    int courseId;
    int numStudents;
    String courseName;
    int groupId;
    String studentFirstName;
    String studentLastName;
    int studentId;

    public String getCommandName() {
        return commandName;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public int getStudentId() {
        return studentId;
    }
}