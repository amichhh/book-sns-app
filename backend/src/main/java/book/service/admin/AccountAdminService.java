package book.service.admin;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class AccountAdminService {
    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    /** アカウントを取得する */
    public Account loadAccount(String accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("アカウントが見つかりませんでした。"));
    }

    /** アカウントを検索する */
    public List<Account> findAccount() {
        return repository.findAll();
    }

    /** アカウントを登録する */
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
        account.setAuthority(AuthorityType.valueOf(param.getAuthority()));
        account.setStatus(StatusType.VALID);
        return repository.save(account);
    }

    /** アカウントを編集する */
    public Account editAccount(EditAccount param) {
        Account account = repository.findById(param.getAccountId())
                .orElseThrow(() -> new NullPointerException("アカウントが見つかりませんでした。"));
        if (repository.existsByUserName(param.getUserName())) {
            throw new DuplicateKeyException("ユーザー名が既に登録されています。");
        }
        if (repository.existsByEmail(param.getEmail())) {
            throw new DuplicateKeyException("メールアドレスが既に登録されています。");
        }
        account.setUserName(param.getUserName());
        account.setPassword(passwordEncoder.encode(param.getRawPassword()));
        account.setEmail(param.getEmail());
        account.setAuthority(param.getAuthority());
        return repository.save(account);
    }

    /** アカウントを有効にする */
    public void validAccount(String accountId) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new NullPointerException("アカウントが見つかりませんでした。"));
        if (!account.getStatus().isValid()) {
            account.setStatus(StatusType.VALID);
            repository.save(account);
        } else {
            throw new IllegalArgumentException("アカウント状態が既に有効です。");
        }
    }

    /** アカウントを無効にする */
    public void invalidAccount(String accountId) {
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new NullPointerException("アカウントが見つかりませんでした。"));
        if (account.getStatus().isValid()) {
            account.setStatus(StatusType.INVALID);
            repository.save(account);
        } else {
            throw new IllegalArgumentException("アカウント状態が既に無効です。");
        }
    }

    /** アカウントを削除する */
    public void deleteAccount(String accountId) {
        // アカウントが無効の場合のみ削除可能
        Account account = repository.findById(accountId)
                .orElseThrow(() -> new NullPointerException("アカウントが見つかりませんでした。"));
        if (!account.getStatus().isValid()) {
            repository.deleteById(accountId);
        } else {
            throw new IllegalArgumentException("アカウント状態が有効なため削除できません。");
        }
    }

}
