package book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.model.Account;
import book.service.AccountService;
import book.service.form.account.EditAccount;
import book.service.form.account.RegisterAccount;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/account")
@AllArgsConstructor
public class AccountController {
    private final AccountService service;

    /** 自身のアカウントを取得する */
    @GetMapping("/")
    public Account getAccount() {
        return service.loadAccount();
    }

    /** 自身のアカウントを登録する */
    @PostMapping("")
    public Account registerAccount(@RequestBody @Valid RegisterAccount param) {
        return service.registerAccount(param);
    }

    /** 自身のアカウントを編集する */
    @PostMapping("/edit")
    public Account ediAccount(@RequestBody @Valid EditAccount param) {
        return service.editAccount(param);
    }
}
