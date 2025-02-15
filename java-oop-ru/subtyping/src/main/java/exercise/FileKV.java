package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String filePath;
    private Map<String, String> storage;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        this.storage = new HashMap<>(initialData);
        saveToFile();
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
        saveToFile();
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        saveToFile();
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }

    private void saveToFile() {
        String serializedData = Utils.serialize(storage);
        Utils.writeFile(filePath, serializedData);
    }

    private void loadFromFile() {
        String fileContent = Utils.readFile(filePath);
        storage = Utils.deserialize(fileContent);
    }
}
// END
