package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataStorage {

    private static final ThreadLocal<Map<String, Object>> storage = ThreadLocal.withInitial(HashMap::new);

    public static Object get(String key) {
        return storage.get().get(key);
    }

    public static void put(String key, Object o) {
        storage.get().put(key, o);
    }

    public static void unload() {
        storage.remove();
    }
}
