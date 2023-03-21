package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private static GroupDao groupDao = new GroupDao();

    private static final String ADD_GROUPS_QUERY = "INSERT INTO school.group (name) VALUES (?)";
    private static final String FIND_GROUPS_WITH_LESS_OR_EQUAL_NUM_STUDENTS_QUERY = """
            select group_id, school.group.name group_name, count(student.id)
                from school.student
                inner join school.group on group_id = school.group.id
                group by name, group_id
                having count(student.id) <=?
                """;

    private GroupDao() {
    }

    public static GroupDao getGroupDao() {
        return groupDao;
    }

    public void addGroups(List<Group> groups) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_GROUPS_QUERY)) {
            connection.setAutoCommit(false);
            for (Group group : groups) {
                statement.setString(1, group.getName());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Group> getGroupWithLessOrEqualAmountOfStudents(int numStudents) {
        List<Group> output = new ArrayList<>();
        ResultSet rs = null;
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement(FIND_GROUPS_WITH_LESS_OR_EQUAL_NUM_STUDENTS_QUERY)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, numStudents);
            rs = preparedStatement.executeQuery();
            connection.commit();
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt(1));
                group.setName(rs.getString(2));
                output.add(group);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return output;
    }
}