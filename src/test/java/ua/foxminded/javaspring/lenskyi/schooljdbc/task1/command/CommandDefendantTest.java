package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.commands.UnknownCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandDefendantTest {
    private CommandDefendant commandDefendant = new CommandDefendant();

    @ParameterizedTest
    @ValueSource(strings = {"info", "find_course", "find_groups", "find_students_course", "add_student",
            "delete_student", "add_student_course", "delete_student_course"})
    void getCommandCodeTest(String string) {
        assertEquals(CommandDefendant.getCommandCode().get(string), commandDefendant.getCommandByCode(string));
    }
}
