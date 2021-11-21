package ua.com.alevel.db;


import ua.com.alevel.entity.ClientBank;

public class ClientBankDB {

    private static ClientBankDB instance;

    private ClientBankDB() {
    }

    public static ClientBankDB getInstance() {
        if (instance == null) {
            instance = new ClientBankDB();
        }
        return instance;
    }

    private ClientBank[] clientBanksDB = new ClientBank[10];
    private int index = 0;

    public void createClientBank(int clientId, int bankId) {
        if (clientBanksDB[clientBanksDB.length - 1] != null) {
            increaseArray();
        }
        clientBanksDB[index] = new ClientBank(clientId, bankId);
        index++;
    }

    public void deleteClientBank(int clientId, int bankId) {
        for (int i = 0; i < clientBanksDB.length; i++) {
            if (clientBanksDB[i].getClientId() == clientId && clientBanksDB[i].getBankId() == bankId) {
                clientBanksDB[i] = null;
            }
        }
        rebuildArray(clientBanksDB.length);
    }

    public int[] getBankIDsByClientIDorNull(int clientId) {
        int count = 0;
        for (int i = 0; i < clientBanksDB.length; i++) {
            if (clientBanksDB[i] != null && clientBanksDB[i].getClientId() == clientId) {
                count++;
            }
        }
        int[] bankIDs = null;
        int indexCount = 0;
        if (count > 0) {
            bankIDs = new int[count];
            for (int i = 0; i < clientBanksDB.length; i++) {
                if (clientBanksDB[i] != null && clientBanksDB[i].getClientId() == clientId) {
                    bankIDs[indexCount] = clientBanksDB[i].getBankId();
                    indexCount++;
                }
            }
        }
        return bankIDs;
    }

    public int[] getClientIDsByBankIDorNull(int banksId) {
        int count = 0;
        int[] bankIDs = null;
        int indexCount = 0;
        for (int i = 0; i < clientBanksDB.length; i++) {
            if (clientBanksDB[i] != null && clientBanksDB[i].getBankId() == banksId) {
                count++;
            }
        }
        if (count > 0) {
            bankIDs = new int[count];
            for (ClientBank client : clientBanksDB) {
                if (client.getBankId() == banksId) {
                    bankIDs[indexCount] = client.getClientId();
                    indexCount++;
                    if (indexCount == count) {
                        break;
                    }
                }
            }
        }
        return bankIDs;
    }

    public void updateListBanksByClientId(int clientId, int[] banksList) {
        int bankIndex = banksList.length;
        for (int i = 0; i < clientBanksDB.length; i++) {
            if (clientBanksDB[i] != null && clientBanksDB[i].getClientId() == clientId) {
                int indexInBanksList = getIndexById(clientBanksDB[i].getBankId(), banksList);
                if (indexInBanksList != -1) {
                    banksList[indexInBanksList] = -1;
                    bankIndex--;
                } else {
                    clientBanksDB[i] = null;
                }
            }
        }
        if (bankIndex > 0) {
            for (int j = 0; j < banksList.length; j++) {
                if (banksList[j] != -1) {
                    createClientBank(clientId, banksList[j]);
                }
            }
        }
        rebuildArray(clientBanksDB.length);
    }

    public void updateListClientsByBanktId(int bankId, int[] clientsList) {
        int clientIndex = clientsList.length;
        for (int i = 0; i < clientBanksDB.length; i++) {
            if (clientBanksDB[i] != null && clientBanksDB[i].getClientId() == bankId) {
                int indexInBanksList = getIndexById(clientBanksDB[i].getBankId(), clientsList);
                if (indexInBanksList != -1) {
                    clientsList[indexInBanksList] = -1;
                    clientIndex--;
                } else {
                    clientBanksDB[i] = null;
                }
            }
        }
        if (clientIndex > 0) {
            for (int j = 0; j < clientsList.length; j++) {
                if (clientsList[j] != -1) {
                    createClientBank(clientsList[j], bankId);
                }
            }
        }
        rebuildArray(clientBanksDB.length);
    }

    private void increaseArray() {
        int newLength = clientBanksDB.length + (clientBanksDB.length >> 1);
        rebuildArray(newLength);
    }

    private void rebuildArray(int newLength) {
        ClientBank[] newClientBankDb = new ClientBank[newLength];
        int increment = 0;
        for (int i = 0; i < clientBanksDB.length; i++) {
            if (clientBanksDB[i] != null) {
                newClientBankDb[increment] = clientBanksDB[i];
                increment++;
            }
        }
        clientBanksDB = newClientBankDb;
        index = increment;
    }

    private int getIndexById(int id, int[] ids) {
        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == id) {
                return i;
            }
        }
        return -1;
    }
}
