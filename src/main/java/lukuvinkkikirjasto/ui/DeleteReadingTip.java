
package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class DeleteReadingTip extends Command {

    public DeleteReadingTip(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    @Override
    public void execute() {
        io.output("Give tip id:");
        String input = io.input();
        int id;
        try {
            id = Integer.valueOf(input);
            if (!rtService.tipExists(id)) {
                io.output("Could not find responding tip.");
                return;
            }
        } catch (Exception e) {
            io.output("Id must be a number.");
            return;
        }
        io.output("Are you sure? Enter 'y' to confirm.");
        input = io.input();
        if (input.toLowerCase().equals("y")) {
            try {
                rtService.delete(id);
            } catch (SQLException ex) {
                io.output("Deletion failed. Please try again.");
            }
        }
    }    
}
