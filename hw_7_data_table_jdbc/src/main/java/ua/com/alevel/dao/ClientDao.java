package ua.com.alevel.dao;

import ua.com.alevel.entity.Client;
import ua.com.alevel.view.ClientViewDto;

import java.util.List;

public interface ClientDao extends BaseDao<Client> {

    List<ClientViewDto> findAllPrepareViewByBank(int id);
}
