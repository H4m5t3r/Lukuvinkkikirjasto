package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

public class AddVideoTest {
    
    IO io;
    ReadingTipService rtService;
    AddVideo addVideo;

    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        addVideo = new AddVideo(io, rtService);
        when(io.input()).thenReturn("Name")
                .thenReturn("Link")
                .thenReturn("Published")
                .thenReturn("Description");
    }

    @Test
    public void addVideoAsksForDetails() {
        addVideo.execute();
        verify(io).output("Name: ");
        verify(io).output("Link: ");
        verify(io).output("Published: ");
        verify(io).output("Description: ");
    }
    
    
    @Test
    public void addVideoAddsRightTip() throws SQLException {
        addVideo.execute();
        verify(rtService, times(1)).addVideo("Name", "Link", "Published", "Description");
    }
}
