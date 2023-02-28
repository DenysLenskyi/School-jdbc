package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.GroupsDao;

public class GroupsDaoTest {
    @Test
    void randomTwoCharsTest() {
        GroupsDao test = new GroupsDao();
        assertEquals(2, test.getRandomCharactersUpperCase(2).length());
    }

    @Test
    void createScriptTest() {
        GroupsDao test = new GroupsDao();
        assertEquals(184, test.createSqlScriptToPopulateGroupsTable().length());
    }
}
