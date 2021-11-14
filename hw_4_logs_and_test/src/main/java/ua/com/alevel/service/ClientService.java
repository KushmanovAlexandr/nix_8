package ua.com.alevel.service;

import ua.com.alevel.dao.ClientDao;
import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientService {
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private final ClientDao clientDao = new ClientDao();

    public int create(Client client) {
        MyList<Client> clients = findAllClients();
        for (int i = 0; i < clients.length; i++) {
            if (clients.get(i) != null) {
                if (clients.get(i).getFullName().equals(client.getFullName())) {
                    LOGGER_INFO.info("add new bank to client.IdBanks: " + client.getFullName());
                    updateArrayOfIdBanks(client.getIdBanks, clients.get(i));
                    clients.get(i).setHasOneBank(false);
                    return clients.get(i).getId();
                }
            }
        }
        LOGGER_INFO.info("create new client: " + client.getFullName());
        clientDao.create(client);
        return client.getId();
    }

    private void updateArrayOfIdBanks(int idBank, Client clientInBD) {
        MyList<Integer> array = clientInBD.getIdBanks();
        array.get(idBank);
        clientInBD.setIdBanks(array);
    }

    public void update(Client client) {
        clientDao.update(client);
    }

    public void delete(int id) {
        LOGGER_WARN.warn("remove client by id: " + id);
        clientDao.delete(id);
    }

    public Client findClientById(int id) {
        return clientDao.findClientById(id);
    }

    public MyList<Client> findAllClients() {
        return clientDao.findAllClients();
    }
}


