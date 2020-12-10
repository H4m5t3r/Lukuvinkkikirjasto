package lukuvinkkikirjasto.domain;

public class BlogReadingTip extends ReadingTip {
    
    public BlogReadingTip(
        int id,
        boolean read,
        String writer,
        String name,
        String link,
        String description
        ) {
            super(id, read, "blog");
            this.setField("writer", writer);
            this.setField("name", name);
            this.setField("link", link);
            this.setField("description", description);
        }

    @Override
    public String toString() {
        return "ID: " + this.getId()
        + "\nType: " + this.getType()
        + "\nWriter: " + this.getField("writer")
        + "\nName: " + this.getField("name")
        + "\nLink: " + this.getField("link")
        + "\nDescription: " + this.getField("description");
    }
}
