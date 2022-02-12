package ua.com.alevel.view;

import ua.com.alevel.entity.Client;

public class ClientViewDto extends Client {

    private int countOfBanks;

    public int getCountOfBanks() {
        return countOfBanks;
    }

    public void setCountOfBanks(int countOfBanks) {
        this.countOfBanks = countOfBanks;
    }
}
