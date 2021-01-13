package model;

import java.util.List;

public class Province {
    private int idProvince;
    private String ProvinceName;
    private List<Customer> listCustomer;

    public Province() {
    }

    public Province(int idProvince, String provinceName) {
        this.idProvince = idProvince;
        ProvinceName = provinceName;
    }

    public Province(String provinceName) {
        ProvinceName = provinceName;
    }

    public int getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(int idProvince) {
        this.idProvince = idProvince;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }
}
