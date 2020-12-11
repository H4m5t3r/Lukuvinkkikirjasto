package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import lukuvinkkikirjasto.domain.ReadingTipService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteReadingTipTest {
    IO io;
    ReadingTipService rtService;
    DeleteReadingTip delete;
    
    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        delete = new DeleteReadingTip(io, rtService);
    }
    
    @Test
    public void deleteReadingTipAsksForId() {
        delete.execute();
        verify(io).output("Give tip id:");
    }
    
    @Test
    public void errorMessageWhenTipDoesNotExist() {
        when(io.input()).thenReturn("1");
        delete.execute();
        verify(io).output("Could not find responding tip.");
    }
    
    @Test
    public void deleteReadingTipDeletesRightTip() throws SQLException {
        when(io.input()).thenReturn("1").thenReturn("y");
        when(rtService.tipExists(1)).thenReturn(true);
        delete.execute();
        verify(rtService, times(1)).delete(1);
    }
    
    @Test
    public void doesNotDeleteIfConfirmationIsNotGiven() throws SQLException {
        when(io.input()).thenReturn("1").thenReturn("n");
        when(rtService.tipExists(1)).thenReturn(true);
        delete.execute();
        verify(rtService, times(0)).delete(1);
    }
    
    @Test
    public void errorMessageWhenInvalidInput() {
        when(io.input()).thenReturn("A");
        delete.execute();
        verify(io).output("Id must be a number.");
    }
}
