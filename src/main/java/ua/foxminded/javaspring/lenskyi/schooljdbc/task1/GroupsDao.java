package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import java.nio.charset.Charset;
import java.sql.*;
import java.util.Random;

public class GroupsDao {

    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INIT_TABLE = "/initiate-table-groups.sql";
    public final String OPEN_BRACKET = "(";
    public final String CLOSE_BRACKET = ")";
    public final String COMA = ",";
    public final String WHITESPACE = " ";
    public final String QUOTE = "'";
    public final String HYPHEN = "-";
    public static final String NEWLINE = "\n";
    public static final String SEMICOLON = ";";
    public static final String VALUES = "VALUES";
    private final String INSERT_QUERY = "INSERT INTO school.groups (id, name)";
    FileReader reader = new FileReader();

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS school.groups");
            statement.execute(reader.readFile(INIT_TABLE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(createSqlScriptToPopulateGroupsTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createSqlScriptToPopulateGroupsTable() {
        StringBuilder script = new StringBuilder();
        script.append(INSERT_QUERY).append(NEWLINE).append(VALUES).append(NEWLINE);
        for (int i = 1; i <= 10; i++) {
            script.append(OPEN_BRACKET)
                    .append(i)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(getRandomCharactersUpperCase(2))
                    .append(HYPHEN)
                    .append(getRandomNumber())
                    .append(QUOTE)
                    .append(CLOSE_BRACKET)
                    .append(COMA)
                    .append(NEWLINE);
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(SEMICOLON)
                .toString();
    }

    public String getRandomCharactersUpperCase(int n) {
            byte[] array = new byte[256];
            new Random().nextBytes(array);
            String randomString = new String(array, Charset.forName("UTF-8"));
            StringBuffer r = new StringBuffer();
            for (int k = 0; k < randomString.length(); k++) {
                char ch = randomString.charAt(k);
                if ((ch >= 'A' && ch <= 'Z') && (n > 0)) {
                    r.append(ch);
                    n--;
                }
            }
            return r.toString();
        }
        public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(10, 100);
        }
    }
