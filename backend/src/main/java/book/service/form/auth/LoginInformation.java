package book.service.form.auth;

import book.model.Account;
import lombok.Data;

/** ログイン情報 */
@Data
public class LoginInformation {
    /** ログイン状態 */
    private Boolean logined;
    /** ログインユーザー */
    private Account loginUser;
}
