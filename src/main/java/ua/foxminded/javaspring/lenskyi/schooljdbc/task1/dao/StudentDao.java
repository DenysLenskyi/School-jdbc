package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.*;

public class StudentDao {

    ConnectionManager connectionManager = new ConnectionManager();
    private static final String GROUP_ID = "Group ID: ";
    private static final String GROUP_NAME = "Group name: ";
    private static final String NUM_STUDENTS = "Students in group: ";
    private static final String SQL_GROUPS_WITH_LESS_OR_EQUAL_NUM_STUDENTS = """
            select group_id, public.group.name group_name, count(student.id)
                from public.student
                inner join public.group on group_id = public.group.id
                group by name, group_id
                having count(student.id) <= 
                """;
    private static final String SQL_ADD_NEW_STUDENT = "insert into public.student values (default, ";
    private static final String SQL_DELETE_STUDENT_BY_ID = "delete from public.student where id = ";

    public String getGroupWithLessOrEqualAmountOfStudents(int numStudents) {
        StringBuilder output = new StringBuilder();
        StringBuilder script = new StringBuilder();
        script.append(SQL_GROUPS_WITH_LESS_OR_EQUAL_NUM_STUDENTS)
                .append(numStudents)
                .append(" order by group_id;");
        output.append("Groups with less or equal than ")
                .append(numStudents)
                .append(" students")
                .append(StringConstant.NEWLINE);
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(script.toString());
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                output.append(GROUP_ID)
                        .append(rs.getInt(1))
                        .append(StringConstant.VERTICAL_BAR)
                        .append(StringConstant.WHITESPACE)
                        .append(GROUP_NAME)
                        .append(rs.getString(2))
                        .append(StringConstant.VERTICAL_BAR)
                        .append(StringConstant.WHITESPACE)
                        .append(NUM_STUDENTS)
                        .append(rs.getInt(3))
                        .append(StringConstant.VERTICAL_BAR)
                        .append(StringConstant.NEWLINE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void addNewStudent(int groupId, String firstName, String lastName) {
        if (groupId <= 10 && groupId >= 0) {
            StringBuilder script = new StringBuilder();
            script.append(SQL_ADD_NEW_STUDENT);
            if (groupId == 0) {
                script.append(StringConstant.NULL);
            } else {
                script.append(groupId);
            }
            script.append(StringConstant.COMA)
                    .append(StringConstant.WHITESPACE)
                    .append(StringConstant.QUOTE)
                    .append(firstName)
                    .append(StringConstant.QUOTE)
                    .append(StringConstant.COMA)
                    .append(StringConstant.WHITESPACE)
                    .append(StringConstant.QUOTE)
                    .append(lastName)
                    .append(StringConstant.QUOTE)
                    .append(StringConstant.CLOSE_BRACKET)
                    .append(StringConstant.SEMICOLON);
            try (Connection connection = connectionManager.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.execute(script.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Student added");
        } else {
            System.out.println("Student hasn't been added!\nGroup ID should be a number in range from 0 to 10");
        }
    }

    public void deleteStudentById(int studentId) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_DELETE_STUDENT_BY_ID)
                .append(studentId)
                .append(StringConstant.SEMICOLON);
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student deleted");
    }
}