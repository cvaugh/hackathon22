package ver1.db;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

import ver1.Utils;

public final class DatabaseSerializer {
    private static final byte[] MAGIC_NUMBER = new byte[] { (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };
    private static final Class<?>[] SUPPORTED_ENTRY_TYPES = { String.class, Integer.class, Byte.class, Long.class,
            UUID.class };

    public static byte[] serialize(Database db) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.writeBytes(MAGIC_NUMBER);
        if(db.passwordHash == null) {
            out.writeBytes(Utils.intToBytes(0));
        } else {
            out.writeBytes(serializeString(db.passwordHash));
        }
        for(Map.Entry<String, DatabaseTable> table : db.tables.entrySet()) {
            System.out.println(table.getKey() + ": " + table.getValue());
            out.writeBytes(serializeString(table.getKey()));
            out.writeBytes(Utils.intToBytes(table.getValue().size()));
            for(String key : table.getValue().map.keySet()) {
                DatabaseEntry<?> entry = table.getValue().map.get(key);
                System.out.println(key + ": " + entry.type.getName() + ": " + entry.get());
                if(!isEntryTypeSupported(entry.type))
                    throw new RuntimeException("Unsupported entry type: " + entry.type.getName());
                out.writeBytes(serializeString(entry.type.getName()));
                if(entry.type == String.class) {
                    out.writeBytes(serializeString((String) entry.get()));
                } else if(entry.type == Integer.class) {
                    out.writeBytes(Utils.intToBytes((int) entry.get()));
                } else if(entry.type == Byte.class) {
                    out.writeBytes(new byte[] { (byte) entry.get() });
                } else if(entry.type == Long.class) {
                    out.writeBytes(Utils.longToBytes((long) entry.get()));
                } else if(entry.type == UUID.class) {
                    out.writeBytes(Utils.uuidToBytes((UUID) entry.get()));
                }
            }
        }
        return out.toByteArray();
    }

    private static boolean isEntryTypeSupported(Class<?> type) {
        for(Class<?> clazz : SUPPORTED_ENTRY_TYPES) {
            if(type == clazz) return true;
        }
        return false;
    }

    private static byte[] serializeString(String s) {
        byte[] string = s.getBytes(StandardCharsets.UTF_8);
        byte[] size = Utils.intToBytes(string.length);
        byte[] out = new byte[Integer.BYTES + string.length];
        for(int i = 0; i < Integer.BYTES; i++) {
            out[i] = size[i];
        }
        for(int i = 0; i < string.length; i++) {
            out[i + Integer.BYTES] = string[i];
        }
        return out;
    }
}
