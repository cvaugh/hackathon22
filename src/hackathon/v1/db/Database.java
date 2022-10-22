package hackathon.v1.db;

import java.util.HashMap;
import java.util.Map;

public class Database {
    public final Map<String, DatabaseEntry<?>> map = new HashMap<>();
    public byte[] passwordHash;

    @SuppressWarnings("unchecked")
    public <T> DatabaseEntry<T> get(String key, Class<T> type) {
        if(!map.containsKey(key)) {
            return null;
        }
        DatabaseEntry<?> entry = map.get(key);
        if(!(type.isInstance(entry.get()))) {
            throw new RuntimeException(String.format("%s is not an instance of %s", entry.get(), type.getName()));
        }
        return (DatabaseEntry<T>) map.get(key);
    }

    public void put(String key, Object obj, Class<?> type) {
        map.put(key, DatabaseEntry.of(obj, type));
    }
}
