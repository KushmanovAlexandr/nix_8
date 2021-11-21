package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.BankDao;
import ua.com.alevel.db.BankDB;
import ua.com.alevel.db.ClientBankDB;
import ua.com.alevel.entity.Bank;

public class BankDAOImpl implements BankDao {

    private BankDB bankDB = BankDB.getInstance();
    private ClientBankDB clientBankDB = ClientBankDB.getInstance();

    @Override
    public Bank getBankById(int id) {
        return bankDB.getBankById(id);
    }

    @Override
    public void createBank(Bank bank) {
        bankDB.createBank(bank);
    }

    @Override
    public void deleteBankById(int id) {
        bankDB.deleteBankById(id);
        clientBankDB.updateListClientsByBanktId(id, new int[0]);
    }

    @Override
    public void updateBank(Bank bank) {
        bankDB.updateBank(bank);
    }

    @Override
    public void updateBank(Bank bank, int[] clientListIds) {
        bankDB.updateBank(bank);
        clientBankDB.updateListClientsByBanktId(bank.getId(), clientListIds);
    }

    @Override
    public Bank[] getBanksListByClientIdOrNull(int clientId) {
        int[] bankIdDsList = clientBankDB.getBankIDsByClientIDorNull(clientId);
        Bank[] banksList = null;
        if (bankIdDsList != null && bankIdDsList.length > 0) {
            banksList = new Bank[bankIdDsList.length];
            for (int i = 0; i < bankIdDsList.length; i++) {
                banksList[i] = bankDB.getBankById(bankIdDsList[i]);
            }
        }
        return banksList;
    }

    @Override
    public Bank[] getAllBanks() {
        return bankDB.getAllBank();
    }
}
