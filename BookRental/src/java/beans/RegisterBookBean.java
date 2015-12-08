package beans;

import dao.BookDao;
import entity.BrBook;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
public class RegisterBookBean {
    @NotNull
    @Size(max=250)
    private String title;
    
    @NotNull
    @Size(max=250)
    private String author;
    
    // XXX ここはprivateじゃだめ？
    @EJB
    BookDao dao;

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
    
    public String next(){
        create();
        return "index?faces-redirect=true";
    }
    
    public void create(){
        BrBook book = new BrBook(title,author);
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
