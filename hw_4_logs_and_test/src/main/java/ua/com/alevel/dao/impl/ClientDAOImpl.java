package ua.com.alevel.dao.impl;


import ua.com.alevel.dao.ClientDao;
import ua.com.alevel.db.ClientBankDB;
import ua.com.alevel.db.ClientDB;
import ua.com.alevel.entity.Client;

public class ClientDAOImpl implements ClientDao {
    private ClientDB clientDB = ClientDB.getInstance();
    private ClientBankDB clientBankDB = ClientBankDB.getInstance();

    @Override
    public Client getClientById(int id) {
        return clientDB.getClientById(id);
    }

    @Override
    public void createClient(Client client) {
        clientDB.createClient(client);
    }

    @Override
    public void deleteClientById(int id) {
        clientDB.deleteClientById(id);
        clientBankDB.updateListBanksByClientId(id, new int[0]);
    }

    @Override
    public void updateClient(Client client) {
        clientDB.updateClient(client);
    }

    @Override
    public void updateClient(Client client, int[] bankListIds) {
        clientDB.updateClient(client);
        clientBankDB.updateListBanksByClientId(client.getId(), bankListIds);
    }

    @Override
    public Client[] getClientListByBankIdOrNull(int bankId) {
        int[] clientIdDsList = clientBankDB.getClientIDsByBankIDorNull(bankId);
        Client[] clientsList = null;
        if (clientIdDsList != null && clientIdDsList.length > 0) {
            clientsList = new Client[clientIdDsList.length];
            for (int i = 0; i < clientIdDsList.length; i++) {
                clientsList[i] = clientDB.getClientById(clientIdDsList[i]);
            }
        }
        return clientsList;
    }

    @Override
    public Client[] getAllClients() {
        return clientDB.getAllClient();
    }
}
