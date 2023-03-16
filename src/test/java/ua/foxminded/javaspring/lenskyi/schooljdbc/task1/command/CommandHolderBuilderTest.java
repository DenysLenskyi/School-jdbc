package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandHolderBuilderTest {

    @Test
    void invokeInfoTest() {
        CommandHolder test = CommandHolderBuilder.buildCommandFromInputString("info");
        assertEquals("info", test.getCommandName());
    }

    @Test
    void invokeFindCourseTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("find_course --course_id=7");
        assertEquals(7, test.getCourseId());
        assertEquals("find_course", test.getCommandName());
    }

    @Test
    void invokeFindGroupTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("find_groups --num_students=20");
        assertEquals("find_groups", test.getCommandName());
        assertEquals(20, test.getNumStudents());
    }

    @Test
    void invokeStudentsFromCourseTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("find_students --course_name=History");
        assertEquals("find_students", test.getCommandName());
        assertEquals("History", test.getCourseName());
    }

    @Test
    void invokeAddStudentTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("add_student --group_id=10 --first_name=Alice --last_name=Cooper");
        assertEquals("add_student", test.getCommandName());
        assertEquals(10, test.getGroupId());
        assertEquals("Alice", test.getStudentFirstName());
        assertEquals("Cooper", test.getStudentLastName());
    }

    @Test
    void invokeDeleteStudentTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("delete_student --student_id=1");
        assertEquals("delete_student", test.getCommandName());
        assertEquals(1, test.getStudentId());
    }

    @Test
    void invokeAddStudentToCourseTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("add_student_course --student_id=1 --course_name=History");
        assertEquals("add_student_course", test.getCommandName());
        assertEquals(1, test.getStudentId());
        assertEquals("History", test.getCourseName());
    }

    @Test
    void invokeDeleteStudentFromCourseTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("delete_student_course --student_id=1 --course_name=History");
        assertEquals("delete_student_course", test.getCommandName());
        assertEquals(1, test.getStudentId());
        assertEquals("History", test.getCourseName());
    }
}