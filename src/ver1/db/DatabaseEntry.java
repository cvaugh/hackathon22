package ver1.db;

public class DatabaseEntry<T> {
    protected final Class<T> type;
    private T obj;

    private DatabaseEntry(T obj, Class<T> type) {
        this.obj = obj;
        this.type = type;
    }

    public T get() {
        return obj;
    }

    public <U> U get(Class<U> type) {
        if(!(type.isInstance(obj))) {
            throw new RuntimeException(String.format("%s is not an instance of %s", obj, type.getName()));
        }
        return type.cast(obj);
    }

    public String toString() {
        return "DatabaseEntry<" + type.getName() + ">";
    }

    public static <T> DatabaseEntry<T> of(Object object, Class<T> type) {
        if(!(type.isInstance(object))) {
            throw new RuntimeException(String.format("%s is not an instance of %s", object, type.getName()));
        }
        return new DatabaseEntry<T>(type.cast(object), type);
    }
}
