package book.model;

import book.model.type.AuthorityType;
import book.model.type.StatusType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/** アカウント */
@Entity
@Data
@NoArgsConstructor
public class Account {
    /** アカウントID */
    @Id
    private String accountId;
    /** ユーザー名 */
    @NotBlank
    @Size(max = 30)
    private String userName;
    /** パスワード */
    @NotBlank
    @Size(max = 64)
    private String password;
    /** メールアドレス */
    @NotBlank
    @Size(max = 256)
    private String email;
    /** 権限 */
    @Enumerated(EnumType.STRING)
    private AuthorityType authority;
    /** アカウント状態 */
    @Enumerated(EnumType.STRING)
    private StatusType status;
}
