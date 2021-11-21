package ua.com.alevel.service.impl.util;


import ua.com.alevel.service.objects.BankObject;

public class BankObjectUtil {
    public static final String NAME = "BankTest";

    public static BankObject generateBankObject() {
        BankObject bankObject = new BankObject();
        bankObject.setName(NAME);
        return bankObject;
    }

    public static BankObject generateBankObject(String name) {
        BankObject bankObject = new BankObject();
        bankObject.setName(name);
        return bankObject;
    }
}
