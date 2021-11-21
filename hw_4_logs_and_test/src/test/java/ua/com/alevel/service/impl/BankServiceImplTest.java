package ua.com.alevel.service.impl;

import org.junit.jupiter.api.*;
import ua.com.alevel.service.BankService;
import ua.com.alevel.service.ClientService;
import ua.com.alevel.service.impl.util.BankObjectUtil;
import ua.com.alevel.service.impl.util.ClientObjectUtil;
import ua.com.alevel.service.objects.BankObject;
import ua.com.alevel.service.objects.ClientObject;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankServiceImplTest {

    private final static ClientService clientservice = new ClientServiceImpl();
    private final static BankService bankService = new BankServiceImpl();
    private final static int CLIENTS_SIZE = 5;
    private final static int BANK_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        for (BankObject co : bankObjects) {
            bankService.deleteBankById(co.getId());
        }
        for (int i = 0; i < CLIENTS_SIZE; i++) {
            ClientObject clientObject = ClientObjectUtil.generateClientObject("test " + i);
            clientservice.createClient(clientObject);
        }
        for (int i = 0; i < BANK_SIZE; i++) {
            BankObject bankObject = BankObjectUtil.generateBankObject("test " + i);
            bankService.createBank(bankObject);
        }
        Assertions.assertEquals(BANK_SIZE, bankService.getAllBankObject().length);
    }

    @AfterAll
    public static void clearAll() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        for (BankObject co : bankObjects) {
            bankService.deleteBankById(co.getId());
        }
    }

    @Test
    @Order(0)
    void getAllBankObject() {
        BankObject[] bankObjects = bankService.getAllBankObject();
        Assertions.assertEquals(BANK_SIZE, bankObjects.length);
    }

    @Test
    @Order(1)
    void updateBank() {
        BankObject bankObject = bankService.getAllBankObject()[0];
        String testName = "TestUpdate";
        bankObject.setName(testName);
        bankObject.setClient(clientservice.getAllClientObject());
        bankService.updateBank(bankObject);
        BankObject bankObjectAfterUpdate = bankService.getBankByIdWithoutBanks(bankObject.getId());
        Assertions.assertEquals(testName, bankObjectAfterUpdate.getName());
    }

    @Test
    @Order(2)
    void getBankByIdWithBanks() {
        BankObject bankObject = bankService.getAllBankObject()[0];
        BankObject bankObjectById = bankService.getBankByIdWithBanks(bankObject.getId());
        Assertions.assertEquals(CLIENTS_SIZE, bankObjectById.getClient().length);
    }

    @Test
    @Order(3)
    void getBankByIdWithoutBanks() {
        BankObject bankObject = bankService.getAllBankObject()[0];
        BankObject bankObjectById = bankService.getBankByIdWithoutBanks(bankObject.getId());
        Assertions.assertEquals(null, bankObjectById.getClient());
    }

    @Test
    @Order(4)
    void getBanksListByClientOrNull() {
        ClientObject clientObject = clientservice.getAllClientObject()[0];
        BankObject[] bankObjects = bankService.getBanksListByClientOrNull(clientObject);
        Assertions.assertNotEquals(null, bankObjects);
    }

    @Test
    @Order(5)
    void createBank() {
        BankObject bankObject = BankObjectUtil.generateBankObject("testCreate");
        bankService.createBank(bankObject);
        BankObject[] bankObjects = bankService.getAllBankObject();
        Assertions.assertEquals(BANK_SIZE + 1, bankObjects.length);

    }

    @Test
    @Order(6)
    void deleteBankById() {
        BankObject bankObject = bankService.getAllBankObject()[0];
        bankService.deleteBankById(bankObject.getId());
        BankObject[] bankObjects = bankService.getAllBankObject();
        Assertions.assertEquals(BANK_SIZE, bankObjects.length);
    }


}