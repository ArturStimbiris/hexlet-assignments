package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(car);
        Files.write(path, json.getBytes());
    }

    public static Car extract(Path path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String json = new String(Files.readAllBytes(path));
        return mapper.readValue(json, Car.class);
    }
}
// END
