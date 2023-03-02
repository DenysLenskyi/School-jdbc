package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

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

    }
