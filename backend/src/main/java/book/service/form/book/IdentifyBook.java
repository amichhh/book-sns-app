package book.service.form.book;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 書籍特定用パラメータ */
@Data
public class IdentifyBook {
    /** 書籍ID */
    @NotNull
    private Long id;
}
