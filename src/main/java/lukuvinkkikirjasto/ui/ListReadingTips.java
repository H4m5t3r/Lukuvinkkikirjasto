package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class ListReadingTips extends Command {
    public ListReadingTips(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    public void execute() {
        try {
            ArrayList<ReadingTip> tips = null;
            io.output("Which tips to list? Type unread/read (default: all)");
            String input = io.input();
            if (input.equals("unread")) {
                tips = rtService.getTips("unread");
            } else if (input.equals("read")) {
                tips = rtService.getTips("read");
            } else {
                tips = rtService.getTips("all");
            }

            if (tips.isEmpty()) {
                io.output("No tips");
            }
            for (ReadingTip tip : tips) {
                io.output(tip.toString() + "\n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListReadingTips.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
