package package1;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by BilalAM on 12/20/2016.
 */


@Entity
@Table(name = "customer")
public class Customer implements Serializable{
    public int Id;
    public String customerName;
    public int customerAge;

    @Id
    @Column(name = "CustomerID",unique = true,nullable = false)
    public int getId() {

        return Id;
    }
    public Customer(String customerName, int customerAge ){
        setCustomerName(customerName);
        setCustomerAge(customerAge);
    }
    public Customer(){

    }

    public void setId(int id) {
        Id = id;
    }


    @Column(name ="Name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "Age")
    public int getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(int customerAge) {
        this.customerAge = customerAge;
    }
}
