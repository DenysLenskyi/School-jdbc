package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.TestDBPreparation;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseDaoTest {

    @BeforeAll
    static void setup() {
        TestDBPreparation.setupDB();
    }

    @ParameterizedTest
    @MethodSource("provideIdsForFindCourseById")
    void findCourseByIdTest(int courseId, String expectedName, String expectedDescr) {
        Course test = CourseDao.getCourseDao().findCourseById(courseId);
        assertEquals(expectedName, test.getName());
        assertEquals(expectedDescr, test.getDescription());
    }

    private static Stream<Arguments> provideIdsForFindCourseById() {
        return Stream.of(
                Arguments.of(1, "Math", "Math"),
                Arguments.of(2, "Bio", "Bio"),
                Arguments.of(3, "Sport", "Sport"),
                Arguments.of(0, null, null)
        );
    }
}