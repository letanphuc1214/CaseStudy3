package model;

import java.sql.Date;

public class Customer {
    private int id;
    private String fullname;
    private Date dateOfBirth;
    private String address;
    private String gender;
    private String email;
    private String phoneNumber;
    private int cmt;
    private int idProvince;
    private Province province;
    private int idCustomerType;
    private CustomerType customerType;

    public Customer() {
    }




    public Customer(int id, String fullname, Date dayOfBirth, String address, String gender, String email, String phoneNumber, int cmt, int idProvince, int idCustomerType) {
        this.id = id;
        this.fullname = fullname;
        this.dateOfBirth =dayOfBirth;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cmt = cmt;
        this.idProvince = idProvince;
        this.idCustomerType = idCustomerType;
    }

    public Customer(String fullname, Date dateOfBirth, String address, String gender, String email, String phoneNumber, int cmt, int idProvince, int idCustomerType) {
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cmt = cmt;
        this.idProvince = idProvince;
        this.idCustomerType = idCustomerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCmt() {
        return cmt;
    }

    public void setCmt(int cmt) {
        this.cmt = cmt;
    }

    public int getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public int getIdCustomerType() {
        return idCustomerType;
    }

    public void setIdCustomerType(int idCustomerType) {
        this.idCustomerType = idCustomerType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
}
