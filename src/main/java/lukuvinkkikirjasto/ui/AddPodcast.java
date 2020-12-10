package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class AddPodcast extends Command {

    public AddPodcast(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }
    
     public void execute() {
        io.output("Host: ");
        String host = io.input();
        io.output("Name: ");
        String name = io.input();
        io.output("Link: ");
        String link = io.input();
        io.output("Description: ");
        String description =io.input();
        try {
            rtService.addPodcast(host, name, link, description);
        } catch (SQLException ex) {
            io.output("Creation failed. Please try again.");
        }
    }
}