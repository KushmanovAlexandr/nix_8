package ua.com.alevel.db;


import ua.com.alevel.entity.Bank;

import java.util.Arrays;

public class BankDB {

    private static BankDB instance;

    private BankDB() {
    }

    public static BankDB getInstance() {
        if (instance == null) {
            instance = new BankDB();
        }
        return instance;
    }

    private Bank[] banksDB = new Bank[10];
    private int index = 0;

    public void createBank(Bank bank) {
        if (banksDB[banksDB.length - 1] != null) {
            increaseArray();
        }
        bank.setId((int) System.currentTimeMillis());
        banksDB[index] = bank;
        index++;
    }

    public void updateBank(Bank bank) {
        int indexBank = getIndexById(bank.getId());
        banksDB[indexBank].setName(bank.getName());
    }

    public Bank getBankById(int id) {
        int indexBank = getIndexById(id);
        return banksDB[indexBank];
    }

    public Bank[] getAllBank() {
        Bank[] banks = Arrays.copyOfRange(banksDB, 0, index);
        return banks;
    }

    public void deleteBankById(int id) {
        int indexBank = getIndexById(id);
        banksDB[indexBank] = null;
        rebuildArray(banksDB.length);
    }

    private void increaseArray() {
        int newLength = banksDB.length + (banksDB.length >> 1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength) {
        Bank[] newBankDb = new Bank[newLength];
        int indexCount = 0;
        for (int i = 0; i < banksDB.length; i++) {
            if (banksDB[i] != null) {
                newBankDb[indexCount] = banksDB[i];
                indexCount++;
            }
        }
        banksDB = newBankDb;
        index = indexCount;
    }

    private int getIndexById(int id) {
        for (int i = 0; i < banksDB.length; i++) {
            if (banksDB[i] != null && banksDB[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
