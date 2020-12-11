package lukuvinkkikirjasto.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lukuvinkkikirjasto.domain.DefaultReadingTip;
import lukuvinkkikirjasto.domain.ReadingTip;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class SQLDatabaseTest {

    SQLDatabase database;
    List<String> columnNames;
    List<String> columnValues;

    @Before
    public void setUp() throws SQLException, Exception {
        database = new SQLDatabase("testdatabase.db");
        // columnNames = new List<String>();
        // columnValues = new List<String>();
    }

    @After
    public void tearDown() {
        File dbFile = new File("testdatabase.db");
        dbFile.delete();
    }

    private void typeIsCorrect(String type) throws SQLException {
        assertEquals(database.getTips("all").get(0).getType(), type);
    }

    private void columnValuesAreCorrect() throws SQLException {
        for (int i = 0; i < columnNames.size(); i++) {
            assertEquals(database.getTips("all").get(0).getField(columnNames.get(i)), columnValues.get(i));
        }
    }

    @Test
    public void readingTipIsCreatedAndIsIncludedInTheList() throws SQLException {
        database.createDefault("Test Item", "Test description");
        typeIsCorrect("default");
        columnNames = Arrays.asList("header", "description");
        columnValues = Arrays.asList("Test Item", "Test description");
        columnValuesAreCorrect();
    }
    
    @Test
    public void bookIsCreatedAndIsIncludedInTheList() throws SQLException {
        database.createBook("Test writer", "Test Item", "Test isbn", "Test year", "Test description");
        typeIsCorrect("book");

        columnNames = Arrays.asList("writer", "name", "isbn", "year", "description");
        columnValues = Arrays.asList("Test writer", "Test Item", "Test isbn", "Test year", "Test description");
        columnValuesAreCorrect();
    }
    
    @Test
    public void podcastIsCreatedAndIsIncludedInTheList() throws SQLException {
        database.createPodcast("Test host", "Test Item", "Test link", "Test description");
        typeIsCorrect("podcast");
        columnNames = Arrays.asList("host", "name", "link", "description");
        columnValues = Arrays.asList("Test host", "Test Item", "Test link", "Test description");
        columnValuesAreCorrect();
    }
    
    @Test
    public void blogIsCreatedAndIsIncludedInTheList() throws SQLException {
        database.createBlog("Test writer", "Test Item", "Test link", "Test description");
        typeIsCorrect("blog");
        columnNames = Arrays.asList("writer", "name", "link", "description");
        columnValues = Arrays.asList("Test writer", "Test Item", "Test link", "Test description");
        columnValuesAreCorrect();
    }
    
    @Test
    public void videoIsCreatedAndIsIncludedInTheList() throws SQLException {
        database.createVideo("Test Item", "Test link", "Test published", "Test description");
        typeIsCorrect("video");
        columnNames = Arrays.asList("name", "link", "published", "description");
        columnValues = Arrays.asList("Test Item", "Test link", "Test published", "Test description");
        columnValuesAreCorrect();
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
