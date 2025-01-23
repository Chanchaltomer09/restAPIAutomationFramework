package POJOClasses;

import java.util.ArrayList;
import java.util.List;

public class CompanyDetails {
    private String CompanyName;
    private String Street;
    private String City;
    private String State;
    private int pinCode;
    private List<employeePOJO>employees;

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public List<String> getBankAccounts() {
        return BankAccounts;
    }

    public void setBankAccounts(List<String> bankAccounts) {
        BankAccounts = bankAccounts;
    }

    public List<employeePOJO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<employeePOJO> employees) {
        this.employees = employees;
    }

    private List<String> BankAccounts;

}
