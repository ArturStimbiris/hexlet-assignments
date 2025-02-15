package exercise;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;
// BEGIN
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void testFileKV() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));

        assertThat(storage.get("key", "default")).isEqualTo("value");
        assertThat(storage.get("unknown", "default")).isEqualTo("default");

        storage.set("key2", "value2");
        assertThat(storage.get("key2", "default")).isEqualTo("value2");

        storage.unset("key2");
        assertThat(storage.get("key2", "default")).isEqualTo("default");

        assertThat(storage.toMap()).isEqualTo(Map.of("key", "value"));
    }

    @Test
    void testFileKVPersistence() throws Exception {
    KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));

    assertThat(storage.get("key", "default")).isEqualTo("value");

    storage.set("key2", "value2");
    assertThat(storage.get("key2", "default")).isEqualTo("value2");

    KeyValueStorage newStorage = new FileKV(filepath.toString(), Map.of());
    assertThat(newStorage.get("key", "default")).isEqualTo("value");
    assertThat(newStorage.get("key2", "default")).isEqualTo("value2");
}

    @Test
    void testFileKVInitialData() {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("foo", "bar", "bar", "zoo"));

        assertThat(storage.get("foo", "default")).isEqualTo("bar");
        assertThat(storage.get("bar", "default")).isEqualTo("zoo");

        assertThat(storage.toMap()).isEqualTo(Map.of("foo", "bar", "bar", "zoo"));
    }
    // END
}
