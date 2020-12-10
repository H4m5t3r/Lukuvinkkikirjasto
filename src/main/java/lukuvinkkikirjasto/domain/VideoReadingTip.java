package lukuvinkkikirjasto.domain;

public class VideoReadingTip extends ReadingTip {
    
    public VideoReadingTip(
        int id,
        boolean read,
        String name,
        String link,
        String published,
        String description
        ) {
            super(id, read, "video");
            this.setField("name", name);
            this.setField("link", link);
            this.setField("published", published);
            this.setField("description", description);
        }

    @Override
    public String toString() {
        return "ID: " + this.getId()
        + "\nType: " + this.getType()
        + "\nName: " + this.getField("name")
        + "\nLink: " + this.getField("link")
        + "\nPublished: " + this.getField("published")                
        + "\nDescription: " + this.getField("description");
    }
}
