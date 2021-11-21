package ua.com.alevel.service.objects;

public class BankObject extends BaseObject {

    private ClientObject[] client;

    public ClientObject[] getClient() {
        return client;
    }

    public void setClient(ClientObject[] client) {
        this.client = client;
    }
}
