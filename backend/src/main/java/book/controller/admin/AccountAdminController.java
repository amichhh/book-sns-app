package book.controller.admin;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.model.Account;
import book.service.admin.AccountAdminService;
import book.service.form.account.EditAccount;
import book.service.form.account.IdentifyAccount;
import book.service.form.account.RegisterAccount;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/admin/account")
@AllArgsConstructor
public class AccountAdminController {
    private final AccountAdminService service;

    /** アカウントを取得する */
    @GetMapping("/load")
    public Account getAccount(@RequestBody @Valid IdentifyAccount param) {
        return service.loadAccount(param.getAccountId());
    }

    /** アカウントを検索する */
    @GetMapping("")
    public List<Account> findAccount() {
        return service.findAccount();
    }

    /** アカウントを登録する */
    @PostMapping("")
    public Account registerAccount(@RequestBody @Valid RegisterAccount param) {
        return service.registerAccount(param);
    }

    /** アカウントを編集する */
    @PostMapping("/edit")
    public Account editAccount(@RequestBody @Valid EditAccount param) {
        return service.editAccount(param);
    }

    /** アカウントを有効にする */
    @PostMapping("/valid")
    public void validAccount(@RequestBody @Valid IdentifyAccount param) {
        service.validAccount(param.getAccountId());
    }

    /** アカウントを無効にする */
    @PostMapping("/invalid")
    public void invalidAccount(@RequestBody @Valid IdentifyAccount param) {
        service.invalidAccount(param.getAccountId());
    }

    /** アカウントを削除する */
    @PostMapping("/delete")
    public void deleteAccount(@RequestBody @Valid IdentifyAccount param) {
        service.deleteAccount(param.getAccountId());
    }

}
