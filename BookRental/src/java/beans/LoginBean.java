/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UserDao;
import entity.BrUser;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * ログイン画面用バッキングビーン
 * @author T.IMAIZUMI
 * @since 2015/12/10
 */
@Named
@ViewScoped
public class LoginBean {
    @Inject
    private AuthHolder auth;

    @EJB
    private UserDao dao;
    
    public void login(String userCode, String password) {

        // ここで認証処理を行うとする
        BrUser user = dao.find(userCode, password);
        if(user != null){
            // 認証処理に成功したら認証情報を保存
            this.auth.login(userCode);
        }
    }

    public void logout() {
        this.auth.logout();
    }  
}
