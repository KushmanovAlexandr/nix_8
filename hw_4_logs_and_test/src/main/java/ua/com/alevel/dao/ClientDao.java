package ua.com.alevel.dao;

import ua.com.alevel.db.ArrayDB;
import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Client;

public class ClientDao {

    public void create(Client client) {
        ArrayDB.getInstance().create(client);
    }

    public void update(Client client) {
        ArrayDB.getInstance().update(client);
    }

    public void delete(int id) {
        ArrayDB.getInstance().delete(id, ArrayDB.Entity.CLIENT);
    }

    public Client findClientById(int id) {
        return ArrayDB.getInstance().findClientById(id);
    }

    public MyList<Client> findAllClients() {
        return ArrayDB.getInstance().findAllClients();
    }

}