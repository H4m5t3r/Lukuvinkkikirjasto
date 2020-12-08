package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class MarkAsUnread extends Command {
    public MarkAsUnread(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    @Override
    public void execute() {
        io.output("Give tip id:");
        String input = io.input();
        int id;
        try {
            id = Integer.valueOf(input);
        } catch (Exception e) {
            io.output("Id must be a number.");
            return;
        }
        try {
            if (!rtService.tipExists(id)) {
                io.output("Could not find responding tip.");
                return;
            }
            rtService.setReadStatus(id, false);
        } catch (SQLException e) {
            io.output("Problem accessing database.");
        }
    }
}