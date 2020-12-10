package lukuvinkkikirjasto.domain;

import java.sql.SQLException;
import lukuvinkkikirjasto.dao.Database;
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
        verify(fakeDatabase).createDefault("test", "asd");
    }
    
    @Test
    public void addBookAddsBookToDatabase() throws SQLException {
        rtService.addBook("writer", "test", "!!!", "2020", "asd");
        verify(fakeDatabase).createBook("writer", "test", "!!!", "2020", "asd");
    }
    
    @Test
    public void addPodcastAddsPodcastToDatabase() throws SQLException {
        rtService.addPodcast("host", "test", "ohtu.com", "asd");
        verify(fakeDatabase).createPodcast("host", "test", "ohtu.com", "asd");
    }
    
    @Test
    public void addBlogAddsBlogToDatabase() throws SQLException {
        rtService.addBlog("writer", "test", "ohtu.com", "asd");
        verify(fakeDatabase).createBlog("writer", "test", "ohtu.com", "asd");
    }
    
    @Test
    public void addVideoAddsVideoToDatabase() throws SQLException {
        rtService.addVideo("test", "ohtu.com", "2020", "asd");
        verify(fakeDatabase).createVideo("test", "ohtu.com", "2020", "asd");
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
