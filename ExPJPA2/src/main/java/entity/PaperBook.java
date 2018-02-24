
package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@DiscriminatorValue("P")
@Entity
public class PaperBook extends Book {
    
    private double shippingWeight;
    private int inStock;

    public PaperBook() {
    }

    public double getShippingWeight() {
        return shippingWeight;
    }

    public void setShippingWeight(double shippingWeight) {
        this.shippingWeight = shippingWeight;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }
}
