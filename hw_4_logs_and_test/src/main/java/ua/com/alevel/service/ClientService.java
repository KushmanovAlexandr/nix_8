package ua.com.alevel.service;


import ua.com.alevel.service.objects.BankObject;
import ua.com.alevel.service.objects.ClientObject;

public interface ClientService {

    ClientObject getClientByIdWithBankList(int id);

    ClientObject getClientByIdWithoutBankList(int id);

    void createClient(ClientObject clientObject);

    void deleteClientById(int id);

    void updateClient(ClientObject client);

    ClientObject[] getClientsListByBankOrNull(BankObject BankObject);

    ClientObject[] getAllClientObject();

}
