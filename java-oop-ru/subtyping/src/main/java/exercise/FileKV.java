package exercise;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileKV implements KeyValueStorage {
    private String filePath;
    private Map<String, String> storage;

    public FileKV(String filePath, Map<String, String> initialData) {
        this.filePath = filePath;
        // Если файл существует, загружаем данные с диска
        if (Files.exists(Paths.get(filePath))) {
            loadFromFile();
        } else {
            // Если файла нет, используем начальные данные
            this.storage = new HashMap<>(initialData);
            saveToFile(); // Сохраняем начальные данные на диск
        }
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
        saveToFile(); // Сохраняем изменения на диск
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        saveToFile(); // Сохраняем изменения на диск
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
