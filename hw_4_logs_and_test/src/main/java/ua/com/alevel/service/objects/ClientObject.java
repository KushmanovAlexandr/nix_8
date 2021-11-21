package ua.com.alevel.service.objects;

public class ClientObject extends BaseObject {

    private BankObject[] bank;

    public BankObject[] getBank() {
        return bank;
    }

    public void setBank(BankObject[] bank) {
        this.bank = bank;
    }
}
