package exercise;
import exercise.MinLength;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// BEGIN
public class Validator {

    public static List<String> validate(Object obj) {
        List<String> notValidFields = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(obj) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> errors = new HashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            List<String> fieldErrors = new ArrayList<>();

            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    if (field.get(obj) == null) {
                        fieldErrors.add("can not be null");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                int minLength = minLengthAnnotation.minLength();
                try {
                    String value = (String) field.get(obj);
                    if (value != null && value.length() < minLength) {
                        fieldErrors.add("length less than " + minLength);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            if (!fieldErrors.isEmpty()) {
                errors.put(field.getName(), fieldErrors);
            }
        }

        return errors;
    }
}
// END
