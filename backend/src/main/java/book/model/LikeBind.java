package book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/** いいね紐付け */
@Entity
@Data
@NoArgsConstructor
public class LikeBind {
    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 読書記録ID */
    @NotNull
    private Long recordId;
    /** アカウントID */
    @NotBlank
    private String accountId;
}
