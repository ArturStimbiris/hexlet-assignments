package exercise;

import java.util.Map;

public class FileKV implements KeyValueStorage {
    private String filepath;
    private Map<String, String> storage;

    public FileKV(String filepath, Map<String, String> initialData) {
        this.filepath = filepath;
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
        String content = Utils.serialize(storage);
        Utils.writeFile(filepath, content);
    }

    private void loadFromFile() {
        String content = Utils.readFile(filepath);
        storage = Utils.deserialize(content);
    }
}
