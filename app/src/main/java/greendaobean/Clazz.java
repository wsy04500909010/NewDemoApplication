package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WangSiYe on 2018/12/20.
 */
@Entity
public class Clazz {
    @Id
    private Long id;
    private String name;
    private Long person_number;
    @Generated(hash = 83088104)
    public Clazz(Long id, String name, Long person_number) {
        this.id = id;
        this.name = name;
        this.person_number = person_number;
    }
    @Generated(hash = 1166360579)
    public Clazz() {
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
    public Long getPerson_number() {
        return this.person_number;
    }
    public void setPerson_number(Long person_number) {
        this.person_number = person_number;
    }

}
