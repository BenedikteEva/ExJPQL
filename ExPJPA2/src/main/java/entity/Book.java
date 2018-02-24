/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author Ejer
 */
@Entity
@DiscriminatorColumn(name="BTYPE")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long ISBN;
    private String title;
    private String author;
    private double price;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getISBN() {
        return ISBN;
    }

    public void setISBN(Long ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ISBN != null ? ISBN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the ISBN fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.ISBN == null && other.ISBN != null) || (this.ISBN != null && !this.ISBN.equals(other.ISBN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Book[ ISBN=" + ISBN + " ]";
    }

    public void setSizeMB(double d) {
        
    }

    public void setDownLoadURl(String httpswwwsaxocomdkbookofmagicinpractice_pd) {
      
    }

}
