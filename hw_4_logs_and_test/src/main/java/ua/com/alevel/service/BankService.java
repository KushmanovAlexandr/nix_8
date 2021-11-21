package ua.com.alevel.service;


import ua.com.alevel.service.objects.BankObject;
import ua.com.alevel.service.objects.ClientObject;

public interface BankService {
    BankObject getBankByIdWithBanks(int id);

    BankObject getBankByIdWithoutBanks(int id);

    void createBank(BankObject bank);

    void deleteBankById(int id);

    void updateBank(BankObject bank);

    BankObject[] getBanksListByClientOrNull(ClientObject client);

    BankObject[] getAllBankObject();
}
