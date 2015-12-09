package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 書籍クラス
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Entity
@Table(name = "br_book")
public class BrBook implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BR_BOOK_ID_SEQ_GEN")
    @SequenceGenerator(name="BR_BOOK_ID_SEQ_GEN",sequenceName="BR_BOOK_ID_SEQ",allocationSize = 1)
    @Column(name="book_id")
    private Long bookId;
    private String title;
    private String author;

    public BrBook() {
    }

    public BrBook( String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

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

}
