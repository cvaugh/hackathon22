package ver1.db;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import ver1.Utils;

public class Database {
    public final Map<String, DatabaseTable> tables = new HashMap<>();
    public String passwordHash;

    public Database(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Database() {
        this(null);
    }

    public DatabaseTable getTable(String name) {
        return tables.get(name);
    }

    public boolean addTable(String name) {
        if(tables.containsKey(name)) return false;
        tables.put(name, new DatabaseTable(name));
        return true;
    }

    public DatabaseTable dropTable(String name) {
        return tables.remove(name);
    }

    public boolean hasPassword() {
        return passwordHash != null;
    }

    public boolean checkPassword(String password, byte[] salt) {
        return Utils.sha512(salt, password.getBytes(StandardCharsets.UTF_8)).equals(passwordHash);
    }
}
