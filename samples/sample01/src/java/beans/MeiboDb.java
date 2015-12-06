/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MeiboDb {
    @PersistenceContext
    private EntityManager em;
    
    public void create(Meibo meibo){
        em.persist(meibo);
    }
}
