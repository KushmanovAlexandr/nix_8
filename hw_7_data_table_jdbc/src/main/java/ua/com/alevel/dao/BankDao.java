package ua.com.alevel.dao;

import ua.com.alevel.entity.Bank;
import ua.com.alevel.view.BankViewDto;

import java.util.List;

public interface BankDao extends BaseDao<Bank> {

    List<BankViewDto> findAllPrepareViewByClient(int id);
}
