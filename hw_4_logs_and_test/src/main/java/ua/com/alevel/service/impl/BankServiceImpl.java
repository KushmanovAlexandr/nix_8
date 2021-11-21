package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.BankDao;
import ua.com.alevel.dao.impl.BankDAOImpl;
import ua.com.alevel.entity.Bank;
import ua.com.alevel.service.BankService;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.service.objects.BankObject;
import ua.com.alevel.service.objects.ClientObject;

public class BankServiceImpl implements BankService {
    BankDao bankDao;
    ClientService clientService;

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public BankServiceImpl() {
        this.bankDao = new BankDAOImpl();

    }

    @Override
    public BankObject getBankByIdWithBanks(int id) {
        Bank bank = bankDao.getBankById(id);
        BankObject bankObject = new BankObject();
        bankObject.setId(bank.getId());
        bankObject.setName(bank.getName());
        this.clientService = new ClientServiceImpl();
        bankObject.setClient(clientService.getClientsListByBankOrNull(bankObject));
        return bankObject;
    }

    @Override
    public BankObject getBankByIdWithoutBanks(int id) {
        Bank bank = bankDao.getBankById(id);
        BankObject bankObject = new BankObject();
        bankObject.setId(bank.getId());
        bankObject.setName(bank.getName());
        return bankObject;
    }

    @Override
    public void createBank(BankObject bankObject) {
        if (isBankNameValid(bankObject.getName())) {
            LOGGER_INFO.info("create new bank: " + bankObject.getName());
            Bank bank = new Bank();
            bank.setId(bankObject.getId());
            bank.setName(bankObject.getName());
            bankDao.createBank(bank);
        } else {
            LOGGER_WARN.info("Entered not valid name: " + bankObject.getName() + "\non create");
        }

    }

    @Override
    public void deleteBankById(int id) {
        LOGGER_INFO.info("delete bank id: " + id);
        bankDao.deleteBankById(id);
    }

    @Override
    public void updateBank(BankObject bankObject) {
        if (isBankNameValid(bankObject.getName())) {
            LOGGER_INFO.info("update bank id: " + bankObject.getId());
            Bank bank = new Bank();
            bank.setId(bankObject.getId());
            bank.setName(bankObject.getName());
            if (bankObject.getClient() != null && bankObject.getClient().length > 0) {
                int[] clients = new int[bankObject.getClient().length];
                for (int i = 0; i < bankObject.getClient().length; i++) {
                    if (bankObject.getClient()[i] != null) {
                        clients[i] = bankObject.getClient()[i].getId();
                    } else {
                        clients[i] = -1;
                    }
                }
                bankDao.updateBank(bank, getFixedArray(clients));
            } else {
                bankDao.updateBank(bank);
            }
        } else {
            LOGGER_WARN.info("Entered not valid name: " + bankObject.getName() + "\non update");
        }
    }

    @Override
    public BankObject[] getBanksListByClientOrNull(ClientObject clientObject) {
        Bank[] banks = bankDao.getBanksListByClientIdOrNull(clientObject.getId());
        if (banks != null) {
            BankObject[] bankObjects = new BankObject[banks.length];
            for (int i = 0; i < banks.length; i++) {
                bankObjects[i] = new BankObject();
                bankObjects[i].setId(banks[i].getId());
                bankObjects[i].setName(banks[i].getName());
            }
            return bankObjects;
        } else {
            LOGGER_WARN.info("banks list by client id " + clientObject.getId() + " is empty");
            return null;
        }
    }

    @Override
    public BankObject[] getAllBankObject() {
        Bank[] banks = bankDao.getAllBanks();
        if (banks != null) {
            BankObject[] bankObjects = new BankObject[banks.length];
            for (int i = 0; i < banks.length; i++) {
                bankObjects[i] = new BankObject();
                bankObjects[i].setId(banks[i].getId());
                bankObjects[i].setName(banks[i].getName());
            }
            return bankObjects;
        } else {
            LOGGER_WARN.info("banks list is empty");
            return null;
        }
    }

    private int[] getFixedArray(int[] intArray) {
        int[] result = intArray;
        int countNotValidCell = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < 0) {
                countNotValidCell++;
            }
        }
        if (countNotValidCell > 0) {
            result = new int[intArray.length - countNotValidCell];
            int index = 0;
            for (int i = 0; i < intArray.length; i++) {
                if (intArray[i] > 0) {
                    result[index] = intArray[i];
                    index++;
                }
            }
        }
        return result;
    }

    private boolean isBankNameValid(String name) {
        return !name.matches("[^a-zA-z0-9\\-_/]");
    }
}
