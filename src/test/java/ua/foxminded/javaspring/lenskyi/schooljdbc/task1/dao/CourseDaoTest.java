package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.Command;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.CommandHolder;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.CreateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.PopulateTablesCommand;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDaoTest {

    static {
        initiateDatabase();
        populateDatabase();
    }

    private static void initiateDatabase() {
        Command createNewTables = new CreateTablesCommand();
        createNewTables.execute(new CommandHolder());
    }

    private static void populateDatabase() {
        Command populateTables = new PopulateTablesCommand();
        populateTables.execute(new CommandHolder());
    }

    @Test
    public void findCourseById0Test() {
        Course test = CourseDao.getCourseDao().findCourseById(0);
        assertEquals(0, test.getId());
        assertEquals(null, test.getName());
        assertEquals(null, test.getDescription());
    }

    @Test
    public void findCourseByIdNoMatchTest() {
        Course test = CourseDao.getCourseDao().findCourseById(153);
        assertEquals(0, test.getId());
        assertEquals(null, test.getName());
        assertEquals(null, test.getDescription());
    }
}