package book.model;

import java.util.List;

import book.model.type.BookGenre;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 書籍 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    /** 書籍ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /** タイトル */
    @NotBlank
    @Size(max = 60)
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
    @Enumerated(EnumType.STRING)
    private List<BookGenre> genre;
    /** ISBN */
    @Size(max = 13)
    private String isbn;
    /** 画像パス */
    @Size(max = 400)
    private String imagePath;
    /** 商品パス */
    @Size(max = 400)
    private String productPath;
    /** 読書記録投稿数 */
    @NotNull
    @Min(0)
    private Integer recordCount;

    /** 読書記録数を1増やす */
    public void plusRecordCount() {
        this.setRecordCount(this.getRecordCount() + 1);
    }

    /** 読書記録数を1減らす */
    public void minusRecordCount() {
        if (this.getRecordCount() == 0) {
            throw new IllegalArgumentException("読書記録数は0件です。");
        }
        this.setRecordCount(this.getRecordCount() - 1);
    }
}
