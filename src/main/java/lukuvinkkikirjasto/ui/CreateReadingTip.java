package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class CreateReadingTip extends Command {
    public CreateReadingTip(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    public void execute() {
        io.output("Header: ");
        String header = io.input();
        io.output("Description: ");
        String description = io.input();
        try {
            rtService.add(header, description);
        } catch (SQLException ex) {
            io.output("Creation failed. Please try again.");
        }
    }
}
