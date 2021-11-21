package ua.com.alevel.entity;

public class ClientBank {
    private int clientId;
    private int bankId;

    public ClientBank() {
    }

    public ClientBank(int clientId, int bankId) {
        this.clientId = clientId;
        this.bankId = bankId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        clientId = clientId;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        bankId = bankId;
    }

}
