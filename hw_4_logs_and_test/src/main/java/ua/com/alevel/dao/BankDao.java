package ua.com.alevel.dao;


import ua.com.alevel.entity.Bank;

public interface BankDao {

    Bank getBankById(int id);

    void createBank(Bank bank);

    void deleteBankById(int id);

    void updateBank(Bank bank);

    void updateBank(Bank bank, int[] clientListIds);

    Bank[] getBanksListByClientIdOrNull(int clientId);

    Bank[] getAllBanks();
}
