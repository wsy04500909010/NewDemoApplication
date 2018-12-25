package com.wsy.newdemoapplication.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import greendaobean.MemberBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MEMBER_BEAN".
*/
public class MemberBeanDao extends AbstractDao<MemberBean, Long> {

    public static final String TABLENAME = "MEMBER_BEAN";

    /**
     * Properties of entity MemberBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property LeaderId = new Property(1, Long.class, "leaderId", false, "LEADER_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
    }

    private Query<MemberBean> leaderBean_MemberBeanListQuery;

    public MemberBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MemberBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MEMBER_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LEADER_ID\" INTEGER," + // 1: leaderId
                "\"NAME\" TEXT);"); // 2: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MEMBER_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MemberBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long leaderId = entity.getLeaderId();
        if (leaderId != null) {
            stmt.bindLong(2, leaderId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MemberBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long leaderId = entity.getLeaderId();
        if (leaderId != null) {
            stmt.bindLong(2, leaderId);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MemberBean readEntity(Cursor cursor, int offset) {
        MemberBean entity = new MemberBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // leaderId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MemberBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLeaderId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MemberBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MemberBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MemberBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "memberBeanList" to-many relationship of LeaderBean. */
    public List<MemberBean> _queryLeaderBean_MemberBeanList(Long leaderId) {
        synchronized (this) {
            if (leaderBean_MemberBeanListQuery == null) {
                QueryBuilder<MemberBean> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.LeaderId.eq(null));
                leaderBean_MemberBeanListQuery = queryBuilder.build();
            }
        }
        Query<MemberBean> query = leaderBean_MemberBeanListQuery.forCurrentThread();
        query.setParameter(0, leaderId);
        return query.list();
    }

}
