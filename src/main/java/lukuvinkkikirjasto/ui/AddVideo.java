package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class AddVideo extends Command {

    public AddVideo(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }
    
     public void execute() {
        io.output("Name: ");
        String name = io.input();
        io.output("Link: ");
        String link = io.input();
        io.output("Published: ");
        String published = io.input();
        io.output("Description: ");
        String description =io.input();
        try {
            rtService.addVideo(name, link, published, description);
        } catch (SQLException ex) {
            io.output("Creation failed. Please try again.");
        }
    }
}