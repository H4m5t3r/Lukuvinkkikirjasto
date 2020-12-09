package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SetReadStatusTest {
    
    IO io;
    ReadingTipService rtService;
    MarkAsRead markAsRead;
    MarkAsUnread markAsUnread;
    
    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        markAsRead = new MarkAsRead(io, rtService);
        markAsUnread = new MarkAsUnread(io, rtService);
    }
        
    @Test
    public void markAsReadWorksWithValidIdAndExistingTip() throws SQLException {
        setReadStatusWorks(markAsRead, true);
    }

    @Test
    public void markAsUnreadWorksWithValidIdAndExistingTip() throws SQLException {
        setReadStatusWorks(markAsUnread, false);
    }

    private void setReadStatusWorks(Command command, boolean status) throws SQLException {
        when(io.input()).thenReturn("1");
        when(rtService.tipExists(1)).thenReturn(true);
        command.execute();
        verify(rtService, times(1)).setReadStatus(1, status);
    }
    
    @Test
    public void markAsReadGivesErrorMessageWhenIdIsNotANumber() {
        setReadStatusGivesErrorMessageWhenIdIsNotANumber(markAsRead);
    }

    @Test
    public void markAsUnreadGivesErrorMessageWhenIdIsNotANumber() {
        setReadStatusGivesErrorMessageWhenIdIsNotANumber(markAsUnread);
    }

    private void setReadStatusGivesErrorMessageWhenIdIsNotANumber(Command command) {
        when(io.input()).thenReturn("A");
        command.execute();
        verify(io).output("Id must be a number.");
    }
    
    @Test
    public void markAsReadGivesErrorMessageWhenTipDoesNotExist() throws SQLException {
        setReadStatusGivesErrorMessageWhenTipDoesNotExist(markAsRead);
    }

    @Test
    public void markAsUnreadGivesErrorMessageWhenTipDoesNotExist() throws SQLException {
        setReadStatusGivesErrorMessageWhenTipDoesNotExist(markAsUnread);
    }

    private void setReadStatusGivesErrorMessageWhenTipDoesNotExist(Command command) throws SQLException {
        when(io.input()).thenReturn("99");
        when(rtService.tipExists(99)).thenReturn(false);
        command.execute();
        verify(io).output("Could not find responding tip.");
    }
}
