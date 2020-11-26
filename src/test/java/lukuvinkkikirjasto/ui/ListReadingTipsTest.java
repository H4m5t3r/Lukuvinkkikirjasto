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

    @Before
    public void setUp() throws SQLException {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        startingTips = new ArrayList<>();
        startingTips.add(new ReadingTip("title", "description"));
        startingTips.add(new ReadingTip("a", "b"));
        when(rtService.getTips()).thenReturn(startingTips);
        listReadingTips = new ListReadingTips(io, rtService);
    }
    
    @Test
    public void listReadingTipsGivesData() {
        listReadingTips.execute();
        verify(io).output(new ReadingTip("title", "description").toString() + "\n");
        verify(io).output(new ReadingTip("a", "b").toString() + "\n");
    }
    
    @Test
    public void listReadingTipsPrintsRightMessageWhenNoTips() throws SQLException {
        when(rtService.getTips()).thenReturn(new ArrayList<>());
        listReadingTips.execute();
        verify(io).output("No tips\n");
    }
}