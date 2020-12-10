package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

public class AddPodcastTest {
    
    IO io;
    ReadingTipService rtService;
    AddPodcast addPodcast;

    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        addPodcast = new AddPodcast(io, rtService);
        when(io.input()).thenReturn("Host")
                .thenReturn("Name")
                .thenReturn("Link")
                .thenReturn("Description");
    }

    @Test
    public void addPodcastAsksForDetails() {
        addPodcast.execute();
        verify(io).output("Host: ");
        verify(io).output("Name: ");
        verify(io).output("Link: ");
        verify(io).output("Description: ");
    }
    
    
    @Test
    public void addPodcastAddsRightTip() throws SQLException {
        addPodcast.execute();
        verify(rtService, times(1)).addPodcast("Host", "Name", "Link", "Description");
    }
}