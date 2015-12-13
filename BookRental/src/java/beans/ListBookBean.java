package beans;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import dao.BookDao;
import dao.UserDao;
import entity.BrBook;
import entity.BrRental;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 書籍登録画面のバッキングビーン
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Named
@RequestScoped
public class ListBookBean {
    @EJB
    private BookDao dao;

    @EJB
    private UserDao userDao;
    
    @Inject
    private AuthHolder auth;
    
    public List<BrBook> getAll(){
        return dao.findAll();
    }
    
    public void rentalBook(Long bookId){
          BrBook book = dao.find(bookId);
          book.setReservedNum(book.getReservedNum() + 1);
          if(book.getTotalNum()< book.getReservedNum()){
              throw new IllegalStateException("貸出可能数を超えています");
          }
          BrRental rental = new BrRental();
          rental.setBook(book);
          rental.setRentalUser(userDao.find(auth.getUserCode()));
//          rental.setBookId(bookId);
//          rental.setRentalUserCode(auth.getUserCode());
          rental.setRentalDate(new Date());
          // TODO 自動で入れる
          rental.setVersion(0L);
          book.getRentals().add(rental);
          dao.update(book);
    }
    
    public void returnBook(Long bookId){
        //TODO
    }
}
