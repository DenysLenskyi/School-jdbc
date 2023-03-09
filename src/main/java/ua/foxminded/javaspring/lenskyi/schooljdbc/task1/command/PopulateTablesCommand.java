package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.TablePopulateDao;

public class PopulateTablesCommand implements Command {

    TablePopulateDao tables = new TablePopulateDao();
    static QueryBuilder queryBuilder = new QueryBuilder();
    public static final String SCRIPT_POPULATE_COURSE_TABLE = queryBuilder.getPopulateCourseTableQuery();
    public static final String SCRIPT_POPULATE_GROUP_TABLE = queryBuilder.getPopulateGroupTableQuery();
    public static final String SCRIPT_POPULATE_STUDENT_TABLE = queryBuilder.getPopulateStudentTableQuery();
    public static final String SCRIPT_POPULATE_STUDENT_COURSE_TABLE = queryBuilder.getPopulateStudentCourseTableQuery();

    @Override
    public void execute() {
        tables.populateTableCourse(SCRIPT_POPULATE_COURSE_TABLE)
                .populateTableGroup(SCRIPT_POPULATE_GROUP_TABLE)
                .populateTableStudent(SCRIPT_POPULATE_STUDENT_TABLE)
                .populateTableStudentCourse(SCRIPT_POPULATE_STUDENT_COURSE_TABLE);
    }
}