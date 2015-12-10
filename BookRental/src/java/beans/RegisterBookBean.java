package beans;

import dao.BookDao;
import entity.BrBook;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 書籍登録画面のバッキングビーン
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Named
@RequestScoped
public class RegisterBookBean {
    @NotEmpty
    @Size(max=250)
    private String title;
    
    @NotEmpty
    @Size(max=250)
    private String author;

    @NotEmpty
    private String publisher;
    
    @NotNull
    private Date publicationDate;
    
    @NotNull
    @Min(1)
    private Integer totalNum;
    
    @EJB
    private BookDao dao;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String next(){
        create();
        return "index?faces-redirect=true";
    }
    
    public void create(){
        BrBook book = new BrBook();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setPublicationDate(publicationDate);
        book.setTotalNum(totalNum);
        book.setReservedNum(0);
        try{
            dao.create(book);
        }
        // 何が来るのかよくわからないので適当。
        // 汎用的なエラー処理はこのクラスの責務ではない
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
