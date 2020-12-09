package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class AddBook extends Command {

    public AddBook(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }
    
     public void execute() {
        io.output("Writer: ");
        String writer = io.input();
        io.output("Name: ");
        String name = io.input();
        io.output("ISBN: ");
        String isbn = io.input();
        io.output("Year: ");
        String year = io.input();
        try {
            rtService.addBook(writer, name, isbn, year);
        } catch (SQLException ex) {
            io.output("Creation failed. Please try again.");
        }
    }
}

