package lukuvinkkikirjasto.domain;

public class DefaultReadingTip extends ReadingTip {
    public DefaultReadingTip(
        int id,
        boolean read,
        String header,
        String description
    ) {
        super(id, read, "default");
        this.setField("header", header);
        this.setField("description", description);
    }

    @Override
    public String toString() {
        return "ID: default" + this.getId()
        + "\nHeader: " + this.getField("header")
        + "\nDescription: " + this.getField("description");
    }
}
