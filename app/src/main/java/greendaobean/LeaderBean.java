package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.wsy.newdemoapplication.dao.DaoSession;
import com.wsy.newdemoapplication.dao.MemberBeanDao;
import com.wsy.newdemoapplication.dao.LeaderBeanDao;

/**
 * Created by WangSiYe on 2018/12/25.
 */
@Entity
public class LeaderBean {
    @Id
    private Long id;
    private String name;

    //此处的leaderId是在MemberBean中定义的一个变量（请看下面的MemberBean.java）
    @ToMany(referencedJoinProperty = "leaderId")
    private List<MemberBean> memberBeanList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 703242250)
    private transient LeaderBeanDao myDao;

    @Generated(hash = 590590377)
    public LeaderBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1761610276)
    public LeaderBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1515955432)
    public List<MemberBean> getMemberBeanList() {
        if (memberBeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MemberBeanDao targetDao = daoSession.getMemberBeanDao();
            List<MemberBean> memberBeanListNew = targetDao
                    ._queryLeaderBean_MemberBeanList(id);
            synchronized (this) {
                if (memberBeanList == null) {
                    memberBeanList = memberBeanListNew;
                }
            }
        }
        return memberBeanList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 73665005)
    public synchronized void resetMemberBeanList() {
        memberBeanList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1648395956)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLeaderBeanDao() : null;
    }

    @Override
    public String toString() {
        String memberBeanListStr = "";
        if (getMemberBeanList().size() > 0) {
            for (MemberBean memberBean : getMemberBeanList()) {
                memberBeanListStr = memberBeanListStr + memberBean.toString() + ";";
            }
        }else{
            memberBeanListStr = "null";
        }


        return "LeaderBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memberBeanList=" + memberBeanListStr +
                '}';
    }
}
