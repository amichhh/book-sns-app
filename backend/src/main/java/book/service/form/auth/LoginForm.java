package book.service.form.auth;

import lombok.Data;

/** ログインフォーム */
@Data
public class LoginForm {
    /** ログインID */
    private String userId;
    /** パスワード */
    private String password;
}
