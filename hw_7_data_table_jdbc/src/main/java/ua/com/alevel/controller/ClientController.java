package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.dao.ClientDao;
import ua.com.alevel.view.ClientViewDto;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientDao clientDao;

    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @GetMapping
    public String findAll(Model model) {
        List<ClientViewDto> clients = clientDao.findAllPrepareView();
        model.addAttribute("clients", clients);
        return "pages/clients/clients_all";
    }

    @GetMapping("/{bankId}")
    public String findAll(Model model, @PathVariable int bankId) {
        List<ClientViewDto> clients = clientDao.findAllPrepareViewByBank(bankId);
        model.addAttribute("clients", clients);
        return "pages/clients/clients_all";
    }
}
