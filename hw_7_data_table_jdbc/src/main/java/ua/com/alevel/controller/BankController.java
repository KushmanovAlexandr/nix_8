package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.BankDao;
import ua.com.alevel.view.BankViewDto;

import java.util.List;

@Controller
@RequestMapping("/banks")
public class BankController {

    private final BankDao bankDao;

    public BankController(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @GetMapping
    public String findAll(Model model) {
        List<BankViewDto> banks = bankDao.findAllPrepareView();
        model.addAttribute("banks", banks);
        return "pages/banks/banks_all";
    }

    @GetMapping("/{clientId}")
    public String findAllByClient(Model model, @PathVariable int clientId) {
        List<BankViewDto> banks = bankDao.findAllPrepareViewByClient(clientId);
        model.addAttribute("banks", banks);
        return "pages/banks/banks_all";
    }
}
