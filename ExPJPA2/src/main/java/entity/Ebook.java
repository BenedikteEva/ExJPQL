
package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("E")
@Entity
public class Ebook extends Book {
    
//Explain: If I added this annotation @Column(nullable = false) 
// on top of  the downloadUrl field, the Inheritance Strategy single table
// would fail if I actually tried to insert a Book or a PaperBook 
// because a paperback does not have an url and therefore would be null and i cant)  
    private String downLoadURl;
    private double sizeMB;

    public Ebook() {
    }

    public Ebook(String downLoadURl, double sizeMB) {
        this.downLoadURl = downLoadURl;
        this.sizeMB = sizeMB;
    }

   

    public String getDownLoadURl() {
        return downLoadURl;
    }

    public void setDownLoadURl(String downLoadURl) {
        this.downLoadURl = downLoadURl;
    }

    public double getSizeMB() {
        return sizeMB;
    }

    public void setSizeMB(double sizeMB) {
        this.sizeMB = sizeMB;
    }
}
