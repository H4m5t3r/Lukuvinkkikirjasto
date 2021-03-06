package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.HashMap;

import lukuvinkkikirjasto.domain.ReadingTipService;

public class UserInterface {
    private String[] commandDescriptions = {
        "exit           - close the application",
        "add general    - add a new general reading tip",
        "add book       - add a new book",
        "add podcast    - add a new podcast",
        "add blog       - add a new blog",
        "add video      - add a new video",
        "list           - list reading tips by choice",
        "search         - search from tips by header or description",
        "edit           - edit a tip",
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
        commands.put("add general", new CreateReadingTip(io, rtService));
        commands.put("add book", new AddBook(io, rtService));
        commands.put("add podcast", new AddPodcast(io, rtService));
        commands.put("add blog", new AddBlog(io, rtService));
        commands.put("add video", new AddVideo(io, rtService));
        commands.put("list", new ListReadingTips(io, rtService));
        commands.put("search", new Search(io, rtService));
        commands.put("edit header", new EditHeader(io, rtService));
        commands.put("edit desc", new EditDescription(io, rtService));
        commands.put("delete", new DeleteReadingTip(io, rtService));
        commands.put("mark as read", new MarkAsRead(io, rtService));
        commands.put("mark as unread", new MarkAsUnread(io, rtService));
        commands.put("edit", new EditField(io, rtService));
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
