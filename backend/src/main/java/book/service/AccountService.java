package book.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.model.Account;
import book.model.type.AuthorityType;
import book.model.type.StatusType;
import book.repository.AccountRepository;
import book.service.form.account.EditAccount;
import book.service.form.account.RegisterAccount;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    /** 自身のアカウントを取得する */
    public Account loadAccount() {
        return repository.findById(authService.getUser().getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("アカウントが見つかりませんでした。"));
    }

    /** 自身のアカウントを登録する */
    public Account registerAccount(RegisterAccount param) {
        if (repository.existsById(param.getAccountId())) {
            throw new DuplicateKeyException("ユーザーIDが既に登録されています。");
        }
        if (repository.existsByUserName(param.getUserName())) {
            throw new DuplicateKeyException("ユーザー名が既に登録されています。");
        }
        if (repository.existsByEmail(param.getEmail())) {
            throw new DuplicateKeyException("メールアドレスが既に登録されています。");
        }
        Account account = new Account();
        account.setAccountId(param.getAccountId());
        account.setUserName(param.getUserName());
        account.setPassword(passwordEncoder.encode(param.getRawPassword()));
        account.setEmail(param.getEmail());
        account.setAuthority(AuthorityType.ROLE_GENERAL);
        account.setStatus(StatusType.VALID);
        return repository.save(account);
    }

    /** 自身のアカウントを編集する */
    public Account editAccount(EditAccount param) {
        Account account = loadAccount();
        if (!param.getUserName().equals(account.getUserName()) && repository.existsByUserName(param.getUserName())) {
            throw new DuplicateKeyException("ユーザー名が既に登録されています。");
        }
        if (!param.getEmail().equals(account.getEmail()) && repository.existsByEmail(param.getEmail())) {
            throw new DuplicateKeyException("メールアドレスが既に登録されています。");
        }
        account.setUserName(param.getUserName());
        account.setPassword(passwordEncoder.encode(param.getRawPassword()));
        account.setEmail(param.getEmail());
        return repository.save(account);
    }
}
