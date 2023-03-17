package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryBuilderTest {

    @Test
    void addStudentToCourseTest() {
        CommandHolder test = CommandHolderBuilder
                .buildCommandFromInputString("add_student_course --student_id=184 --course_name=Math");
        assertEquals("add_student_course", test.commandName);
        assertEquals(184, test.studentId);
        assertEquals("Math", test.courseName);
        QueryBuilder queryBuilder = new QueryBuilder();
        String expected = """
                insert into public.student_course (student_id, course_id)
                select
                184, c.id
                from public.course c
                where c.name =
                'Math';""";
        assertEquals(expected, queryBuilder.getAddStudentToCourseScript(test.studentId, test.courseName));
    }
}
