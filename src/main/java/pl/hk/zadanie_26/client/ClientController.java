package pl.hk.zadanie_26.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/listClient")
    public String productCatalog(Model model) {
        List<Client> clients = clientService.productCatalog();
        model.addAttribute("clients", clients);
        return "client/listClient";
    }

    @GetMapping("/editClient")
    public String editClient(@RequestParam Long id, Model model) {
        Client client = clientService.findClientById(id);
        model.addAttribute("client", client);
        return "client/editClient";
    }

    @PostMapping("/editClient")
    public String editClient(Client client) {
        clientService.editClient(client);
        return "redirect:/";
    }
}

