package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WangSiYe on 2018/12/25.
 */
@Entity
public class MemberBean {
    @Id
    private Long id;

    //此处自定义leaderId，用于和LeaderBean对应
    private Long leaderId;

    private String name;

    @Generated(hash = 1935793106)
    public MemberBean(Long id, Long leaderId, String name) {
        this.id = id;
        this.leaderId = leaderId;
        this.name = name;
    }

    @Generated(hash = 1592035565)
    public MemberBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaderId() {
        return this.leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MemberBean{" +
                "id=" + id +
                ", leaderId=" + leaderId +
                ", name='" + name + '\'' +
                '}';
    }
}
