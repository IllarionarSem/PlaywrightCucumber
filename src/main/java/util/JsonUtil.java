package util;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonUtil {

    public static <T> T getObject(String body, Class<T> clazz) {
        return new Gson().fromJson(body, clazz);
    }

    public static <T> List<T> getObjects(String body, Type type) {
        return new Gson().fromJson(body, type);
    }

    public static String toJson(Object o) {
        return new Gson().toJson(o);
    }
}