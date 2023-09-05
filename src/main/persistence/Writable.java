package persistence;

import org.json.JSONObject;

// Attributes JsonSerializationDemo project
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
