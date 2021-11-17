import org.junit.jupiter.api.*;
import ua.com.alevel.dynamicarray.MyList;
import ua.com.alevel.entity.Bank;
import ua.com.alevel.entity.Client;
import ua.com.alevel.service.BankService;
import ua.com.alevel.service.ClientService;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class  TestService {
    private static final int BANK_SIZE = 32;
    MyList<Bank> banks = new MyList<>();
    MyList<Client> clients = new MyList<>();
    BankService bankService = new BankService();
    ClientService clientService = new ClientService();
    Bank bank = BankGenerationUtil.generateBank();

    @Test
    @Order(1)
    public  void deleteBank(){
        BankService bankService = new BankService();

        for (int i = 1; i < bankService.findAllBanks().getLength(); i++) {
            bankService.delete(i);
        }
        System.out.println(bankService.findAllBanks().getLength());

        Assertions.assertNotEquals(BANK_SIZE, bankService.findAllBanks().getLength());
    }

    @Test
    @Order(2)
    public void upBank(){
        BankService bankService = new BankService();
        for (int i = 0; i < BANK_SIZE; i++) {
            bankService.create(bank);
        }
        Assertions.assertEquals(BANK_SIZE, bankService.findAllBanks().getLength());
    }


    @Test
    @Order(3)
    public void createBank() {

        Bank bank = BankGenerationUtil.generateBank("MonoBank");
        bankService.create(bank);
        MyList<Bank> users = bankService.findAllBanks();
        Assertions.assertNotEquals(BANK_SIZE, banks.getLength());
    }

    @Test
    @Order(4)
    public void findBankByTitle(){
        for (int i = 1; i < banks.length; i++) {
            if (banks.get(i).getTitle().equals("MegaBank")) {
                Assertions.assertEquals(banks.get(i), "MegaBank");
            }
        }
    }@Test
    @Order(5)
    public void findClientByName(){
        Client client = ClientGenerationUtil.generateClient("Victor Shevchenko");
        clientService.create(client);
        for (int i = 1; i < clients.length; i++) {
            if (clients.get(i).getFullName().equals("Victor Shevchenko")) {
                Assertions.assertEquals(clients.get(i), "Victor Shevchenko");
            }
        }
    }
}