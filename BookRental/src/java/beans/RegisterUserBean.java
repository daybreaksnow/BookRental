package beans;

import dao.UserDao;
import entity.BrUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ユーザ登録画面のバッキングビーン
 * @author T.IMAIZUMI
 * @since 2015/12/10
 */
@Named
@RequestScoped
public class RegisterUserBean {
    @NotEmpty
    @Size(max=250)
    private String userCode;
    
    @NotEmpty
    @Size(max=250)
    private String password;

    @NotEmpty
    @Size(max=250)
    private String userName;
    
    @EJB
    private UserDao dao;

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
    
    public void create(){
        BrUser user = new BrUser();
        user.setUserCode(userCode);
        user.setPassword(password);
        user.setUserName(userName);
        try{
            dao.create(user);
        }
//         何が来るのかよくわからないので適当。
//         汎用的なエラー処理はこのクラスの責務ではない
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public String next(){
        create();
        return "register_user?faces-redirect=true";
    }
}
