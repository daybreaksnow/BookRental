/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
    private boolean authenticated;
    
    public void login(String userCode) {
        this.userCode = userCode;
        this.authenticated = true;
    }

    public void logout() {
        this.userCode = null;
        this.authenticated = false;
    }

    public String getUserCode() {
        return userCode;
    }
}
