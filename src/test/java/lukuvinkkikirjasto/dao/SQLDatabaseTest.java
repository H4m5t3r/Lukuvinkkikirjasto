package lukuvinkkikirjasto.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
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
        database.create("Test Item", "Test description");
        assertEquals(database.getTips().get(0).getHeader(), "Test Item");
        assertEquals(database.getTips().get(0).getDescription(), "Test description");
    }

    @Test
    public void containsIdFindsExistingId() throws SQLException {
        database.create("Test Item", "Test description");
        int id = database.getTips().get(0).getId();
        assertTrue(database.containsId(id));
    }

    @Test
    public void containsIdDoesNotFindNonExistingId() throws SQLException {
        database.create("Test Item", "Test description");
        int id = -1;
        assertFalse(database.containsId(id));
    }
    
    @Test
    public void editHeaderChangesAreSaved() throws SQLException {
        database.create("Header", "Desc");
        int id = database.getTips().get(0).getId();
        database.editHeader(id, "Edited header");
        assertEquals("Edited header", database.getTips().get(0).getHeader());
    }

    @Test
    public void editDescriptionChangesAreSaved() throws SQLException {
        database.create("Header", "Desc");
        int id = database.getTips().get(0).getId();
        database.editDescription(id, "Edited description");
        assertEquals("Edited description", database.getTips().get(0).getDescription());
    }
   
    @Test
    public void readingTipIsMarkedAsReadCorrectly() throws SQLException {
        database.create("Test Item", "Test description");
        database.setReadStatusToTrue(1);
        ArrayList<ReadingTip> tipList = database.getTips();
        assertEquals(true, tipList.get(0).getReadStatus());
    }
    
    @Test
    public void onlyReadReadingTipsAreListedWhenAsked() throws SQLException {
        database.create("Test Item", "Test description");
        database.create("New Test Item", "New test description");
        ArrayList<ReadingTip> tipList = database.getTips();
        database.setReadStatusToTrue(1);
        tipList = database.getReadOrUnreadTips(true);
        assertEquals(1, tipList.size());
        assertEquals(tipList.get(0).toString(), new ReadingTip(1, "Test Item", "Test description").toString());
    }
        
    @Test
    public void onlyUnreadReadingTipsAreListedWhenAsked() throws SQLException {
        database.create("Test Item", "Test description");
        database.create("New Test Item", "New test description");
        ArrayList<ReadingTip> tipList = database.getTips();
        database.setReadStatusToTrue(1);
        tipList = database.getReadOrUnreadTips(false);
        assertEquals(1, tipList.size());
        assertEquals(tipList.get(0).toString(), new ReadingTip(2, "New Test Item", "New test description").toString());
    }
    
    @Test
    public void readingTipIsDeletedFromDatabase() throws SQLException {
        database.create("Test Item", "Test description");
        ArrayList<ReadingTip> tipList = database.getTips();
        assertEquals(1, tipList.size());
        database.delete(1);
        tipList = database.getTips();
        assertEquals(0, tipList.size());
    }
    
    @Test
    public void readingTipIsFoundWhenSearchedWithCorrectString() throws SQLException {
        database.create("My title", "My description");
        database.create("Another Item To Add", "Descriptive text");
        database.create("One more thing", "Already forgotten");
        ArrayList<ReadingTip> searchList = database.searchFromTips("add");
        assertEquals(searchList.get(0).toString(), new ReadingTip(2, "Another Item To Add", "Descriptive text").toString());   
    }
    
    @Test
    public void readingTipsNotFoundWhenSearchedStringNotInTips() throws SQLException {
        database.create("My title", "My description");
        database.create("Another Item To Add", "Descriptive text");
        database.create("One more thing", "Already forgotten");
        ArrayList<ReadingTip> searchList = database.searchFromTips("xxx");
        assertEquals(0, searchList.size());
    }
    
    @Test
    public void manyReadingTipsFoundWhenSearchedWithCorrectString() throws SQLException {
        database.create("My title", "My description");
        database.create("Another Item To Add", "Descriptive text");
        database.create("One more thing", "Already forgotten");
        ArrayList<ReadingTip> searchList = database.searchFromTips("descript");
        assertEquals(2, searchList.size());
        assertEquals(searchList.get(0).toString(), new ReadingTip(1, "My title", "My description").toString());
        assertEquals(searchList.get(1).toString(), new ReadingTip(2, "Another Item To Add", "Descriptive text").toString());
    }
    
    
    
}
