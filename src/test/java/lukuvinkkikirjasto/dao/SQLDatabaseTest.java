package lukuvinkkikirjasto.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.DefaultReadingTip;
import lukuvinkkikirjasto.domain.ReadingTip;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SQLDatabaseTest {

    SQLDatabase database;

    @Before
    public void setUp() throws SQLException, Exception {
        database = new SQLDatabase("testdatabase.db");
    } 

    @After
    public void tearDown() {
        File dbFile = new File("testdatabase.db");
        dbFile.delete();
    }

    @Test
    public void readingTipIsCreatedAndIsIncludedInTheList() throws SQLException {
        database.createDefault("Test Item", "Test description");
        assertEquals(database.getTips("all").get(0).getField("header"), "Test Item");
        assertEquals(database.getTips("all").get(0).getField("description"), "Test description");
    }

    @Test
    public void containsIdFindsExistingId() throws SQLException {
        database.createDefault("Test Item", "Test description");
        int id = database.getTips("all").get(0).getId();
        assertTrue(database.containsId(id));
    }

    @Test
    public void containsIdDoesNotFindNonExistingId() throws SQLException {
        database.createDefault("Test Item", "Test description");
        int id = -1;
        assertFalse(database.containsId(id));
    }
    
    @Test
    public void editHeaderChangesAreSaved() throws SQLException {
        database.createDefault("Header", "Desc");
        int id = database.getTips("all").get(0).getId();
        database.editHeader(id, "Edited header");
        assertEquals("Edited header", database.getTips("all").get(0).getField("header"));
    }

    @Test
    public void editDescriptionChangesAreSaved() throws SQLException {
        database.createDefault("Header", "Desc");
        int id = database.getTips("all").get(0).getId();
        database.editDescription(id, "Edited description");
        assertEquals("Edited description", database.getTips("all").get(0).getField("description"));
    }
   
    @Test
    public void readingTipIsMarkedAsReadCorrectly() throws SQLException {
        database.createDefault("Test Item", "Test description");
        database.setReadStatus(1, true);
        ArrayList<ReadingTip> tipList = database.getTips("all");
        assertEquals(true, tipList.get(0).getReadStatus());
    }
    
    @Test
    public void onlyReadReadingTipsAreListedWhenAsked() throws SQLException {
        database.createDefault("Test Item", "Test description");
        database.createDefault("New Test Item", "New test description");
        ArrayList<ReadingTip> tipList = database.getTips("all");
        database.setReadStatus(1, true);
        tipList = database.getTips("read");
        assertEquals(1, tipList.size());
        assertEquals(tipList.get(0).toString(), new DefaultReadingTip(1, true, "Test Item", "Test description").toString());
    }
        
    @Test
    public void onlyUnreadReadingTipsAreListedWhenAsked() throws SQLException {
        database.createDefault("Test Item", "Test description");
        database.createDefault("New Test Item", "New test description");
        ArrayList<ReadingTip> tipList = database.getTips("all");
        database.setReadStatus(1, true);
        tipList = database.getTips("unread");
        assertEquals(1, tipList.size());
        assertEquals(tipList.get(0).toString(), new DefaultReadingTip(2, true, "New Test Item", "New test description").toString());
    }
    
    @Test
    public void readingTipIsDeletedFromDatabase() throws SQLException {
        database.createDefault("Test Item", "Test description");
        ArrayList<ReadingTip> tipList = database.getTips("all");
        assertEquals(1, tipList.size());
        database.delete(1);
        tipList = database.getTips("all");
        assertEquals(0, tipList.size());
    }
    
    @Test
    public void readingTipIsFoundWhenSearchedWithCorrectString() throws SQLException {
        database.createDefault("My title", "My description");
        database.createDefault("Another Item To Add", "Descriptive text");
        database.createDefault("One more thing", "Already forgotten");
        ArrayList<ReadingTip> searchList = database.searchFromTips("add");
        assertEquals(searchList.get(0).toString(), new DefaultReadingTip(2, false, "Another Item To Add", "Descriptive text").toString());   
    }
    
    @Test
    public void readingTipsNotFoundWhenSearchedStringNotInTips() throws SQLException {
        database.createDefault("My title", "My description");
        database.createDefault("Another Item To Add", "Descriptive text");
        database.createDefault("One more thing", "Already forgotten");
        ArrayList<ReadingTip> searchList = database.searchFromTips("xxx");
        assertEquals(0, searchList.size());
    }
    
    @Test
    public void manyReadingTipsFoundWhenSearchedWithCorrectString() throws SQLException {
        database.createDefault("My title", "My description");
        database.createDefault("Another Item To Add", "Descriptive text");
        database.createDefault("One more thing", "Already forgotten");
        ArrayList<ReadingTip> searchList = database.searchFromTips("descript");
        assertEquals(2, searchList.size());
        assertEquals(searchList.get(0).toString(), new DefaultReadingTip(1, false, "My title", "My description").toString());
        assertEquals(searchList.get(1).toString(), new DefaultReadingTip(2, false, "Another Item To Add", "Descriptive text").toString());
    }
    
    
    
}
