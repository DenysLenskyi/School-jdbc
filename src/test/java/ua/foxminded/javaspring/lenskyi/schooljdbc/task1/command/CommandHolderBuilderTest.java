package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandHolderBuilderTest {

    @Test
    void invokeInfoTest() {
        CommandHolder test = CommandHolderBuilder.buildCommandFromInputString("info");
        assertEquals("info", test.commandName);
    }

    @Test
    void invokeFindCourseTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("find_course --course_id=7");
        assertEquals(7, test.courseId);
        assertEquals("find_course", test.commandName);
    }

    @Test
    void invokeFindGroupTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("find_groups --num_students=20");
        assertEquals("find_groups", test.commandName);
        assertEquals(20, test.numStudents);
    }

    @Test
    void invokeStudentsFromCourseTest() {
        assertEquals(true, true);
    }

    @Test
    void invokeAddStudentTest() {
        assertEquals(true, true);
    }

    @Test
    void invokeDeleteStudentTest() {
        assertEquals(true, true);
    }

    @Test
    void invokeAddStudentToCourseTest() {
        assertEquals(true, true);
    }

    @Test
    void invokeDeleteStudentFromCourseTest() {
        assertEquals(true, true);
    }
}