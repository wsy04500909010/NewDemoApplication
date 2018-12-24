package com.wsy.newdemoapplication.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import greendaobean.TestGreenDaoBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TEST_GREEN_DAO_BEAN".
*/
public class TestGreenDaoBeanDao extends AbstractDao<TestGreenDaoBean, Long> {

    public static final String TABLENAME = "TEST_GREEN_DAO_BEAN";

    /**
     * Properties of entity TestGreenDaoBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Date = new Property(1, String.class, "date", false, "DATE");
        public final static Property Step = new Property(2, int.class, "step", false, "STEP");
        public final static Property SportId = new Property(3, Long.class, "sportId", false, "SPORT_ID");
    }


    public TestGreenDaoBeanDao(DaoConfig config) {
        super(config);
    }
    
    public TestGreenDaoBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TEST_GREEN_DAO_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DATE\" TEXT," + // 1: date
                "\"STEP\" INTEGER NOT NULL ," + // 2: step
                "\"SPORT_ID\" INTEGER);"); // 3: sportId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TEST_GREEN_DAO_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TestGreenDaoBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
        stmt.bindLong(3, entity.getStep());
 
        Long sportId = entity.getSportId();
        if (sportId != null) {
            stmt.bindLong(4, sportId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TestGreenDaoBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(2, date);
        }
        stmt.bindLong(3, entity.getStep());
 
        Long sportId = entity.getSportId();
        if (sportId != null) {
            stmt.bindLong(4, sportId);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TestGreenDaoBean readEntity(Cursor cursor, int offset) {
        TestGreenDaoBean entity = new TestGreenDaoBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // date
            cursor.getInt(offset + 2), // step
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3) // sportId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TestGreenDaoBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStep(cursor.getInt(offset + 2));
        entity.setSportId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TestGreenDaoBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TestGreenDaoBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TestGreenDaoBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}