package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.utils;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Group;

import java.util.ArrayList;
import java.util.List;

public class RandomDataCreator {

    public static List<Group> generateGroups(int numGroups) {
        List<Group> groups = new ArrayList<>();
        for (int i = 1; i <= numGroups; i++) {
            Group group = new Group();
        }
        return groups;
    }
}
