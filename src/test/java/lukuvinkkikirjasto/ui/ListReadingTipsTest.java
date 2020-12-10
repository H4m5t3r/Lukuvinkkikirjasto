package lukuvinkkikirjasto.ui;

import java.sql.SQLException;

import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import lukuvinkkikirjasto.domain.DefaultReadingTip;

public class ListReadingTipsTest {

    IO io;
    ReadingTipService rtService;
    ListReadingTips listReadingTips;
    ArrayList<ReadingTip> startingTips;
    ArrayList<ReadingTip> readAndUnreadTips;

    @Before
    public void setUp() throws SQLException {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        startingTips = new ArrayList<>();
        startingTips.add(new DefaultReadingTip(1, false, "title", "description"));
        startingTips.add(new DefaultReadingTip(2, false, "a", "b"));
        readAndUnreadTips = new ArrayList<>();
        readAndUnreadTips.add(new DefaultReadingTip(1, true, "title", "description"));
        readAndUnreadTips.add(new DefaultReadingTip(2, false, "a", "b"));
        when(rtService.getTips("all")).thenReturn(startingTips);
        rtService.add("asd", "testi");
        listReadingTips = new ListReadingTips(io, rtService);
    }
    
    @Test
    public void listReadingTipsGivesData() {
        when(io.input()).thenReturn("all");
        listReadingTips.execute();
        verify(io).output(new DefaultReadingTip(1, false, "title", "description").toString() + "\n");
        verify(io).output(new DefaultReadingTip(2, false, "a", "b").toString() + "\n");
    }
    
    @Test
    public void listReadingTipsPrintsRightMessageWhenNoTips() throws SQLException {
        when(rtService.getTips("all")).thenReturn(new ArrayList<>());
        when(io.input()).thenReturn("all");
        listReadingTips.execute();
        verify(io).output("No tips");
    }

    @Test
    public void unreadTipsListingCallsCorrectMethod() throws SQLException {
        when(io.input()).thenReturn("unread");
        listReadingTips.execute();
        verify(rtService).getTips("unread");
    }
    
    @Test
    public void readTipsListingCallsCorrectMethod() throws SQLException {
        when(io.input()).thenReturn("read");
        listReadingTips.execute();
        verify(rtService).getTips("read");
    }
    
    @Test
    public void readTipsGivesRightMessageWhenNoReadTips() throws SQLException {
        when(rtService.getTips("read")).thenReturn(new ArrayList<>());
        when(io.input()).thenReturn("read");
        listReadingTips.execute();
        verify(io).output("No tips");
    }
    
    @Test
    public void unreadTipsGivesRightMessageWhenNoReadTips() throws SQLException {
        when(rtService.getTips("unread")).thenReturn(new ArrayList<>());
        when(io.input()).thenReturn("unread");
        listReadingTips.execute();
        verify(io).output("No tips");
    }
    
    @Test
    public void readTipsAreListed() throws SQLException {
        readAndUnreadTips.removeIf(tip -> tip.getReadStatus() == false);
        when(rtService.getTips("read")).thenReturn(readAndUnreadTips);
        when(io.input()).thenReturn("read");
        listReadingTips.execute();
        verify(io).output(new DefaultReadingTip(1, true, "title", "description").toString() + "\n");
    }
    
    @Test
    public void unreadTipsAreListed() throws SQLException {
        readAndUnreadTips.removeIf(tip -> tip.getReadStatus() == true);
        when(rtService.getTips("unread")).thenReturn(readAndUnreadTips);
        when(io.input()).thenReturn("unread");
        listReadingTips.execute();
        verify(io).output(new DefaultReadingTip(2, false,  "a", "b").toString() + "\n");
    }

}
