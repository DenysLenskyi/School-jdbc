package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class InfoCommand implements Command {

    public static final String INFO = """
            info - prints available commands and how to use them
            findcourse_<int> - example: findcourse_7 - prints course's info by course id number (1-10)
            findgroup_<int> - prints groups with less or equal student's number (max 30)
            students_<Course name> - example: students_History - prints students enrolled to course
            addstudent_<group id>_<First name>_<Second name> - group id should be 0-10
            deletestudent_<int> - deletes student by student id
            addstudenttocourse_<student id>_<Course name> - example: addstudenttocourse_1_History
            deletestudentfromcourse_<student id>_<Course name> - example: deletestudentfromcourse_1_History
            """;

    @Override
    public void execute() {
        System.out.println(INFO);
    }
}
