package ua.com.alevel.view;

import ua.com.alevel.entity.Bank;

public class BankViewDto extends Bank {

    private Integer countOfClients;

    public BankViewDto() {
        this.countOfClients = 0;
    }

    public Integer getCountOfClients() {
        return countOfClients;
    }

    public void setCountOfClients(Integer countOfClients) {
        this.countOfClients = countOfClients;
    }
}
