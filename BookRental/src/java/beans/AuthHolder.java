/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

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
        // Session Fixation対策。セッション id を変更
        // http://d.hatena.ne.jp/hagi44/20150122/1421929254
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        request.changeSessionId();
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
