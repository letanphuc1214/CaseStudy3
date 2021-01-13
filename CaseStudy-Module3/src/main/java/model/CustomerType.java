package model;

import java.util.List;

public class CustomerType {
    private int idCustomerType;
    private String typeCusName;
    private List<Customer> listTypeCustomer;

    public CustomerType() {
    }

    public CustomerType(int idCustomerType, String typeCusName) {
        this.idCustomerType = idCustomerType;
        this.typeCusName = typeCusName;
    }

    public CustomerType(String typeCusName) {
        this.typeCusName = typeCusName;
    }

    public int getIdCustomerType() {
        return idCustomerType;
    }

    public void setIdCustomerType(int idCustomerType) {
        this.idCustomerType = idCustomerType;
    }

    public String getTypeCusName() {
        return typeCusName;
    }

    public void setTypeCusName(String typeCusName) {
        this.typeCusName = typeCusName;
    }
}
