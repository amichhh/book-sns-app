package book.service.form.record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/** 読書記録編集用パラメータ */
@Data
public class EditRecord {
    /** 読書記録ID */
    @NotNull
    private Long id;
    /** 評価 */
    @Max(5)
    @Min(1)
    private Integer evaluation;
    /** 感想 */
    @NotBlank
    @Size(max = 400)
    private String impression;
}
