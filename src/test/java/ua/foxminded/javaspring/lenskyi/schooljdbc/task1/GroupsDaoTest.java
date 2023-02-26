package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GroupsDaoTest {
    @Test
    void randomTwoCharsTest() {
        GroupsDao test = new GroupsDao();
        assertEquals(2, test.getRandomCharactersUpperCase(2).length());
    }

    @Test
    void randomTwoNumbersTest() {
        GroupsDao test = new GroupsDao();
        assertEquals(true, test.getRandomNumber() >= 10);
    }

    @Test
    void createScriptTest() {
        GroupsDao test = new GroupsDao();
        assertEquals(184, test.createSqlScriptToPopulateGroupsTable().length());
    }
}
