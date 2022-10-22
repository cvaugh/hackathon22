package hackathon.v1.db;

public class DatabaseEntry<T> {
    private T obj;

    public DatabaseEntry(T obj) {
        this.obj = obj;
    }

    public T get() {
        return obj;
    }

    public static <T> DatabaseEntry<T> of(Object object, Class<T> type) {
        if(!(type.isInstance(object))) {
            throw new RuntimeException(String.format("%s is not an instance of %s", object, type.getName()));
        }
        return new DatabaseEntry<T>(type.cast(object));
    }
}
