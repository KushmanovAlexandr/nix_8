package ua.com.alevel.dao;

import ua.com.alevel.entity.Client;

public interface ClientDao {

    Client getClientById(int id);

    void createClient(Client client);

    void deleteClientById(int id);

    void updateClient(Client client);

    void updateClient(Client client, int[] bankListIds);

    Client[] getClientListByBankIdOrNull(int bankId);

    Client[] getAllClients();
}
