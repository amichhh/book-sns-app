package book.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import book.model.Account;
import book.repository.AccountRepository;
import book.security.MyUserDetails;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository repository;

    // DaoAuthenticationProviderの認証処理の中で呼ばれるloadUserByUsernameを再定義。
    // PrincipalにAccountの情報を格納するため。
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findById(username).orElseThrow(
                () -> new UsernameNotFoundException("Account not found."));
        return new MyUserDetails(account);
    }
}