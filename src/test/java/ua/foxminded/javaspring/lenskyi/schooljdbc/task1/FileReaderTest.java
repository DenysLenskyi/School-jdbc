package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FileReaderTest {
    @Test
    void fileReaderPlainTest() {
        FileReader reader = new FileReader();
        assertEquals("DROP TABLE IF EXISTS courses \n" +
                "CREATE TABLE IF NOT EXISTS courses \n" +
                "(ID INT PRIMARY KEY , \n" +
                " NAME TEXT, \n" +
                " DESCRIPTION TEXT) \n", reader.readFileLines("/test.txt"));
    }
}
