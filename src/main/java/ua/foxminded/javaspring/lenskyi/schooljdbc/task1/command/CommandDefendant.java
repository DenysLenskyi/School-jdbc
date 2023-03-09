package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command;

public class CommandDefendant {

    public static final String INITIATE_TABLES = "100";
    public static final String POPULATE_TABLES = "110";

    public Command findCommand(String input) {
        if (input.equals(INITIATE_TABLES)) {
            return new CreateTablesCommand();
        } else if(input.equals(POPULATE_TABLES)) {
            return new PopulateTablesCommand();
        } else {
            System.out.println("No command found...");
            return null;
        }
    }
}
