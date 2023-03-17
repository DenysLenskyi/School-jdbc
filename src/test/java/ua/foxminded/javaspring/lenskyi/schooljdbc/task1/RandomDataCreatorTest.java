package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import org.junit.jupiter.api.Test;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils.RandomDataCreator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RandomDataCreatorTest {

    @Test
    public void generateGroupsListSizeTest() {
        List<Group> groups = RandomDataCreator.generateGroups(10);
        assertEquals(10, groups.size());
    }

    @Test
    public void generateGroupsIdsTest() {
        List<Group> groups = RandomDataCreator.generateGroups(10);
        assertEquals(1, groups.get(0).getGroupId());
        assertEquals(2, groups.get(1).getGroupId());
        assertEquals(3, groups.get(2).getGroupId());
    }

    @Test
    public void generateGroupsNamesLengthTest() {
        List<Group> groups = RandomDataCreator.generateGroups(10);
        assertEquals(5, groups.get(0).getGroupName().length());
    }
}
