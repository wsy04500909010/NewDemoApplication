package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WangSiYe on 2018/12/24.
 * 一对一 示例类 本表主键作为其他表的外键 无需特殊注解
 */
@Entity
public class OtherUserInfoBean {
    @Id
    //必须使用包装类对象类型Long，而非基本类型long
    private Long id;
    private String address;
    private String tel;
    private Date birthday;


    @Generated(hash = 647485055)
    public OtherUserInfoBean(Long id, String address, String tel, Date birthday) {
        this.id = id;
        this.address = address;
        this.tel = tel;
        this.birthday = birthday;
    }


    @Generated(hash = 59605238)
    public OtherUserInfoBean() {
    }


    @Override
    public String toString() {
        return "OtherUserInfoBean{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", birthday=" + birthday +
                '}';
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getAddress() {
        return this.address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public String getTel() {
        return this.tel;
    }


    public void setTel(String tel) {
        this.tel = tel;
    }


    public Date getBirthday() {
        return this.birthday;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
