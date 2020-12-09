package lukuvinkkikirjasto.domain;

import java.util.HashMap;

public abstract class ReadingTipInterface {
    private int id;
    private boolean read;
    private HashMap<String, String> fields;
    private String type;

    public ReadingTipInterface(int id, boolean read, String type) {
        this.id = id;
        this.read = read;
        this.type = type;
        this.fields = new HashMap<>();
    }

    public String[] getFields() {
        return (String[]) this.fields.keySet().toArray();
    }
    public boolean isField(String field) {
        return this.fields.containsKey(field);

    }
    public void editField(String field, String value) {
        if (isField(field)) {
            this.fields.put(field, value);
        }
    }

    protected void setField(String field, String value) {
        this.fields.put(field, value);
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
