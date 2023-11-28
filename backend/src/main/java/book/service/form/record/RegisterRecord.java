package book.service.form.record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/** 読書記録登録用パラメータ */
@Data
public class RegisterRecord {
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
    /** 公開設定 */
    @NotNull
    private Boolean isPublic;
}
