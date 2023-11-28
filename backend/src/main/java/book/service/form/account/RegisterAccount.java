package book.service.form.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/** アカウント登録用パラメータ */
@Data
public class RegisterAccount {
    /** ユーザーID */
    @NotBlank
    private String accountId;
    /** ユーザー名 */
    @NotBlank
    @Size(max = 30)
    private String userName;
    /** パスワード */
    @NotBlank
    @Size(max = 64)
    private String rawPassword;
    /** メールアドレス */
    @NotBlank
    @Size(max = 256)
    private String email;
    /**
     * 権限
     * (一般ユーザーが自身のアカウントを登録する際は不要)
     */
    private String authority;
}
