package book.service.form.book;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/** 書籍編集用パラメータ */
@Data
public class EditBook {
    /** 書籍ID */
    @NotNull
    private Long id;
    /** タイトル */
    @NotBlank
    @Size(max = 30)
    private String title;
    /** 著者 */
    @NotBlank
    @Size(max = 60)
    private String author;
    /** 出版社 */
    @NotBlank
    @Size(max = 30)
    private String publisher;
    /** 概要 */
    @Size(max = 400)
    private String outline;
    /** ジャンル */
    private List<String> genre;
    /** ISBN */
    @Size(max = 13)
    private String isbn;
    /** 画像パス */
    @Size(max = 400)
    private String imagePath;
    /** 商品パス */
    @Size(max = 400)
    private String productPath;
}
