package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupDaoTest {

    @Test
    public void getGroupWithLessOrEqualThan7StudentsTest() {
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(7);
        assertEquals(2, groups.size());
        assertEquals("AA-13", groups.get(0).getName());
        assertEquals("BB-50", groups.get(1).getName());
    }

    @Test
    public void getGroupWithLessOrEqualThan1StudentTest() {
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(1);
        assertEquals(1, groups.size());
        assertEquals("BB-50", groups.get(0).getName());
    }

    @Test
    public void getGroupWithLessOrEqualThan0StudentsTest() {
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(0);
        assertEquals(0, groups.size());
    }

    @Test
    public void getGroupWithLessOrEqualThan152StudentsTest() {
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(152);
        assertEquals(2, groups.size());
        assertEquals("AA-13", groups.get(0).getName());
        assertEquals("BB-50", groups.get(1).getName());
    }
}
