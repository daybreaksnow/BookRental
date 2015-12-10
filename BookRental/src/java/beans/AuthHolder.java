/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * ログイン情報の保持クラス
 * @author T.IMAIZUMI
 * @since 2015/12/10
 */
@Named
@SessionScoped
public class AuthHolder implements Serializable{
    private String userCode;
    
    public void login(String userCode) {
        this.userCode = userCode;
    }

    public void logout() {
        this.userCode = null;
    }

    public String getUserCode() {
        return userCode;
    }
    
    public boolean isLoggedIn(){
        return userCode != null;
    }
    
    /**
     * ログイン状態でなければログイン画面に飛ばす
     */
    public void checkLogin(){
        if (!isLoggedIn()) {
            ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)
                FacesContext.getCurrentInstance()
                    .getApplication().getNavigationHandler();
            handler.performNavigation("login");
        }
    }
}
