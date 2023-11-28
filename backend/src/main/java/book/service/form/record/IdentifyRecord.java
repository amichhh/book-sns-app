package book.service.form.record;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 読書記録特定用パラメータ */
@Data
public class IdentifyRecord {
    /** 読書記録ID */
    @NotNull
    private Long id;
}
