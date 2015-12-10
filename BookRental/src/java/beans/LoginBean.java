/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UserDao;
import entity.BrUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ログイン画面用バッキングビーン
 *
 * @author T.IMAIZUMI
 * @since 2015/12/10
 */
@Named
@RequestScoped
public class LoginBean {

    @NotEmpty
    private String userCode;
    
    @NotEmpty
    private String password;
    
    @Inject
    private AuthHolder auth;

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
    
    public String login() {
        BrUser user = dao.find(userCode, password);
        // 認証に失敗したらログイン画面に戻す
        if (user == null) {
            return "login?faces-redirect=true";
        }
        // 認証処理に成功したら認証情報を保存しトップページへ
        this.auth.login(userCode);
        return "index?faces-redirect=true";
    }

    public String logout() {
        this.auth.logout();
        return "login?faces-redirect=true";
    }
}
