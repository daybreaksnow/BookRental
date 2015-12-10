/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.BrUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * ユーザ用DAO
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Stateless
public class UserDao {
    @PersistenceContext
    private EntityManager em;
    
    /**
     * ユーザを保存する
     * @param unencryptedUser パスワードが暗号化されていないユーザ
     */
    public void create(BrUser unencryptedUser){
        // TODO UK重複の場合の対応
        unencryptedUser.setPassword(encryptPassword(unencryptedUser.getPassword()));
        em.persist(unencryptedUser);
    }
    
    public BrUser find(String userCode,String unencryptedPassword){
        String encryptedPassword = encryptPassword(unencryptedPassword);
        BrUser user = em.createNamedQuery("User.forLogin", BrUser.class)
          .setParameter("userCode", userCode)//
          .setParameter("password",encryptedPassword)//
          .getSingleResult();
        return user;
    }
    
    public static String encryptPassword(String unencryptedPassword){
        // TODO
        return unencryptedPassword;
    }
}
