package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Employee.deleteAllRows", query = "DELETE from Employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private int salary;

    public Employee(String name, String address, int salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    // TODO, delete this class, or rename to an Entity class that makes sense for what you are about to do
//    // Delete EVERYTHING below if you decide to use this class, it's dummy data used for the initial demo
//    private String dummyStr1;
//    private String dummyStr2;
//
//    public Employee(String dummyStr1, String dummyStr2) {
//        this.dummyStr1 = dummyStr1;
//        this.dummyStr2 = dummyStr2;
//    }
//
//
//    public String getDummyStr1() {
//        return dummyStr1;
//    }
//
//    public void setDummyStr1(String dummyStr1) {
//        this.dummyStr1 = dummyStr1;
//    }
//
//    public String getDummyStr2() {
//        return dummyStr2;
//    }
//
//    public void setDummyStr2(String dummyStr2) {
//        this.dummyStr2 = dummyStr2;
//    }



}
