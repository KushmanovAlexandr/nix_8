package ua.com.alevel.entity;

public class Bank extends BaseEntity {

    private BankName name;

    public BankName getName() {
        return name;
    }

    public void setName(BankName name) {
        this.name = name;
    }
}
