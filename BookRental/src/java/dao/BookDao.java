/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.BrBook;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Stateless
public class BookDao {
    @PersistenceContext
    private EntityManager em;
    
    public void create(BrBook book){
        em.persist(book);
    }
}
