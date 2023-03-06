package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.ConnectionManager;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StringConstant;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class TablePopulate {

    private static Random rand;

    static {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    FileReader reader = new FileReader();
    private static final String SQL_POPULATE_TABLE_COURSES = "/populate-table-courses.sql";
    private static final String SQL_INSERT_INTO_GROUP_TABLE = "INSERT INTO public.group (id, name)";
    private static final String SQL_INSERT_INTO_STUDENT_COURSE_TABLE =
            "INSERT INTO student_course (STUDENT_ID, COURSE_ID)";
    private final String SQL_INSERT_INTO_STUDENT_TABLE =
            "INSERT INTO student (id, group_id, first_name, last_name)";

    public void populateTables() {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(reader.readFile(SQL_POPULATE_TABLE_COURSES));
            statement.execute(getSqlScriptToPopulateGroupTable());
            statement.execute(getSqlScriptToPopulateStudentTable());
            statement.execute(getSqlScriptToPopulateStudentCourseTable());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Tables populated");
    }

    public String getSqlScriptToPopulateGroupTable() {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_GROUP_TABLE)
                .append(StringConstant.NEWLINE)
                .append(StringConstant.VALUES)
                .append(StringConstant.NEWLINE);
        for (int i = 1; i <= 10; i++) {
            script.append(StringConstant.OPEN_BRACKET)
                    .append(i)
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

    public String getRandomCharactersUpperCase(int n) {
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

    public String getSqlScriptToPopulateStudentTable() {
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

    public String getSqlScriptToPopulateStudentCourseTable() {
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
}