
import ua.com.alevel.entity.Client;
import ua.com.alevel.entity.Bank;
import ua.com.alevel.dynamicarray.MyList;

public class BankGenerationUtil {

    public static boolean hasOneClient = true;
    public static final MyList<Integer> ID_Client = new MyList<>();
    public static final int COUNT_OF_BRANCH = 10;
    public static final String TITLE = "PrivatBank";
    public static final String TYPE = "State";

    public static Bank generateBank() {
        Bank bank = new Bank();
        bank.setTitle(TITLE);
        bank.setIdClients(ID_Client);
        bank.setCountOfBranches(COUNT_OF_BRANCH);
        bank.setType(TYPE);
        bank.setHasOneClient(hasOneClient);
        return bank;
    }

    public static Bank generateBank(String title) {
        Bank bank = new Bank();
        bank.setTitle(TITLE);
        bank.setIdClients(ID_Client);
        bank.setCountOfBranches(COUNT_OF_BRANCH);
        bank.setType(TYPE);
        bank.setHasOneClient(hasOneClient);
        return bank;
    }

    public static Bank generateBank(String title, int countOfPage) {
        Bank bank = new Bank();
        bank.setTitle(TITLE);
        bank.setIdClients(ID_Client);
        bank.setCountOfBranches(COUNT_OF_BRANCH);
        bank.setType(TYPE);
        bank.setHasOneClient(hasOneClient);
        return bank;
    }

    public static Bank generateBank(String title, String type) {
        Bank bank = new Bank();
        bank.setTitle(TITLE);
        bank.setIdClients(ID_Client);
        bank.setCountOfBranches(COUNT_OF_BRANCH);
        bank.setType(TYPE);
        bank.setHasOneClient(hasOneClient);
        return bank;
    }

    public static Bank generateBank(String title, int countOfPage, String type, String name) {
        Bank bank = new Bank();
        bank.setTitle(TITLE);
        bank.setIdClients(ID_Client);
        bank.setCountOfBranches(countOfPage);
        bank.setType(TYPE);
        bank.setHasOneClient(hasOneClient);
        generateClient(name);
        return bank;
    }

    private static Client generateClient(String name) {
        Client client = new Client();
        client.setFullName(name);
        return client;
    }
}