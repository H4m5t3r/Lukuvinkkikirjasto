package lukuvinkkikirjasto.domain;

public class BookReadingTip extends ReadingTip {
    public BookReadingTip(
        int id,
        boolean read,
        String writer,
        String name,
        String isbn,
        String year
        ) {
            super(id, read, "book");
            this.setField("writer", writer);
            this.setField("name", name);
            this.setField("isbn", isbn);
            this.setField("year", year);
        }

    @Override
    public String toString() {
        return "ID: book" + this.getId()
        + "\nWriter " + this.getField("writer")
        + "\nName " + this.getField("name")
        + "\nISBN " + this.getField("isbn")
        + "\nYear " + this.getField("year");
    }
}
