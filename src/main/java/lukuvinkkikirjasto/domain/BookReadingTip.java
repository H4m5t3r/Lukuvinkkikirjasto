package lukuvinkkikirjasto.domain;

public class BookReadingTip extends ReadingTip {
    public BookReadingTip(
        int id,
        boolean read,
        String writer,
        String name,
        String isbn,
        String year,
        String description    
        ) {
            super(id, read, "book");
            this.setField("writer", writer);
            this.setField("name", name);
            this.setField("isbn", isbn);
            this.setField("year", year);
            this.setField("description", description);
        }

    @Override
    public String toString() {
        return "ID: " + this.getId()
        + "\nType: " + this.getType()
        + "\nWriter: " + this.getField("writer")
        + "\nName: " + this.getField("name")
        + "\nISBN: " + this.getField("isbn")
        + "\nYear: " + this.getField("year")
        + "\nDescription: " + this.getField("description");
    }
}
