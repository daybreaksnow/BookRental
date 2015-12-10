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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

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
    
    /**
     * ID降順で返す
     * @return 
     */
    public List<BrBook> findAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BrBook> query = cb.createQuery(BrBook.class);
        Root<BrBook> root = query.from(BrBook.class);
        // TODO メタモデルを使う
        query.select(root)
         .orderBy(cb.desc(root.get("bookId")));
        return em.createQuery(query).getResultList();
    }
}
