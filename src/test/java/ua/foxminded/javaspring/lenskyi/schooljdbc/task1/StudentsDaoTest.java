package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.StudentsDao;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentsDaoTest {

    @Test
    void createScriptTest() {
        StudentsDao test = new StudentsDao();
        assertEquals(true, test.createSqlScriptToPopulateStudentsTable().length() > 1000);
    }
}
