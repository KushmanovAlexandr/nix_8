package ua.com.alevel.service.impl.util;


import ua.com.alevel.service.objects.ClientObject;

public class ClientObjectUtil {
    public static final String NAME = "ClientObjectTest";

    public static ClientObject generateClientObject() {
        ClientObject clientObject = new ClientObject();
        clientObject.setName(NAME);
        return clientObject;
    }

    public static ClientObject generateClientObject(String name) {
        ClientObject client = new ClientObject();
        client.setName(name);
        return client;
    }
}
