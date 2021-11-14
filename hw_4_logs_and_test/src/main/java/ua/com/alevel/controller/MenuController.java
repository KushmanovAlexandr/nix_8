package ua.com.alevel.controller;

import static ua.com.alevel.controller.ConsoleController.getInt;
import static ua.com.alevel.controller.ConsoleController.print;

public class MenuController {

    public void libraryMenu() {
        ActionsController action = new ActionsController();

        while (true) {

            print("1 - add a new bank");
            print("2 - remove the bank");
            print("3 - list of all banks ");
            print("4 - list of all clients");
            print("5 - to correct information about the bank");
            print("6 - find a bank by name");
            print("7 - find banks by client");
            print("8 - find banks by type");
            print("0 - exit");


            int input = getInt();
            switch (input) {
                case 1:
                    action.addNewBank();
                    break;
                case 2:
                    action.removeBank();
                    break;
                case 3:
                    action.takeListAllBanks();
                    break;
                case 4:
                    action.takeListAllClients();
                    break;
                case 5:
                    action.changeInfoAboutBank();
                    break;
                case 6:
                    action.findBank();
                    break;
                case 7:
                    action.findClient();
                    break;
                case 8:
                    action.findType();
                    break;
                case 0:
                    print("The End");
                    break;
                default:
                    print("Oops, try again");
                    break;
            }
            if (input == 0) break;
        }
    }
}