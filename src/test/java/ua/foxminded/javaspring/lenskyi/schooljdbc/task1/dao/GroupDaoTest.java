package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupDaoTest {

    @ParameterizedTest
    @MethodSource ("provideArgumentsForTestMethod")
    void getGroupWithLessOrEqualAmountOfStudentsTest(int numStudents, List<Group> expectedGroups) {
        List<Group> groups = GroupDao.getGroupDao().getGroupWithLessOrEqualAmountOfStudents(numStudents);
        assertEquals(expectedGroups.size(), groups.size());
        for (int i = 0; i < expectedGroups.size() - 1; i++) {
            assertEquals(groups.get(i).getName(), expectedGroups.get(i).getName());
        }
    }

    private static Stream<Arguments> provideArgumentsForTestMethod() {
        Group firstTestGroup = new Group();
        firstTestGroup.setId(1);
        firstTestGroup.setName("AA-13");
        Group secondTestGroup = new Group();
        secondTestGroup.setId(2);
        secondTestGroup.setName("BB-50");
        List<Group> empty = new ArrayList<>();
        List<Group> oneGroup = new ArrayList<>();
        oneGroup.add(firstTestGroup);
        List<Group> twoGroup = new ArrayList<>();
        twoGroup.add(firstTestGroup);
        twoGroup.add(secondTestGroup);
        return Stream.of(
                Arguments.of(7, twoGroup),
                Arguments.of(1, oneGroup),
                Arguments.of(0, empty),
                Arguments.of(152, twoGroup)
        );
    }

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