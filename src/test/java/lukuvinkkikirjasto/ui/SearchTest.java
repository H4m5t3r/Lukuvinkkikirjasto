
package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import lukuvinkkikirjasto.domain.DefaultReadingTip;
import lukuvinkkikirjasto.domain.ReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;


public class SearchTest {
    IO io;
    ReadingTipService rtService;
    Search search;
    ArrayList<ReadingTip> searchList;

    @Before
    public void setUp() throws SQLException {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        search = new Search(io, rtService);
        
        searchList = new ArrayList<>();
        searchList.add(new DefaultReadingTip(1, false, "My title", "My description"));
        when(rtService.searchReadingTips("title")).thenReturn(searchList); 

    }
    
    @Test
    public void searchCallsReadingTipServiceWithRightParameterAndPrints() throws SQLException {
        when(io.input()).thenReturn("title");
        search.execute();
        verify(io).output("Enter search term: ");
        verify(io, times(1)).input();
        verify(rtService).searchReadingTips("title");
        verify(io).output(new DefaultReadingTip(1, false, "My title", "My description").toString() + "\n");
    }

    @Test
    public void searchPrintsRightMessageWhenNoTipsFound() throws SQLException {
        when(io.input()).thenReturn("xxx");
        search.execute();
        verify(rtService).searchReadingTips("xxx");
        verify(io).output("No tips");
    }
    
}
