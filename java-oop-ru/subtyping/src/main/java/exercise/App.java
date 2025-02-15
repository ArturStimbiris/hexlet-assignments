package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> originalMap = storage.toMap();
        for (Map.Entry<String, String> entry : originalMap.entrySet()) {
            storage.unset(entry.getKey());
            storage.set(entry.getValue(), entry.getKey());
        }
    }
}
// END
