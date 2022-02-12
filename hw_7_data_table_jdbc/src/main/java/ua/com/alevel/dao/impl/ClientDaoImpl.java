package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dao.ClientDao;
import ua.com.alevel.entity.Client;
import ua.com.alevel.store.ConnectionStoreFactory;
import ua.com.alevel.view.ClientViewDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientDaoImpl implements ClientDao {

    private final ConnectionStoreFactory storeFactory;

    public ClientDaoImpl(ConnectionStoreFactory storeFactory) {
        this.storeFactory = storeFactory;
    }

    private static final String CREATE_CLIENT_QUERY = "insert into clients values (default,?,?)";
    private static final String UPDATE_CLIENT_QUERY = "update clients set first_name = ?, last_name = ? where id = ?";
    private static final String DELETE_CLIENT_QUERY = "delete from clients where id = ?";
    private static final String EXIST_CLIENT_BY_ID_QUERY = "select count(*) as exist from clients where id = ";
    private static final String FIND_CLIENT_BY_ID_QUERY = "select * from clients where id = ";
    private static final String FIND_ALL_CLIENT__QUERY = "select * from clients";
    private static final String FIND_ALL_VIEW_CLIENT__QUERY = "select s.id, s.first_name, s.last_name, count(client_id) as count_of_banks \n" +
            "from clients as s \n" +
            "         left join clients_banks as sc on s.id = sc.client_id \n" +
            "group by s.id";

    private static final String FIND_ALL_VIEW_CLIENT_BY_BANK_QUERY = "select s.id, s.first_name, s.last_name, count(client_id) as count_of_banks \n" +
            "from clients as s \n" +
            "         left join clients_banks as sc on s.id = sc.client_id where sc.bank_id = ? \n" +
            "group by s.id";

    @Override
    public void create(Client entity) {
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(CREATE_CLIENT_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
    }

    @Override
    public void update(Client entity) {
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(UPDATE_CLIENT_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(DELETE_CLIENT_QUERY)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Integer id) {
        long count = 0;
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(EXIST_CLIENT_BY_ID_QUERY + id)) {
            if (rs.next()) {
                count = rs.getLong("exist");
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public Optional<Client> findById(Integer id) {
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_CLIENT_BY_ID_QUERY + id)) {
            if (rs.next()) {
                return Optional.of(convertResultSetToClient(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_CLIENT__QUERY)) {
            while (rs.next()) {
                clients.add(convertResultSetToClient(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return clients;
    }

    @Override
    public List<ClientViewDto> findAllPrepareView() {
        List<ClientViewDto> clients = new ArrayList<>();
        try (Statement statement = storeFactory.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(FIND_ALL_VIEW_CLIENT__QUERY)) {
            while (rs.next()) {
                clients.add(convertResultSetToClientViewDto(rs));
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return clients;
    }

    @Override
    public List<ClientViewDto> findAllPrepareViewByBank(int id) {
        List<ClientViewDto> clients = new ArrayList<>();
        try (PreparedStatement ps = storeFactory.getConnection().prepareStatement(FIND_ALL_VIEW_CLIENT_BY_BANK_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    clients.add(convertResultSetToClientViewDto(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("sql error = " + e.getMessage());
        }
        return clients;
    }

    private Client convertResultSetToClient(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");

        Client client = new Client();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);

        return client;
    }

    private ClientViewDto convertResultSetToClientViewDto(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        int countOfBanks = rs.getInt("count_of_banks");

        ClientViewDto client = new ClientViewDto();
        client.setId(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setCountOfBanks(countOfBanks);

        return client;
    }
}
