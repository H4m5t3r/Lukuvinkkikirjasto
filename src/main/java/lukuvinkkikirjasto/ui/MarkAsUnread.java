package lukuvinkkikirjasto.ui;

import lukuvinkkikirjasto.domain.ReadingTipService;

public class MarkAsUnread extends Command {
    public MarkAsUnread(IO io, ReadingTipService rtService) {
        super(io, rtService);
    }

    @Override
    public void execute() {
        ChangeReadStatus change = new ChangeReadStatus(io, rtService);
        change.change(false);
    }
}