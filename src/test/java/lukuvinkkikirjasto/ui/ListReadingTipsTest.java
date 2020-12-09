package lukuvinkkikirjasto.ui;

import java.sql.SQLException;

import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

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
        startingTips.add(new ReadingTip(1, "title", "description"));
        startingTips.add(new ReadingTip(2, "a", "b"));
        readAndUnreadTips = new ArrayList<>();
        readAndUnreadTips.add(new ReadingTip(1, "title", "description", true));
        readAndUnreadTips.add(new ReadingTip(2, "a", "b", false));
        when(rtService.getTips()).thenReturn(startingTips);
        rtService.add("asd", "testi");
        listReadingTips = new ListReadingTips(io, rtService);
    }
    
    @Test
    public void listReadingTipsGivesData() {
        when(io.input()).thenReturn("all");
        listReadingTips.execute();
        verify(io).output(new ReadingTip(1, "title", "description").toString() + "\n");
        verify(io).output(new ReadingTip(2, "a", "b").toString() + "\n");
    }
    
    @Test
    public void listReadingTipsPrintsRightMessageWhenNoTips() throws SQLException {
        when(rtService.getTips()).thenReturn(new ArrayList<>());
        when(io.input()).thenReturn("all");
        listReadingTips.execute();
        verify(io).output("No tips");
    }

    @Test
    public void unreadTipsListingCallsCorrectMethod() throws SQLException {
        when(io.input()).thenReturn("unread");
        listReadingTips.execute();
        verify(rtService).getReadOrUnreadTips(false);
    }
    
    @Test
    public void readTipsListingCallsCorrectMethod() throws SQLException {
        when(io.input()).thenReturn("read");
        listReadingTips.execute();
        verify(rtService).getReadOrUnreadTips(true);
    }
    
    @Test
    public void readTipsGivesRightMessageWhenNoReadTips() throws SQLException {
        when(rtService.getReadOrUnreadTips(true)).thenReturn(new ArrayList<>());
        when(io.input()).thenReturn("read");
        listReadingTips.execute();
        verify(io).output("No tips");
    }
    
    @Test
    public void unreadTipsGivesRightMessageWhenNoReadTips() throws SQLException {
        when(rtService.getReadOrUnreadTips(false)).thenReturn(new ArrayList<>());
        when(io.input()).thenReturn("unread");
        listReadingTips.execute();
        verify(io).output("No tips");
    }
    
    @Test
    public void readTipsAreListed() throws SQLException {
        readAndUnreadTips.removeIf(tip -> tip.getReadStatus() == false);
        when(rtService.getReadOrUnreadTips(true)).thenReturn(readAndUnreadTips);
        when(io.input()).thenReturn("read");
        listReadingTips.execute();
        verify(io).output(new ReadingTip(1, "title", "description").toString() + "\n");
    }
    
    @Test
    public void unreadTipsAreListed() throws SQLException {
        readAndUnreadTips.removeIf(tip -> tip.getReadStatus() == true);
        when(rtService.getReadOrUnreadTips(false)).thenReturn(readAndUnreadTips);
        when(io.input()).thenReturn("unread");
        listReadingTips.execute();
        verify(io).output(new ReadingTip(2, "a", "b").toString() + "\n");
    }

}
