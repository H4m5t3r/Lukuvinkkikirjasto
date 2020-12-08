package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MarkAsUnreadTest {
    
    IO io;
    ReadingTipService rtService;
    MarkAsUnread markAsUnread;
    
    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        markAsUnread = new MarkAsUnread(io, rtService);
    }
        
    @Test
    public void markAsUnreadWorksWithValidIdAndExistingTip() throws SQLException {
        when(io.input()).thenReturn("1");
        when(rtService.tipExists(1)).thenReturn(true);
        markAsUnread.execute();
        verify(rtService, times(1)).setReadStatus(1, false);
    }
    
    @Test
    public void markAsUnreadGivesErrorMessageWhenIdIsNotANumber() {
        when(io.input()).thenReturn("A");
        markAsUnread.execute();
        verify(io).output("Id must be a number.");
    }
    
    @Test
    public void markAsUnreadGivesErrorMessageWhenTipDoesNotExist() throws SQLException {
        when(io.input()).thenReturn("99");
        when(rtService.tipExists(99)).thenReturn(false);
        markAsUnread.execute();
        verify(io).output("Could not find responding tip.");
    }
}