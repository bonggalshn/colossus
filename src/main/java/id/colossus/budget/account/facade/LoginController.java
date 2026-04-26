package id.colossus.budget.account.facade;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"/login", "/auth/login", "/signin"})
    public String login() {
        return "auth/login";
    }
}