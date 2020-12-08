package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class MarkAsRead extends Command {
    public MarkAsRead(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    @Override
    public void execute() {
        ChangeReadStatus change = new ChangeReadStatus(io, rtService);
        change.change(true);
    }
}
