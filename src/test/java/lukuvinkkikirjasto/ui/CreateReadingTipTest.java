package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

public class CreateReadingTipTest {
    
    IO io;
    ReadingTipService rtService;
    CreateReadingTip createReadingTip;

    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        createReadingTip = new CreateReadingTip(io, rtService);
        when(io.input()).thenReturn("value")
                .thenReturn("value2");
    }

    @Test
    public void createReadingTipAsksForHeaderAndDescription() {
        createReadingTip.execute();
        verify(io).output("Header: ");
        verify(io).output("Description: ");
    }
    
    @Test
    public void createReadingTipAddsRightTip() throws SQLException {
        createReadingTip.execute();
        verify(rtService, times(1)).add("value", "value2");
    }
}
