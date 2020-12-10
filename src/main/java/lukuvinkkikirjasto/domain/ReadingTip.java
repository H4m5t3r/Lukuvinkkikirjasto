package lukuvinkkikirjasto.domain;

import java.util.HashMap;
import java.util.Set;

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

    public String[] getFields() {
        Set<String> asSet = this.fields.keySet();
        String[] result = new String[asSet.size()];
        int i = 0;
        for (String field: asSet) {
            result[i++] = field;
        }
        return result;
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
