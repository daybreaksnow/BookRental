/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.BrBook;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

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
    
    public List<BrBook> findAll(){
        CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
        criteria.select(criteria.from(BrBook.class));
        return em.createQuery(criteria).getResultList();
    }
}
