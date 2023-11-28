package book.service.form.account;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IdentifyAccount {
    /** ユーザーID */
    @NotBlank
    private String accountId;
}
