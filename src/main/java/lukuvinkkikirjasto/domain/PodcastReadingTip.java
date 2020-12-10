package lukuvinkkikirjasto.domain;

public class PodcastReadingTip extends ReadingTip {
    
    public PodcastReadingTip(
        int id,
        boolean read,
        String host,
        String name,
        String link,
        String description
        ) {
            super(id, read, "podcast");
            this.setField("host", host);
            this.setField("name", name);
            this.setField("link", link);
            this.setField("description", description);
        }

    @Override
    public String toString() {
        return "ID: " + this.getId()
        + "\nType: " + this.getType()
        + "\nHost: " + this.getField("host")
        + "\nName: " + this.getField("name")
        + "\nLink: " + this.getField("link")
        + "\nDescription: " + this.getField("description");
    }
}
