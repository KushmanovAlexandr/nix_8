package ua.com.alevel.dao;

import ua.com.alevel.db.ArrayDB;
import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Bank;

public class BankDao {
    public void create(Bank bank) {
        ArrayDB.getInstance().create(bank);
    }

    public void update(Bank bank) {
        ArrayDB.getInstance().update(bank);
    }

    public void delete(int id) {
        ArrayDB.getInstance().delete(id, ArrayDB.Entity.BANK);
    }

    public Bank findBankById(int id) {
        return ArrayDB.getInstance().findBankById(id);
    }

    public MyList<Bank> findAllBanks() {
        return ArrayDB.getInstance().findAllBanks();
    }
}