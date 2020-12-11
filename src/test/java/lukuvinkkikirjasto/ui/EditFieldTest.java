package lukuvinkkikirjasto.ui;

import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.*;

import lukuvinkkikirjasto.domain.BookReadingTip;
import lukuvinkkikirjasto.domain.ReadingTipService;

public class EditFieldTest {
    
    private IO io;
	private ReadingTipService rtService;
	private EditField editField;

	@Before
	public void setUp() {
		io = mock(IO.class);
		rtService = mock(ReadingTipService.class);
		editField = new EditField(io, rtService);
	}

	@Test
	public void stopsExecutingIfIdNotFound() throws SQLException {
		when(io.input()).thenReturn("3");
		when(rtService.tipExists(3)).thenReturn(false);
		editField.execute();
		verify(io, times(1)).input();
		verify(rtService, times(0)).editField(anyInt(), anyString(), anyString());
    }
    
    @Test
    public void informsUserIfIdNotFound() throws SQLException {
		when(io.input()).thenReturn("3");
		when(rtService.tipExists(3)).thenReturn(false);
		editField.execute();
		verify(io, times(1)).output("Could not find responding tip.");
    }

    @Test
    public void stopsExecutingIfIdIsNotANumber() throws SQLException {
        when(io.input()).thenReturn("five");
		editField.execute();
        verify(io, times(1)).output("Id must be a number.");
        verify(rtService, times(0)).editField(anyInt(), anyString(), anyString());
    }

    @Test
	public void stopsIfGivenFieldDoesNotExist() throws SQLException {
        when(io.input()).thenReturn("1").thenReturn("bananas");
        when(rtService.getTip(1)).thenReturn(new BookReadingTip(1, false, "Test writer", "Test name", "TestIsbn", "2000", "Test description"));
		when(rtService.tipExists(1)).thenReturn(true);
        editField.execute();
        verify(io, times(1)).output("Could not find given field.");
        verify(rtService, times(0)).editField(1, "bananas", "new banana");
	}

    @Test
	public void callsReadingTipServiceWithRightParameter() throws SQLException {
        when(io.input()).thenReturn("1").thenReturn("writer").thenReturn("new writer");
        when(rtService.getTip(1)).thenReturn(new BookReadingTip(1, false, "Test writer", "Test name", "TestIsbn", "2000", "Test description"));
		when(rtService.tipExists(1)).thenReturn(true);
		editField.execute();
        verify(rtService, times(1)).editField(1, "writer", "new writer");
    }
    
    @Test
	public void infromsUserOnDatabaseException() throws SQLException {
		when(io.input()).thenReturn("1");
		when(rtService.tipExists(1)).thenThrow(SQLException.class);
		editField.execute();
		verify(io, times(1)).output("Problem accessing database.");
	}
}
