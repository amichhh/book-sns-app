package book.service.form.account;

import book.model.type.AuthorityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/** アカウント編集用パラメータ */
@Data
public class EditAccount {
    /**
     * ユーザーID
     * (一般ユーザーが自身のアカウントを編集する際は不要)
     */
    private String accountId;
    /** ユーザー名 */
    private @NotBlank @Size(max = 30) String userName;
    /** パスワード */
    private @NotBlank @Size(max = 64) String rawPassword;
    /** メールアドレス */
    private @NotBlank @Size(max = 256) String email;
    /**
     * 権限
     * (一般ユーザーが自身のアカウントを編集する際は不要)
     */
    private AuthorityType authority;
}
