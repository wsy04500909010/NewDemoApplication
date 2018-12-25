package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.wsy.newdemoapplication.dao.DaoSession;
import com.wsy.newdemoapplication.dao.OtherUserInfoBeanDao;
import com.wsy.newdemoapplication.dao.UserBeanDao;

/**
 * Created by WangSiYe on 2018/12/24.
 * 一对一 示例类 本表中保存一个其他表的主键
 */
@Entity
public class UserBean {
    @Id
    //必须使用包装类对象类型Long，而非基本类型long
    private Long id;
    @NotNull
    private String name;
    private int age;
    private String province;
    private boolean isMale;
    private String idCard;

    //ToOne注解使用的joinProperty = "otherUserInfoId"指的是UserBean中自定义的这个otherUserInfoId
    private Long otherUserInfoId;

    //在insert时，将otherUserInfoId同一对一关联的OtherUserInfoBean的主键id绑定
    @ToOne(joinProperty = "otherUserInfoId")
    private OtherUserInfoBean otherUserInfoBean;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 83707551)
    private transient UserBeanDao myDao;

    @Generated(hash = 359442482)
    public UserBean(Long id, @NotNull String name, int age, String province,
            boolean isMale, String idCard, Long otherUserInfoId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.province = province;
        this.isMale = isMale;
        this.idCard = idCard;
        this.otherUserInfoId = otherUserInfoId;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean getIsMale() {
        return this.isMale;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }

    public String getIdCard() {
        return this.idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getOtherUserInfoId() {
        return this.otherUserInfoId;
    }

    public void setOtherUserInfoId(Long otherUserInfoId) {
        this.otherUserInfoId = otherUserInfoId;
    }

    @Generated(hash = 4979683)
    private transient Long otherUserInfoBean__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1275005484)
    public OtherUserInfoBean getOtherUserInfoBean() {
        Long __key = this.otherUserInfoId;
        if (otherUserInfoBean__resolvedKey == null
                || !otherUserInfoBean__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OtherUserInfoBeanDao targetDao = daoSession.getOtherUserInfoBeanDao();
            OtherUserInfoBean otherUserInfoBeanNew = targetDao.load(__key);
            synchronized (this) {
                otherUserInfoBean = otherUserInfoBeanNew;
                otherUserInfoBean__resolvedKey = __key;
            }
        }
        return otherUserInfoBean;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1952804343)
    public void setOtherUserInfoBean(OtherUserInfoBean otherUserInfoBean) {
        synchronized (this) {
            this.otherUserInfoBean = otherUserInfoBean;
            otherUserInfoId = otherUserInfoBean == null ? null
                    : otherUserInfoBean.getId();
            otherUserInfoBean__resolvedKey = otherUserInfoId;
        }
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
    @Generated(hash = 1491512534)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserBeanDao() : null;
    }

    @Override
    public String toString() {
        //toString UserBean一对一关联的otherUserInfoBean时，
        //不能直接使用变量otherUserInfoBean，需要调用自动注入生成的getOtherUserInfoBean()获取关联对象
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", province='" + province + '\'' +
                ", isMale=" + isMale +
                ", idCard='" + idCard + '\'' +
                ", otherUserInfoId=" + otherUserInfoId +
                ", otherUserInfoBean=" + (getOtherUserInfoBean() == null ? "null" : getOtherUserInfoBean().toString()) +
                '}';
    }
}
