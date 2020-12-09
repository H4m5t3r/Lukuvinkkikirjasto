package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.HashMap;

import lukuvinkkikirjasto.domain.ReadingTipService;

public class UserInterface {
    private String[] commandDescriptions = {
        "exit           - closes the application",
        "new            - add a new reading tip",
        "add book       - add a new book",
        "list           - lists reading tips by choice",
        "search         - search from tips by header or description",
        "edit header    - edit header of a tip",
        "edit desc      - edit description of a tip",
        "mark as read   - mark a reading tip as read",
        "mark as unread - mark a reading tip as unread",
        "delete         - delete a reading tip",
    };
    private IO io;
    private HashMap<String, Command> commands = new HashMap<String, Command>();
    private Command unknown;
    
    public UserInterface(IO io, ReadingTipService rtService) throws SQLException {
        this.io = io;
        commands.put("exit", new Exit(io, rtService));
        commands.put("new", new CreateReadingTip(io, rtService));
        commands.put("add book", new AddBook(io, rtService));
        commands.put("list", new ListReadingTips(io, rtService));
        commands.put("search", new Search(io, rtService));
        commands.put("edit header", new EditHeader(io, rtService));
        commands.put("edit desc", new EditDescription(io, rtService));
        commands.put("delete", new DeleteReadingTip(io, rtService));
        commands.put("mark as read", new MarkAsRead(io, rtService));
        commands.put("mark as unread", new MarkAsUnread(io, rtService));
        unknown = new Unknown(io, rtService);
    }

    public void start() {
        while (true) {
            printCommands();
            String input = io.input();
            chooseCommand(input).execute();
            io.output("");
        }
    }
    
    private void printCommands() {
        io.output("Here are the available commands:");
        for (String command : commandDescriptions) {
            io.output(command);
        }
    }
    
    public Command chooseCommand(String input) {
        return commands.getOrDefault(input, unknown);
    }
}
