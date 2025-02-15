package exercise;

import java.util.Map;

public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> originalMap = storage.toMap();
        Map<String, String> swappedMap = new HashMap<>();

        for (Map.Entry<String, String> entry : originalMap.entrySet()) {
            swappedMap.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<String, String> entry : originalMap.entrySet()) {
            storage.unset(entry.getKey());
        }

        for (Map.Entry<String, String> entry : swappedMap.entrySet()) {
            storage.set(entry.getKey(), entry.getValue());
        }
    }
}