package beans;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.emptyType;
import dao.BookDao;
import dao.UserDao;
import entity.BrBook;
import entity.BrRental;
import entity.BrUser;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 書籍登録画面のバッキングビーン
 *
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

    public List<BrBook> getAll() {
        return dao.findAll();
    }

    public void rentalBook(Long bookId) {
        BrBook book = dao.find(bookId);
        book.setReservedNum(book.getReservedNum() + 1);
        if (book.getTotalNum() < book.getReservedNum()) {
            throw new IllegalStateException("貸出可能数を超えています");
        }
        BrRental rental = new BrRental();
        rental.setBook(book);
        rental.setRentalUser(userDao.find(auth.getUserCode()));
        rental.setRentalDate(new Date());
        // TODO 自動で入れる
        rental.setVersion(0L);
        book.getRentals().add(rental);
        dao.update(book);
    }

    public void returnBook(Long bookId) {
        BrBook book = dao.find(bookId);
        book.setReservedNum(book.getReservedNum() - 1);
        BrRental returnRenal = null;
        for (BrRental rental : book.getRentals()) {
            if(rental.getRentalUser().getUserCode().equals(auth.getUserCode()) && rental.getReturnDate() == null){
                returnRenal = rental;
                break;
            }
        }
        if(returnRenal == null){
            throw new IllegalStateException("借りていません");
        }
        returnRenal.setReturnDate(new Date());
        dao.update(book);
    }

    public String getRentalUsers(Long bookId) {
        BrBook book = dao.find(bookId);
        StringBuilder sb = new StringBuilder();
        // 貸出中のユーザ名を,で繋いで出力する
        final String delimiter = ",";
        Set<BrRental> rentals = book.getRentals();
        for (BrRental rental : rentals) {
            // 返却済みは除く
            if(rental.getReturnDate() != null){
                continue;
            }
            BrUser rentalUser = rental.getRentalUser();
            sb.append(rentalUser.getUserName());
            sb.append(delimiter);
        }
        if (sb.length() == 0) {
            return "";
        }
        String result = sb.toString();
        // 末尾の,を消す
        return result.substring(0, result.length() - delimiter.length());

    }

    public boolean canRental(Long bookId) {
        // TODO 最初からSQLで検索したい
        BrBook book = dao.find(bookId);
        Set<BrRental> rentals = book.getRentals();
        for (BrRental rental : rentals) {
            // すでに借りている
            if (rental.getRentalUser().getUserCode().equals(auth.getUserCode()) && rental.getReturnDate() == null) {
                return false;
            }
        }
        return true;

    }

    public boolean canReturn(Long bookId) {
        return !canRental(bookId);
    }

}
