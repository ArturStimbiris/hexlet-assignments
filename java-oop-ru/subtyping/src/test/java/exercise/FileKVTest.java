package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;

class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Test
    void testFileKV() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
        assertThat(storage.get("key", "default")).isEqualTo("value");
        assertThat(storage.get("unknown", "default")).isEqualTo("default");

        storage.set("key2", "value2");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");

        storage.unset("key2");
        assertThat(storage.get("key2", "default")).isEqualTo("default");

        Map<String, String> data = storage.toMap();
        assertThat(data).isEqualTo(Map.of("key", "value"));
    }
}
