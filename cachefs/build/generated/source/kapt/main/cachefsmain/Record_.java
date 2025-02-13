
package cachefsmain;

import cachefsmain.RecordCursor.Factory;
import io.objectbox.EntityInfo;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * Properties for entity "Record". Can be used for QueryBuilder and for referencing DB names.
 */
public final class Record_ implements EntityInfo<Record> {

    // Leading underscores for static constants to avoid naming conflicts with property names

    public static final String __ENTITY_NAME = "Record";

    public static final int __ENTITY_ID = 1;

    public static final Class<Record> __ENTITY_CLASS = Record.class;

    public static final String __DB_NAME = "Record";

    public static final CursorFactory<Record> __CURSOR_FACTORY = new Factory();

    @Internal
    static final RecordIdGetter __ID_GETTER = new RecordIdGetter();

    public final static Record_ __INSTANCE = new Record_();

    public final static io.objectbox.Property<Record> ID =
        new io.objectbox.Property<>(__INSTANCE, 0, 1, long.class, "ID", true, "ID");

    public final static io.objectbox.Property<Record> ORIGINAL_ID =
        new io.objectbox.Property<>(__INSTANCE, 1, 2, String.class, "ORIGINAL_ID");

    public final static io.objectbox.Property<Record> CALLING_NUM =
        new io.objectbox.Property<>(__INSTANCE, 2, 3, String.class, "CALLING_NUM");

    public final static io.objectbox.Property<Record> CALLED_NUM =
        new io.objectbox.Property<>(__INSTANCE, 3, 4, String.class, "CALLED_NUM");

    public final static io.objectbox.Property<Record> START_TIME_I =
        new io.objectbox.Property<>(__INSTANCE, 4, 5, Long.class, "START_TIME_I");

    public final static io.objectbox.Property<Record> INSERT_TIME =
        new io.objectbox.Property<>(__INSTANCE, 5, 6, Long.class, "INSERT_TIME");

    @SuppressWarnings("unchecked")
    public final static io.objectbox.Property<Record>[] __ALL_PROPERTIES = new io.objectbox.Property[]{
        ID,
        ORIGINAL_ID,
        CALLING_NUM,
        CALLED_NUM,
        START_TIME_I,
        INSERT_TIME
    };

    public final static io.objectbox.Property<Record> __ID_PROPERTY = ID;

    @Override
    public String getEntityName() {
        return __ENTITY_NAME;
    }

    @Override
    public int getEntityId() {
        return __ENTITY_ID;
    }

    @Override
    public Class<Record> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override
    public String getDbName() {
        return __DB_NAME;
    }

    @Override
    public io.objectbox.Property<Record>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override
    public io.objectbox.Property<Record> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override
    public IdGetter<Record> getIdGetter() {
        return __ID_GETTER;
    }

    @Override
    public CursorFactory<Record> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    @Internal
    static final class RecordIdGetter implements IdGetter<Record> {
        @Override
        public long getId(Record object) {
            return object.getID();
        }
    }

}
