/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 貸出情報クラス
 * @author T.IMAIZUMI
 * @since 2015/12/14
 */
@Entity
@Table(name = "br_rental")
public class BrRental implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "BR_RENTAL_ID_SEQ_GEN")
    @SequenceGenerator(name="BR_RENTAL_ID_SEQ_GEN",sequenceName="BR_RENTAL_ID_SEQ",allocationSize = 1)
    @Column(name="rental_id")
    private Long rentalId;
    
//    @Column(name="book_id")
//    private Long bookId;
    
    @ManyToOne
    @JoinColumn(name="book_id",referencedColumnName = "book_id")
    private BrBook book;

//    @Column(name="rental_user_code")
//    private String rentalUserCode;
    
    @ManyToOne
    @JoinColumn(name="rental_user_code",referencedColumnName = "user_code")
    private BrUser rentalUser;
    
    @Column(name="rental_date")
    @Temporal(TemporalType.DATE)
    private Date rentalDate;

    @Column(name="return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    
    private Long version;

    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }
//
//    public Long getBookId() {
//        return bookId;
//    }
//
//    public void setBookId(Long bookId) {
//        this.bookId = bookId;
//    }
    
    public BrBook getBook() {
        return book;
    }

    public void setBook(BrBook book) {
        this.book = book;
    }
//
//    public String getRentalUserCode() {
//        return rentalUserCode;
//    }
//
//    public void setRentalUserCode(String rentalUserCode) {
//        this.rentalUserCode = rentalUserCode;
//    }

    public BrUser getRentalUser() {
        return rentalUser;
    }

    public void setRentalUser(BrUser rentalUser) {
        this.rentalUser = rentalUser;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    
}
