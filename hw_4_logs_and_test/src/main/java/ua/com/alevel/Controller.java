package ua.com.alevel;


import ua.com.alevel.service.BankService;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.service.impl.BankServiceImpl;
import ua.com.alevel.service.impl.ClientServiceImpl;
import ua.com.alevel.service.objects.BankObject;
import ua.com.alevel.service.objects.ClientObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private ClientService clientService = new ClientServiceImpl();
    private BankService bankService = new BankServiceImpl();

    public void startController() {
        creatDB();
        boolean isContinue = true;
        while (isContinue) {
            System.out.println(" 1 - create client");
            System.out.println(" 2 - get client");
            System.out.println(" 3 - update client");
            System.out.println(" 4 - add bank to client");
            System.out.println(" 5 - delete client");
            System.out.println(" 6 - create bank");
            System.out.println(" 7 - get bank");
            System.out.println(" 8 - update bank");
            System.out.println(" 9 - add client to bank");
            System.out.println(" 10 - delete bank");
            System.out.println(" 0 - exit");
            System.out.println("enter your choice: ");
            int choice = integerReader();
            switch (choice) {
                case 1: {
                    createClient();
                }
                break;
                case 2: {
                    getClient();
                }
                break;
                case 3: {
                    updateClient();
                }
                break;
                case 4: {
                    addBankToClient();
                }
                break;
                case 5: {
                    deleteClient();
                }
                break;
                case 6: {
                    createBank();
                }
                break;
                case 7: {
                    getBank();
                }
                break;
                case 8: {
                    updateBank();
                }
                break;
                case 9: {
                    addClientToBank();
                }
                break;
                case 10: {
                    deleteBank();
                }
                break;
                case 0: {
                    isContinue = false;
                }
                break;
            }
        }
    }

    public void createClient() {
        System.out.println("enter client name");
        String clientName = getStringFromConsole();
        ClientObject client = new ClientObject();
        client.setName(clientName);
        clientService.createClient(client);
    }

    public void createBank() {
        System.out.println("enter bank name");
        String clientName = getStringFromConsole();
        BankObject bank = new BankObject();
        bank.setName(clientName);
        bankService.createBank(bank);
    }

    public void deleteClient() {
        ClientObject[] clientObjects = clientService.getAllClientObject();
        for (int i = 0; i < clientObjects.length; i++) {
            System.out.println("id = " + clientObjects[i].getId() + " name = " + clientObjects[i].getName());
        }
        System.out.print("enter client id for delete:");
        int id = integerReader();
        clientService.deleteClientById(id);
    }

    public void deleteBank() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        for (int i = 0; i < bankObjects.length; i++) {
            System.out.println("id = " + bankObjects[i].getId() + " name = " + bankObjects[i].getName());
        }
        System.out.println("enter bank id for delete");
        int id = integerReader();
        bankService.deleteBankById(id);
    }

    public void updateClient() {
        ClientObject[] clientObjects = clientService.getAllClientObject();

        for (int i = 0; i < clientObjects.length; i++) {
            System.out.println("id = " + clientObjects[i].getId() + " name = " + clientObjects[i].getName());
        }
        System.out.print("enter client id for update:");
        int id = integerReader();
        ClientObject client = clientService.getClientByIdWithoutBankList(id);
        System.out.print("enter new bank name");
        client.setName(getStringFromConsole());
        clientService.updateClient(client);
    }

    public void updateBank() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        for (int i = 0; i < bankObjects.length; i++) {
            System.out.println("id = " + bankObjects[i].getId() + " name = " + bankObjects[i].getName());
        }
        System.out.println("enter bank id for update");
        int id = integerReader();
        BankObject bankObject = bankService.getBankByIdWithoutBanks(id);
        System.out.print("enter new bank name");
        bankObject.setName(getStringFromConsole());
        bankService.updateBank(bankObject);
    }

    public void getClient() {
        ClientObject[] clientObjects = clientService.getAllClientObject();
        for (int i = 0; i < clientObjects.length; i++) {
            System.out.println("id = " + clientObjects[i].getId() + " name = " + clientObjects[i].getName());
        }
        System.out.print("enter client id");
        int id = integerReader();
        ClientObject clientObject = clientService.getClientByIdWithBankList(id);
        System.out.println("Client id = " + clientObject.getId() + " bank name = " + clientObject.getName());
        if (clientObject.getBank() != null) {
            System.out.println("bank list on bank:");
            for (int i = 0; i < clientObject.getBank().length; i++) {
                System.out.println("id = " + clientObject.getBank()[i].getId() + " name = " + clientObject.getBank()[i].getName());
            }
        }
    }

    public void getBank() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        for (int i = 0; i < bankObjects.length; i++) {
            System.out.println("id = " + bankObjects[i].getId() + " name = " + bankObjects[i].getName());
        }
        System.out.println("enter bank id ");
        int id = integerReader();
        BankObject bankObject = bankService.getBankByIdWithBanks(id);
        System.out.println("Bank id = " + bankObject.getId() + " bank name = " + bankObject.getName());
        if (bankObject.getClient() != null) {
            System.out.println("client list on bank:");
            for (int i = 0; i < bankObject.getClient().length; i++) {
                System.out.println("id = " + bankObject.getClient()[i].getId() + " name = " + bankObject.getClient()[i].getName());
            }
        }
    }

    public void addBankToClient() {
        ClientObject[] clientObjects = clientService.getAllClientObject();
        for (int i = 0; i < clientObjects.length; i++) {
            System.out.println("id = " + clientObjects[i].getId() + " name = " + clientObjects[i].getName());
        }
        System.out.print("enter client id");
        int id = integerReader();
        ClientObject clientObject = clientService.getClientByIdWithBankList(id);
        System.out.println("Client id = " + clientObject.getId() + " bank name = " + clientObject.getName());
        if (clientObject.getBank() != null) {
            System.out.println("bank list on bank:");
            for (int i = 0; i < clientObject.getBank().length; i++) {
                System.out.println("id = " + clientObject.getBank()[i].getId() + " name = " + clientObject.getBank()[i].getName());
            }
        }
        BankObject[] bankObjects = bankService.getAllBankObject();
        System.out.println("all bank list");
        for (int i = 0; i < bankObjects.length; i++) {
            System.out.println("id = " + bankObjects[i].getId() + " name = " + bankObjects[i].getName());
        }
        System.out.print("enter id to add:");
        int bankId = integerReader();
        boolean isNotContainBank = true;
        BankObject[] bankObjects1;
        if (clientObject.getBank() != null) {
            for (int i = 0; i < clientObject.getBank().length; i++) {
                if (clientObject.getBank()[i].getId() == bankId) {
                    isNotContainBank = false;
                    break;
                }
            }
            if (isNotContainBank) {
                bankObjects1 = new BankObject[clientObject.getBank().length + 1];
                for (int i = 0; i < clientObject.getBank().length; i++) {
                    bankObjects1[i] = clientObject.getBank()[i];
                }
                bankObjects1[bankObjects1.length - 1] = bankService.getBankByIdWithoutBanks(bankId);
                clientObject.setBank(bankObjects1);
                clientService.updateClient(clientObject);
            }
        } else {
            bankObjects1 = new BankObject[1];
            bankObjects1[0] = bankService.getBankByIdWithoutBanks(bankId);
            clientObject.setBank(bankObjects1);
            clientService.updateClient(clientObject);
        }
    }

    public void addClientToBank() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        for (int i = 0; i < bankObjects.length; i++) {
            System.out.println("id = " + bankObjects[i].getId() + " name = " + bankObjects[i].getName());
        }
        System.out.println("enter bank id ");
        int id = integerReader();
        BankObject bankObject = bankService.getBankByIdWithBanks(id);
        System.out.println("Bank id = " + bankObject.getId() + " bank name = " + bankObject.getName());
        if (bankObject.getClient() != null) {
            System.out.println("client list on bank:");
            for (int i = 0; i < bankObject.getClient().length; i++) {
                System.out.println("id = " + bankObject.getClient()[i].getId() + " name = " + bankObject.getClient()[i].getName());
            }
        }
        ClientObject[] clientObjects = clientService.getAllClientObject();
        System.out.println("all clients list");
        for (int i = 0; i < clientObjects.length; i++) {
            System.out.println("id = " + clientObjects[i].getId() + " name = " + clientObjects[i].getName());
        }
        System.out.print("enter id to add:");
        int clientId = integerReader();
        boolean isNotContainsClient = true;
        if (bankObject.getClient() != null) {
            for (int i = 0; i < bankObject.getClient().length; i++) {
                if (bankObject.getClient()[i].getId() == clientId) {
                    isNotContainsClient = false;
                    break;
                }
            }
            if (isNotContainsClient) {
                ClientObject[] clientObjects1 = new ClientObject[bankObject.getClient().length + 1];
                for (int i = 0; i < bankObject.getClient().length; i++) {
                    clientObjects1[i] = bankObject.getClient()[i];
                }
                clientObjects1[clientObjects1.length - 1] = clientService.getClientByIdWithoutBankList(clientId);
                bankObject.setClient(clientObjects1);
                bankService.updateBank(bankObject);
            }
        } else {
            ClientObject[] clientObjects1 = new ClientObject[1];
            clientObjects1[0] = clientService.getClientByIdWithoutBankList(clientId);
            bankObject.setClient(clientObjects1);
            bankService.updateBank(bankObject);
        }
    }

    private int integerReader() {
        String choice = getStringFromConsole().replaceAll("[ \\t\\n\\x0B\\f\\r]", "").replace("[^0-9]", "");
        if (choice.length() > 0) {
            return Integer.parseInt(choice);
        }
        return 0;
    }

    private String getStringFromConsole() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void creatDB() {
        String name = "test ";
        for (int i = 0; i < 10; i++) {
            ClientObject clientObject = new ClientObject();
            clientObject.setName(name);
            clientService.createClient(clientObject);
            BankObject bankObject = new BankObject();
            bankObject.setName(name + i);
            bankService.createBank(bankObject);
        }
    }
}
