package lukuvinkkikirjasto;

import java.sql.SQLException;
import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.dao.SQLDatabase;
import lukuvinkkikirjasto.ui.IO;
import lukuvinkkikirjasto.ui.SystemIO;
import lukuvinkkikirjasto.ui.UserInterface;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class Main {
    public static void main(String[] args) throws SQLException {
        IO systemIO = new SystemIO();
        Database database = new SQLDatabase("database.db");
        ReadingTipService rtService = new ReadingTipService(database);
        UserInterface ui = new UserInterface(systemIO, rtService);
        ui.start();
    }
}
