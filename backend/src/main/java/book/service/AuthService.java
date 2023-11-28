package book.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import book.model.Account;
import book.model.type.AuthorityType;
import book.model.type.StatusType;
import book.security.MyUserDetails;
import book.service.form.auth.LoginInformation;

@Service
public class AuthService {

    /** ログイン情報を取得する */
    public LoginInformation getLoginInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginInformation info = new LoginInformation();
        info.setLogined(!auth.getName().equals("anonymousUser"));
        info.setLoginUser(getUser());
        return info;
    }

    /** ログイン中のユーザーを取得する */
    public Account getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")) {
            Account account = new Account();
            account.setAccountId(null);
            account.setUserName(null);
            account.setPassword(null);
            account.setEmail(null);
            account.setAuthority(AuthorityType.ANONYMOUS);
            account.setStatus(StatusType.INVALID);
            return account;
        } else {
            var principal = (MyUserDetails) auth.getPrincipal();
            return principal.getAccount();
        }
    }

}
