package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.BankDao;
import ua.com.alevel.entity.Bank;
import ua.com.alevel.entity.BankName;
import ua.com.alevel.store.ConnectionStoreFactory;
import ua.com.alevel.view.BankViewDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankDaoImpl implements BankDao {

    private final ConnectionStoreFactory storeFactory;

    private static final String FIND_ALL_VIEW_BANKS_QUERY = "select c.id, c.name, count(bank_id) from banks as c\n" +
            "    left join clients_banks as sc on c.id = sc.bank_id\n" +
            "group by c.id";

    private static final String FIND_ALL_VIEW_BANKS_BY_CLIENT_QUERY = "select c.id, c.name, count(bank_id) from banks as c\n" +
            "    left join clients_banks as sc on c.id = sc.bank_id\n" +
            "where sc.client_id = ?\n" +
            "group by c.id;";

    public BankDaoImpl(ConnectionStoreFactory storeFactory) {
        this.storeFactory = storeFactory;
    }

    @Override
    public void create(Bank entity) {

    }

    @Override
    public void update(Bank entity) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public boolean existById(Integer id) {
        return false;
    }

    @Override
    public Optional<Bank> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Bank> findAll() {
        return null;
    }

    @Override
    public List<BankViewDto> findAllPrepareView() {
        List<BankViewDto> banks = new ArrayList<>();
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_VIEW_BANKS_QUERY)) {
            while (rs.next()) {
                banks.add(convertResultSetToBankViewDto(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return banks;
    }

    @Override
    public List<BankViewDto> findAllPrepareViewByClient(int id) {
        List<BankViewDto> banks = new ArrayList<>();
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(FIND_ALL_VIEW_BANKS_BY_CLIENT_QUERY)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    banks.add(convertResultSetToBankViewDto(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return banks;
    }

    private BankViewDto convertResultSetToBankViewDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int count = rs.getInt("count(bank_id)");

        BankViewDto bank = new BankViewDto();
        bank.setId(id);
        bank.setName(BankName.valueOf(name));
        bank.setCountOfClients(count);

        return bank;
    }
}
