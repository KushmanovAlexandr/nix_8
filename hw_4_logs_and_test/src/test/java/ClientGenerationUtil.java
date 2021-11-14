import ua.com.alevel.entity.Client;
import ua.com.alevel.dynamicarray.MyList;

public class ClientGenerationUtil {

    public static boolean hasOneBank = true;
    public static final MyList<Integer> ID_BANKS = new MyList<>();
    public static final String FULL_NAME = "Ivanov Ivan";

    public static Client generateClient() {
        Client client = new Client();
        client.setFullName(FULL_NAME);
        client.setIdBanks(ID_BANKS);
        client.setHasOneBank(hasOneBank);
        return client;
    }

    public static Client generateClient(String name) {
        Client client = new Client();
        client.setFullName(name);
        client.setIdBanks(ID_BANKS);
        client.setHasOneBank(hasOneBank);
        return client;
    }

    public static Client generateClient(String name, MyList<Integer>idBanks) {
        Client client = new Client();
        client.setFullName(name);
        client.setIdBanks(idBanks);
        client.setHasOneBank(hasOneBank);
        return client;
    }
}