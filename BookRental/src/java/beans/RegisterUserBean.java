package beans;

import dao.UserDao;
import entity.BrUser;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ユーザ登録画面のバッキングビーン
 *
 * @author T.IMAIZUMI
 * @since 2015/12/10
 */
@Named
@RequestScoped
public class RegisterUserBean {

    @NotEmpty
    @Size(max = 250)
    private String userCode;

    @NotEmpty
    @Size(max = 250)
    private String password;

    @NotEmpty
    @Size(max = 250)
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

    /**
     * 入力値を使ってユーザをDBに保存する
     *
     * @return 保存に成功したらtrue
     */
    public boolean create() {
        BrUser user = new BrUser();
        user.setUserCode(userCode);
        user.setPassword(password);
        user.setUserName(userName);
        try {
            dao.create(user);
            return true;
        } catch (Exception e) {
            String stackTrace = getStackTrace(e);

            // TODO イマイチな判定方法。UK制約のキー名があればUK違反とみなす
            if (stackTrace.contains("br_user_user_code_key")) {
                FacesContext context = FacesContext.getCurrentInstance();
                UIComponent component = context.getViewRoot().findComponent("registerUserForm:userCode");
                String clientId = component.getClientId(context);
                FacesMessage message = new FacesMessage("ユーザコードが重複しています。");
                context.addMessage(clientId, message);
            } 
            else {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return false;
        }
    }

    // TODO どこかUtilに移す
    /**
     * 例外スタックトレースをStringにする
     */
    public static String getStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        String stackTrace = sw.toString();
        return stackTrace;
    }

    public String next() {
        boolean success = create();
        // 保存に成功したら入力をクリアして開き直す
        if (success) {
            return "register_user?faces-redirect=true";
        } 
        // エラーメッセージが表示できるようそのまま返す
        else {
            return null;
        }
    }
}
