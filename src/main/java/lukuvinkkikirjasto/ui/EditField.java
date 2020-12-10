package lukuvinkkikirjasto.ui;

import java.sql.SQLException;

import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class EditField extends Command {
	
	public EditField(IO io, ReadingTipService rtService) {
		super(io, rtService);
	}

	public void execute() {
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
			ReadingTip tip = rtService.getTip(id);
			String fields = String.join(", ", tip.getFields());
            io.output("Select field: " + fields);
			String selectedField = io.input();
			if (!tip.isField(selectedField)) {
				io.output("Could not find given field.");
                return;
			}

			io.output("New " + selectedField + ":");
			String newContent = io.input();
			rtService.editField(id, selectedField, newContent);
        } catch (SQLException e) {
            io.output("Problem accessing database.");
        }
	}
}
