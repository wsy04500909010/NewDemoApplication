package greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by WangSiYe on 2018/12/19.
 */
@Entity
public class TestGreenDaoBean {
    @Id
    private Long id;
    private String date;
    private int step;
    private Long sportId;
@Generated(hash = 273675955)
    public TestGreenDaoBean(Long id, String date, int step, Long sportId) {
        this.id = id;
        this.date = date;
        this.step = step;
        this.sportId = sportId;
    }
    @Generated(hash = 2074694533)
    public TestGreenDaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getStep() {
        return this.step;
    }
    public void setStep(int step) {
        this.step = step;
    }
    public Long getSportId() {
        return this.sportId;
    }
    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
