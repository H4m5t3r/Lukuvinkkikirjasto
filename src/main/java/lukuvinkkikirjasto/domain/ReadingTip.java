package lukuvinkkikirjasto.domain;

import java.util.HashMap;

public abstract class ReadingTip {
    private int id;
    private boolean read;
    private HashMap<String, String> fields;
    private String type;

    public ReadingTip(int id, boolean read, String type) {
        this.id = id;
        this.read = read;
        this.type = type;
        this.fields = new HashMap<>();
    }

    public String getType() {
        return this.type;
    }

    public boolean isField(String field) {
        return this.fields.containsKey(field);
    }

    protected void setField(String field, String value) {
        this.fields.put(field, value);
    }

    public String getField(String field) {
        return this.fields.get(field);
    }

    public int getId() {
        return this.id;
    }

    public boolean getReadStatus() {
        return this.read;
    }

    public void setReadStatus(boolean status) {
        this.read = status;
    }
}
