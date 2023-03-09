package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain;

public class Course {
    public int courseId;
    public String courseName;
    public String courseDescription;

    private static final String COURSE_ID = "Course ID: ";
    private static final String COURSE_NAME = "Course name: ";
    private static final String COURSE_DESCRIPTION = "Description: ";

    private static final String VERTICAL_BAR = " | ";
    private static final String NEWLINE = "\n";

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        return output.append(COURSE_ID)
                .append(courseId)
                .append(VERTICAL_BAR)
                .append(COURSE_NAME)
                .append(courseName)
                .append(VERTICAL_BAR)
                .append(COURSE_DESCRIPTION)
                .append(courseDescription)
                .append(NEWLINE)
                .toString();
    }
}
