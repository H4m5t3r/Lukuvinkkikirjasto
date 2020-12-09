package lukuvinkkikirjasto.domain;

public class ReadingTip {
    private int id;
    private String header;
    private String description;
    private boolean read;
    
    public ReadingTip(int id, String title, String description, boolean read) {
        this.id = id;
        this.header = title;
        this.description = description;
        this.read = read;
    }

    public ReadingTip(int id, String title, String description) {
        this(id, title, description, false);
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean getReadStatus() {
        return read;
    }
    
    public int getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.header = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setReadStatus(Boolean read) {
        this.read = read;
    }
    
    @Override
    public String toString() {
        return ("ID: " + this.id + "\nHeader: " + this.header + "\nDescription: " + this.description);
    }
    
}
