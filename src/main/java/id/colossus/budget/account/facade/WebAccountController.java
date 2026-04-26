package id.colossus.budget.account.facade;

import id.colossus.budget.account.core_service.AccountService;
import id.colossus.common.lang.dto.AccountResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class WebAccountController {

    private final AccountService accountService;

    public WebAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/list")
    public String listAccounts(Model model) {
        List<AccountResponse> accounts = accountService.getAccounts(1L);
        model.addAttribute("accounts", accounts);
        return "account/list";
    }
}