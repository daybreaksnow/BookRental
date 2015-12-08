package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * 書籍クラス
 * @author T.IMAIZUMI
 * @since 2015/12/09
 */
@Entity
public class BrBook implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String title;
    private String author;

    public BrBook() {
    }

    public BrBook( String title, String author) {
        this.title = title;
        this.author = author;
    }
    
}
