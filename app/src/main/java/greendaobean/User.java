package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WangSiYe on 2018/12/20.
 */
@Entity
public class User {

    @Id
    private Long id;
    private String name;
    private Long age;
    @Property(nameInDb = "class")
    private String clazz;
    @Generated(hash = 1747033563)
    public User(Long id, String name, Long age, String clazz) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.clazz = clazz;
    }
    @Generated(hash = 586692638)
    public User() {
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
    public Long getAge() {
        return this.age;
    }
    public void setAge(Long age) {
        this.age = age;
    }
    public String getClazz() {
        return this.clazz;
    }
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }


}
