package exercise;

import lombok.Value;

// BEGIN
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
