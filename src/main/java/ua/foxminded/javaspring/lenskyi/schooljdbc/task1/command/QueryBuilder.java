package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class QueryBuilder {

    static FileReader reader = new FileReader();
    private static Random rand;

    static {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static final String TABLES_INITIATION_SCRIPT_FILE_NAME = "/initiate-tables.sql";
    private static final String COURSE_TABLE_POPULATION_SCRIPT_FILE_NAME = "/populate-table-course.sql";
    private static final String SQL_INSERT_INTO_GROUP_TABLE =
            "TRUNCATE TABLE public.group CASCADE; INSERT INTO public.group (id, name)";
    private static final String SQL_INSERT_INTO_STUDENT_COURSE_TABLE =
            "TRUNCATE TABLE student_course CASCADE; INSERT INTO student_course (STUDENT_ID, COURSE_ID)";
    private static final String SQL_INSERT_INTO_STUDENT_TABLE =
            "TRUNCATE TABLE student CASCADE; INSERT INTO student (id, group_id, first_name, last_name)";

    private static final String SQL_FIND_COURSE_BY_ID = "SELECT ID, NAME, DESCRIPTION FROM public.course WHERE ID = ";

    private static final String SQL_GROUPS_WITH_LESS_OR_EQUAL_NUM_STUDENTS = """
            select group_id, public.group.name group_name, count(student.id)
                from public.student
                inner join public.group on group_id = public.group.id
                group by name, group_id
                having count(student.id) <= 
                """;
    private static final String ORDER_BY_GROUP_ID = " order by group_id;";
    private static final String SQL_FIND_STUDENTS_ENROLLED_TO_COURSE = """
            select distinct s.id, first_name, last_name
                from public.student_course s_c
                inner join public.course c on course_id = c.id
                inner join public.student s on s_c.student_id = s.id
                where c.name = 
            """;


    public String getInitiateTablesQuery() {
        return reader.readFile(TABLES_INITIATION_SCRIPT_FILE_NAME);
    }

    public String getPopulateTablesQuery() {
        StringBuilder script = new StringBuilder();
        return script.append(getPopulateCourseTableQuery())
                .append(StringConstant.NEWLINE)
                .append(getPopulateGroupTableQuery())
                .append(StringConstant.NEWLINE)
                .append(getPopulateStudentTableQuery())
                .append(StringConstant.NEWLINE)
                .append(getPopulateStudentCourseTableQuery())
                .toString();
    }

    public String getPopulateCourseTableQuery() {
        return reader.readFile(COURSE_TABLE_POPULATION_SCRIPT_FILE_NAME);
    }

    public String getPopulateGroupTableQuery() {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_GROUP_TABLE)
                .append(StringConstant.NEWLINE)
                .append(StringConstant.VALUES)
                .append(StringConstant.NEWLINE);
        for (int i = 1; i <= 10; i++) {
            script.append(StringConstant.OPEN_BRACKET)
                    .append(StringConstant.DEFAULT)
                    .append(StringConstant.COMA)
                    .append(StringConstant.WHITESPACE)
                    .append(StringConstant.QUOTE)
                    .append(getRandomCharactersUpperCase(2))
                    .append(StringConstant.HYPHEN)
                    .append(rand.nextInt(10, 100))
                    .append(StringConstant.QUOTE)
                    .append(StringConstant.CLOSE_BRACKET)
                    .append(StringConstant.COMA)
                    .append(StringConstant.NEWLINE);
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(StringConstant.SEMICOLON)
                .toString();
    }

    private static String getRandomCharactersUpperCase(int n) {
        byte[] array = new byte[256];
        rand.nextBytes(array);
        String randomString = new String(array, StandardCharsets.UTF_8);
        StringBuilder r = new StringBuilder();
        for (int k = 0; k < randomString.length(); k++) {
            char ch = randomString.charAt(k);
            if ((ch >= 'A' && ch <= 'Z') && (n > 0)) {
                r.append(ch);
                n--;
            }
        }
        return r.toString();
    }

    public String getPopulateStudentTableQuery() {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_STUDENT_TABLE)
                .append(StringConstant.NEWLINE)
                .append(StringConstant.VALUES)
                .append(StringConstant.NEWLINE);
        String[] names = reader.readFile(StringConstant.NAMES_TXT).split(StringConstant.SEMICOLON);
        int totalNumStudentsAssignedToGroups = 0;
        int groupId = 0;
        while (totalNumStudentsAssignedToGroups <= 200) {
            groupId++;
            int randomNumStudentsForGroup = rand.nextInt(10, 31);
            totalNumStudentsAssignedToGroups += randomNumStudentsForGroup;
            if (totalNumStudentsAssignedToGroups > 200) {
                int lastStudentGroup = 200 - (totalNumStudentsAssignedToGroups - randomNumStudentsForGroup);
                randomNumStudentsForGroup = lastStudentGroup;
            }
            for (int i = 0; i < randomNumStudentsForGroup; i++) {
                script.append(StringConstant.OPEN_BRACKET)
                        .append(StringConstant.DEFAULT)
                        .append(StringConstant.COMA)
                        .append(StringConstant.WHITESPACE);
                if (groupId > 10) {
                    script.append(StringConstant.NULL);
                } else {
                    script.append(groupId);
                }
                script.append(StringConstant.COMA)
                        .append(StringConstant.WHITESPACE)
                        .append(StringConstant.QUOTE)
                        .append(names[rand.nextInt(40)])
                        .append(StringConstant.QUOTE)
                        .append(StringConstant.COMA)
                        .append(StringConstant.WHITESPACE)
                        .append(StringConstant.QUOTE)
                        .append(names[rand.nextInt(40)])
                        .append(StringConstant.SON)
                        .append(StringConstant.QUOTE)
                        .append(StringConstant.CLOSE_BRACKET)
                        .append(StringConstant.COMA)
                        .append(StringConstant.NEWLINE);
            }
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(StringConstant.SEMICOLON)
                .toString();
    }

    public String getPopulateStudentCourseTableQuery() {
        StringBuilder script = new StringBuilder(); //this script could enroll one student to one course 3 times
        script.append(SQL_INSERT_INTO_STUDENT_COURSE_TABLE)
                .append(StringConstant.NEWLINE)
                .append(StringConstant.VALUES)
                .append(StringConstant.NEWLINE);
        for (int i = 1; i <= 200; i++) {
            int amountOfCourses = rand.nextInt(1, 4);
            while (amountOfCourses > 0) {
                script.append(StringConstant.OPEN_BRACKET)
                        .append(i)
                        .append(StringConstant.COMA)
                        .append(StringConstant.WHITESPACE)
                        .append(rand.nextInt(1, 11))
                        .append(StringConstant.CLOSE_BRACKET)
                        .append(StringConstant.COMA)
                        .append(StringConstant.NEWLINE);
                amountOfCourses--;
            }
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(StringConstant.SEMICOLON)
                .toString();
    }

    public String getFindCourseByIdScript(int courseId) {
        StringBuilder script = new StringBuilder();
        return script.append(SQL_FIND_COURSE_BY_ID)
                .append(courseId)
                .append(StringConstant.SEMICOLON)
                .toString();
    }

    public String getFindGroupsWithNumStudentsScript(int numStudents) {
        StringBuilder script = new StringBuilder();
        return script.append(SQL_GROUPS_WITH_LESS_OR_EQUAL_NUM_STUDENTS)
                .append(numStudents)
                .append(ORDER_BY_GROUP_ID)
                .toString();
    }

    public String getFindStudentsEnrolledToCourseScript(String courseName) {
        StringBuilder script = new StringBuilder();
        return script.append(SQL_FIND_STUDENTS_ENROLLED_TO_COURSE)
                .append(StringConstant.QUOTE)
                .append(courseName)
                .append(StringConstant.QUOTE)
                .append(StringConstant.SEMICOLON)
                .toString();
    }
}