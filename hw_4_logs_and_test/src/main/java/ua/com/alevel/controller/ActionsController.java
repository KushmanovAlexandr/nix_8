package ua.com.alevel.controller;

import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Client;
import ua.com.alevel.entity.Bank;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.service.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static ua.com.alevel.controller.ConsoleController.*;

public class ActionsController {

    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    BankService bankService = new BankService();
    ClientService clientService = new ClientService();
    MyList<Integer> newArray = new MyList<>();

    public void addNewBank() {
        print("Enter the name of the bank:");
        String title = getString();
        print("Enter the type of bank ownership:");
        String type = getString();
        print("Enter the number of bank branches:");
        int countOfBranches = getInt();
        print("Input name client of bank:");
        String fullName = getString();

        Client client = new Client();
        Bank bank = new Bank();
        bank.setTitle(title);
        bank.setType(type);
        bank.setCountOfBranches(countOfBranches);
        bankService.create(bank);
        client.setFullName(fullName);
        client.setIdBanks(newArray.add(bank.getId()));
        int clientId = clientService.create(client);
        bank.setIdClients(newArray.add(clientId));

        print("This bank has more clients? +/-?");
        print("if yes enter (+)" + ",\n" +
                "if no enter (-)");
        String answer = getString();
        boolean isMoreClients = isMoreClients(answer);
        while (isMoreClients) {
            addMoreClients(client);
            print("This bank has more clients? +/-?");
            print("if yes enter (+)" + ",\n" +
                    "if no enter (-)");
            answer = getString();
            isMoreClients = isMoreClients(answer);
        }
    }

    private void addMoreClients(Client bank) {
        print("Enter the name of the client:");
        String fullName = getString();
        Client client = new Client();
        client.setFullName(fullName);
        client.setIdBanks(newArray.add(bank.getId()));
        int clientId = clientService.create(client);
        bankService.updateArrayOfIdClients(clientId, client);
        client.setHasOneBank(false);
    }

    public void removeBank() {
        Client client;
        print("Enter the name of the bank:");
        String title = getString();
        MyList<Bank> banks = bankService.findAllBanks();
        try{
            for (int i = 0; i < banks.length; i++) {
                if (banks.get(i).getTitle().equals(title)) {
                    MyList<Integer> idClients = banks.get(i).getIdClients();
                    bankService.delete(banks.get(i).getId());
                    for (int j = 0; j < idClients.length; j++) {
                        if (idClients.get(j) == 0) continue;
                        client = clientService.findClientById(idClients.get(j));
                        if (client.isHasOneBank()) {
                            clientService.delete(client.getId());
                            break;
                        }
                        else {
                            MyList<Integer> idBanks = client.getIdBanks();
                            for (int k = 0; k < idBanks.length; k++) {
                                if (idBanks.get(k) == banks.get(i).getId()) {
                                    idBanks.remove(k);
                                    client.setIdBanks(idBanks);
                                    break;
                                }
                            }

                        }
                    }
                }
            }
        }catch (NullPointerException e ){
            LOGGER_ERROR.error(e.getMessage() + "removeBank");
        }
    }

    public void takeListAllBanks() {
        print("list of all banks:");
        MyList<Bank> banks = bankService.findAllBanks();
        for (int i = 0; i < banks.length; i++) {
            printBank(banks.get(i));
        }

    }

    public void takeListAllClients() {
        print("list of all clients:");
        MyList<Client> clients = clientService.findAllClients();
        for (int i = 0; i < clients.length; i++) {
            printClient(clients.get(i));
        }
    }

    public void changeInfoAboutBank() {

        try {
            System.out.println("Please, enter name");
            String title = getString();
            System.out.println("Please, enter type");
            String type = getString();
            Bank bank = new Bank();

            bank.setTitle(title);
            bank.setType(type);
            BankService.update(bank);
        }catch (NullPointerException e){
            LOGGER_ERROR.error(e.getMessage() + " changeInfoAboutBank");
        }

    }

    public void findBank() {
        print("Enter the name of the bank:");
        String title = getString();
        MyList<Bank> banks = bankService.findAllBanks();
        for (int i = 0; i < banks.length; i++) {
            if (banks.get(i).getTitle().equals(title)) {
                System.out.println(banks.get(i).toString());

            }else {
                print("We cannot find this bank.");
            }
        }

    }

    public void findType() {
        boolean isFind = false;
        print("Input a type of bank:");
        String type = getString();
        MyList<Bank> banks = bankService.findAllBanks();
        for (int i = 0; i < banks.length; i++) {
            if (banks.get(i).getType().equals(type)) {
                printBank(banks.get(i));
                isFind = true;
            }
        }
        if (!isFind) {
            print("this bank does not exist.");
        }
    }

    public void findClient() {
        boolean isFind = false;
        print("Enter the name of the bank:");
        String fullName = getString();
        MyList<Client> clients = clientService.findAllClients();
        for (int i = 0; i < clients.length; i++) {
            if (clients.get(i).getFullName().equals(fullName)) {
                printClient(clients.get(i));
                isFind = true;
            }
        }
        if (!isFind) {
            print("We cannot find this client.");
        }
    }

    private void printBank(Bank bank) {
        try{
            MyList<Integer> idClients = bank.getIdClients();
            print(bank.toString());
            for (int j = 0; j < idClients.length; j++) {
                if (idClients.get(j) == 0) continue;
                print("- client's full name: " + clientService.findClientById(idClients.get(j)).getFullName());
            }
            print("");
        }catch (NullPointerException e){
            LOGGER_ERROR.error(e.getMessage() + " printBank");
        }
    }

    private void printClient(Client client) {
        try {
            print(client.toString());
            MyList<Integer> idBanks = client.getIdBanks();
            for (int j = 0; j < idBanks.length; j++) {
                if (idBanks.get(j) == 0) continue;
                print("- title: " + bankService.findBankById(idBanks.get(j)).getTitle());
            }
            print("");

        }catch(NullPointerException e){
            LOGGER_ERROR.error(e.getMessage() + " printClient");
        }

    }

    private boolean isMoreClients(String answer) {
        if (answer.equals("+")) {
            return true;
        }
        else if (answer.equals("-")) {
            return false;
        }
        else {
            print("Please, enter only + or -:");
            String newAnswer = getString();
            return isMoreClients(newAnswer);
        }
    }
}