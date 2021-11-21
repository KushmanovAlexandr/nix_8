package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.ClientDao;
import ua.com.alevel.dao.impl.ClientDAOImpl;
import ua.com.alevel.entity.Client;
import ua.com.alevel.service.BankService;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.service.objects.BankObject;
import ua.com.alevel.service.objects.ClientObject;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private BankService bankService;
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    public ClientServiceImpl() {
        this.clientDao = new ClientDAOImpl();
    }

    @Override
    public ClientObject getClientByIdWithBankList(int id) {
        Client client = clientDao.getClientById(id);
        ClientObject clientObject = new ClientObject();
        clientObject.setId(client.getId());
        clientObject.setName(client.getName());
        this.bankService = new BankServiceImpl();
        clientObject.setBank(bankService.getBanksListByClientOrNull(clientObject));
        return clientObject;
    }

    @Override
    public ClientObject getClientByIdWithoutBankList(int id) {
        Client client = clientDao.getClientById(id);
        ClientObject clientObject = new ClientObject();
        clientObject.setId(client.getId());
        clientObject.setName(client.getName());
        return clientObject;
    }

    @Override
    public void createClient(ClientObject clientObject) {
        if (isClientNameValid(clientObject.getName())) {
            LOGGER_INFO.info("create new client: " + clientObject.getName());
            Client client = new Client();
            client.setId(clientObject.getId());
            client.setName(clientObject.getName());
            clientDao.createClient(client);
        } else {
            LOGGER_WARN.info("Entered not valid name: " + clientObject.getName() + "\non create");
        }
    }

    @Override
    public void deleteClientById(int id) {
        LOGGER_INFO.info("delete client id: " + id);
        clientDao.deleteClientById(id);
    }

    @Override
    public void updateClient(ClientObject clientObject) {
        if (isClientNameValid(clientObject.getName())) {
            LOGGER_INFO.info("update client id: " + clientObject.getId());
            Client client = new Client();
            client.setId(clientObject.getId());
            client.setName(clientObject.getName());
            if (clientObject.getBank() != null && clientObject.getBank().length > 0) {
                int[] banks = new int[clientObject.getBank().length];
                for (int i = 0; i < clientObject.getBank().length; i++) {
                    if (clientObject.getBank()[i] != null) {
                        banks[i] = clientObject.getBank()[i].getId();
                    } else {
                        banks[i] = -1;
                    }
                }
                clientDao.updateClient(client, getFixedArray(banks));
            } else {
                clientDao.updateClient(client);
            }
        } else {
            LOGGER_WARN.info("Entered not valid name: " + clientObject.getName() + "\non update");
        }
    }

    @Override
    public ClientObject[] getClientsListByBankOrNull(BankObject bankObject) {
        Client[] clients = clientDao.getClientListByBankIdOrNull(bankObject.getId());
        if (clients != null) {
            ClientObject[] clientObjects = new ClientObject[clients.length];
            for (int i = 0; i < clients.length; i++) {
                clientObjects[i] = new ClientObject();
                clientObjects[i].setId(clients[i].getId());
                clientObjects[i].setName(clients[i].getName());
            }
            return clientObjects;
        } else {
            LOGGER_WARN.info("clients list by bank id " + bankObject.getId() + " is empty");
            return null;
        }
    }

    @Override
    public ClientObject[] getAllClientObject() {
        Client[] clients = clientDao.getAllClients();
        if (clients != null) {
            ClientObject[] clientObjects = new ClientObject[clients.length];
            for (int i = 0; i < clients.length; i++) {
                clientObjects[i] = new ClientObject();
                clientObjects[i].setId(clients[i].getId());
                clientObjects[i].setName(clients[i].getName());
            }
            return clientObjects;
        } else {
            LOGGER_WARN.info("clients list is empty");
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

    private boolean isClientNameValid(String name) {
        return !name.matches("[^a-zA-z]");
    }
}
