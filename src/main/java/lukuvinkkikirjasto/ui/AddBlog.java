package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class AddBlog extends Command {

    public AddBlog(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }
    
    public void execute() {
        io.output("Writer: ");
        String writer = io.input();
        io.output("Name: ");
        String name = io.input();
        io.output("Link: ");
        String link = io.input();
        io.output("Description: ");
        String description =io.input();
        try {
            rtService.addBlog(writer, name, link, description);
        } catch (SQLException ex) {
            io.output("Creation failed. Please try again.");
        }
    }
}