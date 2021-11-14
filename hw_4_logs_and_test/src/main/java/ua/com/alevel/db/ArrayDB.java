package ua.com.alevel.db;

import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Client;
import ua.com.alevel.entity.Bank;

import java.util.Random;

public class ArrayDB {

    private static ArrayDB instance = new ArrayDB();
    MyList<Client> clients = new MyList<>();
    MyList<Bank> banks = new MyList<>();


    private ArrayDB() {
    }

    public static ArrayDB getInstance() {
        if (instance == null) {
            instance = new ArrayDB();
        }
        return instance;
    }

    public void create(Bank bank) {
        bank.setId(generateId(Entity.BANK));
        banks.add(bank);

    }

    public void update(Bank bank) {
        Bank currentBank = findBankById(bank.getId());
        currentBank.setId(bank.getId());
        currentBank.setType(bank.getType());
        currentBank.setCountOfBranches(bank.getCountOfBranches());

    }

    public void delete(int id, Entity entity) {
        switch (entity) {
            case BANK:
                for (int i = 0; i < banks.length; i++) {
                    if (banks.get(i).getId() == id) {
                        banks.remove(i);
                        break;
                    }
                }
                break;
            case CLIENT:
                for (int i = 0; i < clients.length; i++) {
                    if (clients.get(i).getId() == id) {
                        clients.remove(i);
                        break;
                    }
                }
                break;
        }
    }

    public Bank findBankById(int id) {
        for (int i = 0; i < banks.length; i++) {
            if (banks.get(i).getId() == id) {
                return banks.get(i);
            }
        }
        return null;
    }


    public MyList<Bank> findAllBanks() {
        return banks;
    }


    public void create(Client client) {
        client.setId(generateId(Entity.CLIENT));
        clients.add(client);
    }

    public void update(Client client) {
        Client inDbClient = findClientById(client.getId());
        inDbClient.setFullName(client.getFullName());
    }

    public Client findClientById(int id) {
        for (int i = 0; i < clients.getLength(); i++) {
            if (clients.get(i) == null) continue;
            if (clients.get(i).getId() == id) return clients.get(i);
        }
        return null;
    }

    public MyList<Client> findAllClients() {
        return clients;
    }


    private int generateId(Entity entity) {
        int id = new Random().nextInt();
        switch (entity) {
            case BANK:
                for (int i = 0; i < banks.length; i++) {
                    if (banks.get(i) == null) continue;
                    if (banks.get(i).getId() == id) return generateId(Entity.BANK);
                }
                break;
            case CLIENT:
                for (int i = 0; i < clients.length; i++) {
                    if (clients.get(i) == null) continue;
                    if (clients.get(i).getId() == id) return generateId(Entity.CLIENT);
                }
                break;
        }
        return id;
    }


    public enum Entity {
        CLIENT, BANK
    }

    public void delete(int id, MyList<Integer> idArray) {
        for (int i = 0; i < idArray.length; i++) {
            if (idArray.get(i).equals(id)) {
                idArray.remove(i);
                break;
            }
        }
    }
}