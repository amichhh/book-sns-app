package book.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.service.AuthService;
import book.service.form.auth.LoginInformation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService service;

    /** ログイン情報を取得する */
    @GetMapping("/login/information")
    public LoginInformation getLoginInformation() {
        return service.getLoginInformation();
    }

}
