package book.security;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import book.model.Account;
import lombok.Getter;

@Getter
// AuthenticationオブジェクトのPrincipalに格納
public class MyUserDetails extends User {
    private final Account account;

    public MyUserDetails(Account account) {
        super(account.getAccountId(),
                account.getPassword(),
                Arrays.asList(account.getAuthority().toString().split(",")).stream().map(
                        role -> new SimpleGrantedAuthority((role))).toList());
        this.account = account;
    }

}
