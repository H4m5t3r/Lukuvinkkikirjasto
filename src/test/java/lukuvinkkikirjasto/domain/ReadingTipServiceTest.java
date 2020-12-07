package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkkikirjasto.dao.Database;
import lukuvinkkikirjasto.ui.IO;
import lukuvinkkikirjasto.ui.Unknown;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ReadingTipServiceTest {
    ReadingTipService rtService;
    Database fakeDatabase;
    
    @Before
    public void setUp() throws SQLException {
        this.fakeDatabase = mock(Database.class);
        rtService = new ReadingTipService(fakeDatabase);
    }

    @Test
    public void addAddsReadingTipToDatabase() throws SQLException {
        rtService.add("test", "asd");
        verify(fakeDatabase).create("test", "asd");
    }

    @Test
    public void onEditHeaderCallsDatabaseWithRightParameters() throws SQLException {
        rtService.editHeader(1, "header");
        verify(fakeDatabase).editHeader(1, "header");
    }

    @Test
    public void onEditDescriptionCallsDatabaseWithRightParameters() throws SQLException {
        rtService.editDescription(1, "desc");
        verify(fakeDatabase).editDescription(1, "desc");
    }
    
    @Test
    public void deleteDeletesReadingTipFromDatabase() throws SQLException {
        rtService.delete(1);
        verify(fakeDatabase).delete(1);
    }
    
    @Test
    public void searchReadingTipsSearchesTips() throws SQLException {
        rtService.searchReadingTips("search");
        verify(fakeDatabase).searchFromTips("search");
    } 
    
}
