package ua.com.alevel.db;


import ua.com.alevel.entity.Client;

import java.util.Arrays;

public class ClientDB {
    private static ClientDB instance;

    private ClientDB() {
    }

    public static ClientDB getInstance() {
        if (instance == null) {
            instance = new ClientDB();
        }
        return instance;
    }

    private Client[] clientsDB = new Client[10];
    private int index = 0;

    public void createClient(Client client) {
        if (clientsDB[clientsDB.length - 1] != null) {
            increaseArray();
        }
        client.setId((int) System.currentTimeMillis());
        clientsDB[index] = client;
        index++;
    }

    public void updateClient(Client client) {
        int indexClient = getIndexById(client.getId());
        clientsDB[indexClient].setName(client.getName());
    }

    public Client getClientById(int id) {
        int indexClient = getIndexById(id);
        return clientsDB[indexClient];
    }

    public Client[] getAllClient() {
        Client[] clients;
        if (index > 0) {
            clients = Arrays.copyOfRange(clientsDB, 0, index);
        } else {
            clients = null;
        }
        return clients;
    }

    public void deleteClientById(int id) {
        int indexClient = getIndexById(id);
        clientsDB[indexClient] = null;
        rebuildArray(clientsDB.length);
    }

    private void increaseArray() {
        int newLength = clientsDB.length + (clientsDB.length >> 1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength) {
        Client[] newClientDb = new Client[newLength];
        int indexCount = 0;
        for (int i = 0; i < clientsDB.length; i++) {
            if (clientsDB[i] != null) {
                newClientDb[indexCount] = clientsDB[i];
                indexCount++;
            }
        }
        clientsDB = newClientDb;
        index = indexCount;
    }

    private int getIndexById(int id) {
        for (int i = 0; i < clientsDB.length; i++) {
            if (clientsDB[i] != null && clientsDB[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
