package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

public class AddBlogTest {
    
    IO io;
    ReadingTipService rtService;
    AddBlog addBlog;

    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        addBlog = new AddBlog(io, rtService);
        when(io.input()).thenReturn("Writer")
                .thenReturn("Name")
                .thenReturn("Link")
                .thenReturn("Description");
    }

    @Test
    public void addBlogAsksForDetails() {
        addBlog.execute();
        verify(io).output("Writer: ");
        verify(io).output("Name: ");
        verify(io).output("Link: ");
        verify(io).output("Description: ");
    }
    
    
    @Test
    public void addBlogAddsRightTip() throws SQLException {
        addBlog.execute();
        verify(rtService, times(1)).addBlog("Writer", "Name", "Link", "Description");
    }
}
