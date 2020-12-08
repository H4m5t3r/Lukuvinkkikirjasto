package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MarkAsReadTest {
    
    IO io;
    ReadingTipService rtService;
    MarkAsRead markAsRead;
    
    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        markAsRead = new MarkAsRead(io, rtService);
    }
        
    @Test
    public void markAsReadWorksWithValidIdAndExistingTip() throws SQLException {
        when(io.input()).thenReturn("1");
        when(rtService.tipExists(1)).thenReturn(true);
        markAsRead.execute();
        verify(rtService, times(1)).setReadStatus(1, true);
    }
    
    @Test
    public void markAsReadGivesErrorMessageWhenIdIsNotANumber() {
        when(io.input()).thenReturn("A");
        markAsRead.execute();
        verify(io).output("Id must be a number.");
    }
    
    @Test
    public void markAsReadGivesErrorMessageWhenTipDoesNotExist() throws SQLException {
        when(io.input()).thenReturn("99");
        when(rtService.tipExists(99)).thenReturn(false);
        markAsRead.execute();
        verify(io).output("Could not find responding tip.");
    }
}
