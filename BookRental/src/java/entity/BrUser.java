package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * ユーザクラス
 * @author T.IMAIZUMI
 * @since 2015/12/10
 */
@Entity
@Table(name = "br_user")
@NamedQueries({
  @NamedQuery(name = BrUser.QUERY_FOR_LOGIN, 
     query = "Select user from BrUser user where user.userCode = :userCode and user.password = :password"),
})
public class BrUser implements Serializable{
    public static final String QUERY_FOR_LOGIN="forLogin";
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BR_USER_ID_SEQ_GEN")
    @SequenceGenerator(name="BR_USER_ID_SEQ_GEN",sequenceName="BR_USER_ID_SEQ",allocationSize = 1)
    @Column(name="user_id")
    private Long userId;
    
    @NotNull
    @Column(name="user_code",unique = true)
    private String userCode;
    
    @NotNull
    private String password;
    
    @NotNull
    @Column(name="user_name")
    private String userName;
    
    @Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name="update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Long version;
    

    public BrUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
