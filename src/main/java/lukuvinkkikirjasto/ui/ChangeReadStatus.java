package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class ChangeReadStatus {
    
    private ReadingTipService rtService;
    private IO io;
    
    public ChangeReadStatus(IO io, ReadingTipService rtService) {
        this.io = io;
        this.rtService = rtService;
    }

    public void change(boolean status) {
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
            rtService.setReadStatus(id, status);
        } catch (SQLException e) {
            io.output("Problem accessing database.");
        }
    }
}
