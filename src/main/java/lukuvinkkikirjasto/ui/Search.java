package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class Search extends Command {
    public Search(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    public void execute() {
        try {

            io.output("Enter search term: ");
            String userInput = io.input();
            ArrayList<ReadingTip> tips = rtService.searchReadingTips(userInput);
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