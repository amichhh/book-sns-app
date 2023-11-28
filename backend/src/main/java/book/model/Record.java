package book.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 読書記録 */
@Entity
@Data
@NoArgsConstructor
public class Record {
    /** ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** 作成者ID */
    @NotBlank
    private String accountId;
    /** 書籍ID */
    @NotNull
    private Long bookId;
    /** 評価 */
    @Max(5)
    @Min(1)
    private Integer evaluation;
    /** 感想 */
    @NotBlank
    @Size(max = 400)
    private String impression;
    /** いいね数 */
    @NotNull
    @Min(0)
    private Integer likeCount;
    /** 公開設定 */
    @NotNull
    private Boolean isPublic;
    /** 登録日時 */
    @NotNull
    private LocalDate registerDate;
    /** 更新日時 */
    @NotNull
    private LocalDate updateDate;

    /** いいね数を1増やす */
    public void plusLikeCount() {
        this.setLikeCount(this.getLikeCount() + 1);
    }

    /** いいね数を1減らす */
    public void minusLikeCount() {
        if (this.getLikeCount() == 0) {
            throw new IllegalArgumentException("いいねは0件です。");
        }
        this.setLikeCount(this.getLikeCount() - 1);
    }
}