package lukuvinkkikirjasto.ui;

import java.sql.SQLException;
import org.junit.*;

import lukuvinkkikirjasto.domain.ReadingTipService;

import static org.mockito.Mockito.*;

public class AddBookTest {
    
    IO io;
    ReadingTipService rtService;
    AddBook addBook;

    @Before
    public void setUp() {
        io = mock(IO.class);
        rtService = mock(ReadingTipService.class);
        addBook = new AddBook(io, rtService);
        when(io.input()).thenReturn("Writer")
                .thenReturn("Name")
                .thenReturn("ISBN")
                .thenReturn("Year")
                .thenReturn("Description");
    }

    @Test
    public void addBookAsksForDetails() {
        addBook.execute();
        verify(io).output("Writer: ");
        verify(io).output("Name: ");
        verify(io).output("ISBN: ");
        verify(io).output("Year: ");
        verify(io).output("Description: ");
    }
    
    
    @Test
    public void addBookAddsRightTip() throws SQLException {
        addBook.execute();
        verify(rtService, times(1)).addBook("Writer", "Name", "ISBN", "Year", "Description");
    }
}
