package book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import book.model.Book;
import book.model.type.BookGenre;
import book.service.form.book.EditBook;
import book.service.form.book.RegisterBook;

/** Bookドメインに関するServiceとRepositoryのインテグレーションテスト */
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Sql("./sql/BookServiceTest.sql")
@Transactional
public class BookServiceRepositoryTest {
    @Autowired
    BookService service;

    @Test
    void 書籍を取得する() {
        // 例外処理
        assertThrows(IllegalArgumentException.class, () -> service.loadBook(99L));

        // 通常処理
        Book book = service.loadBook(1L);
        assertEquals("ノルウェイの森", book.getTitle());
        assertEquals("村上春樹", book.getAuthor());
        assertEquals("十八年という歳月が...", book.getOutline());
        assertEquals(List.of(BookGenre.YOUTH), book.getGenre());
        assertEquals(0, book.getRecordCount());
    }

    @Test
    void 書籍を検索する() {
        // 通常処理
        assertEquals(4, service.findBook().size());
    }

    @Test
    void 書籍を登録する() {
        // 事前検証
        assertEquals(4, service.findBook().size());

        // 例外処理
        RegisterBook exParam = new RegisterBook();
        exParam.setTitle("ノルウェイの森");
        exParam.setAuthor("xxx");
        exParam.setOutline("xxx...");
        exParam.setGenre(List.of("YOUTH"));

        assertThrows(DuplicateKeyException.class, () -> service.registerBook(exParam));

        // 通常処理
        RegisterBook param = new RegisterBook();
        param.setTitle("時をかける少女");
        param.setAuthor("筒井康隆");
        param.setOutline("放課後の誰もいない理科実験室で...");
        param.setGenre(List.of("YOUTH"));
        Book book = service.registerBook(param);

        assertEquals(book, service.loadBook(book.getId()));

        // 事後検証
        assertEquals(5, service.findBook().size());
    }

    @Test
    void 書籍を編集する() {
        // 例外処理
        EditBook exParam = new EditBook();
        exParam.setId(1L);
        exParam.setTitle("博士の愛した数式");
        exParam.setAuthor("xxx");
        exParam.setOutline("xxx...");
        exParam.setGenre(List.of("YOUTH"));

        assertThrows(DuplicateKeyException.class, () -> service.editBook(exParam));

        // 通常処理
        EditBook param = new EditBook();
        param.setId(1L);
        param.setTitle("1Q84");
        param.setAuthor("村上春樹");
        param.setOutline("「1Q84年――私はこの新しい世界を...");
        param.setGenre(List.of("YOUTH"));

        Book book = service.editBook(param);

        assertEquals(book, service.loadBook(1L));
    }

    @Test
    void 読書記録数を1増やす() {
        // 事前検証
        Book book = service.loadBook(1L);
        assertEquals(0, book.getRecordCount());

        // 通常処理
        book.plusRecordCount();

        // 事後検証
        assertEquals(1, service.loadBook(1L).getRecordCount());
    }

    @Test
    void 読書記録数を1減らす() {
        // 事前検証
        Book book1 = service.loadBook(1L);
        Book book2 = service.loadBook(2L);
        assertEquals(0, book1.getRecordCount());
        assertEquals(2, book2.getRecordCount());

        // 例外処理
        assertThrows(IllegalArgumentException.class, () -> book1.minusRecordCount());

        // 通常処理
        book2.minusRecordCount();

        // 事後検証
        assertEquals(0, service.loadBook(1L).getRecordCount());
        assertEquals(1, service.loadBook(2L).getRecordCount());
    }

}
