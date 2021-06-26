package pl.hk.zadanie_26;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.hk.zadanie_26.mail.MailForm;
import pl.hk.zadanie_26.mail.MailService;

@Controller
public class HomeController {
    private final MailService mailService;

    public HomeController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("sender",new MailForm());
        return "contact/contact";
    }

    @PostMapping("/sent")
    public String sendMail(MailForm sender) {
        mailService.sendMail(sender);
        return "contact/result";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "error_forbidden";
    }
}
