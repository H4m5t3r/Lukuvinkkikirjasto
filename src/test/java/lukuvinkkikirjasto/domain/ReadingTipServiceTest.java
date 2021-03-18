package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import lukuvinkkikirjasto.dao.Database;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class ReadingTipServiceTest {
    ReadingTipService readingTipService;
    Database fakeDatabase;
    
    @Before
    public void setUp() throws SQLException {
        this.fakeDatabase = mock(Database.class);
        readingTipService = new ReadingTipService(fakeDatabase);
    }

    @Test
    public void addAddsReadingTipToDatabase() throws SQLException {
        readingTipService.add("test", "asd");
        verify(fakeDatabase).createDefault("test", "asd");
    }
    
    @Test
    public void addBookAddsBookToDatabase() throws SQLException {
        readingTipService.addBook("writer", "test", "!!!", "2020", "asd");
        verify(fakeDatabase).createBook("writer", "test", "!!!", "2020", "asd");
    }
    
    @Test
    public void addPodcastAddsPodcastToDatabase() throws SQLException {
        readingTipService.addPodcast("host", "test", "ohtu.com", "asd");
        verify(fakeDatabase).createPodcast("host", "test", "ohtu.com", "asd");
    }
    
    @Test
    public void addBlogAddsBlogToDatabase() throws SQLException {
        readingTipService.addBlog("writer", "test", "ohtu.com", "asd");
        verify(fakeDatabase).createBlog("writer", "test", "ohtu.com", "asd");
    }
    
    @Test
    public void addVideoAddsVideoToDatabase() throws SQLException {
        readingTipService.addVideo("test", "ohtu.com", "2020", "asd");
        verify(fakeDatabase).createVideo("test", "ohtu.com", "2020", "asd");
    }

    @Test
    public void onEditHeaderCallsDatabaseWithRightParameters() throws SQLException {
        readingTipService.editHeader(1, "header");
        verify(fakeDatabase).editHeader(1, "header");
    }

    @Test
    public void onEditDescriptionCallsDatabaseWithRightParameters() throws SQLException {
        readingTipService.editDescription(1, "desc");
        verify(fakeDatabase).editDescription(1, "desc");
    }
    
    @Test
    public void deleteDeletesReadingTipFromDatabase() throws SQLException {
        readingTipService.delete(1);
        verify(fakeDatabase).delete(1);
    }
    
    @Test
    public void searchReadingTipsSearchesTips() throws SQLException {
        readingTipService.searchReadingTips("search");
        verify(fakeDatabase).searchFromTips("search");
    }
    
    @Test
    public void editFieldEditsAFieldInTheDatabase() throws SQLException {
        readingTipService.editField(1, "host", "new host");
        verify(fakeDatabase).editField(1, "host", "new host");
    }
}
