package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    
    private String publisher;
    
    @Column(name="publication_date")
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    
    @Column(name="total_num")
    private Integer totalNum;
    
    @Column(name="reserved_num")
    private Integer reservedNum;
    
    @Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name="update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Long version;
    

    public BrBook() {
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

    public Integer getReservedNum() {
        return reservedNum;
    }

    public void setReservedNum(Integer reservedNum) {
        this.reservedNum = reservedNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
}
