package ua.com.alevel.service;

import ua.com.alevel.dao.BankDao;
import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Bank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final BankDao DAO = new BankDao();

    public void create(Bank bank) {
        LOGGER_INFO.info("create new bank: " + bank.getTitle());
        DAO.create(bank);

    }

    public static void update(Bank bank) {
        LOGGER_INFO.info("update student: " + bank.getTitle());
        DAO.update(bank);
    }

    public void delete(int id) {
        LOGGER_WARN.warn("remove bank by id: " + id);
        DAO.delete(id);
    }

    public Bank findBankById(int id) {
        return DAO.findBankById(id);
    }

    public MyList<Bank> findAllBanks() {
        return DAO.findAllBanks();
    }

    public void updateArrayOfIdClients(int id, Bank bank) {
        MyList<Integer> array = bank.getIdClients();
        array.add(id);
        bank.setIdClients(array);
    }

}