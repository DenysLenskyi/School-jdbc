package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain.FileReader;

public class FileReaderTest {
    @Test
    void fileReaderPlainTest() {
        FileReader reader = new FileReader();
        assertEquals("CREATE TABLE IF NOT EXISTS school.courses " +
                        "(ID INT PRIMARY KEY, NAME TEXT, DESCRIPTION TEXT);",
                reader.readFile("/initiate-table-courses.sql"));
    }
}
