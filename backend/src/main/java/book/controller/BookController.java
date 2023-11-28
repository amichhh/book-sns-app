package book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.model.Book;
import book.service.BookService;
import book.service.form.book.EditBook;
import book.service.form.book.IdentifyBook;
import book.service.form.book.RegisterBook;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {
    private final BookService service;

    /** 書籍を取得する */
    @GetMapping("/load")
    public Book loadBook(@RequestBody @Valid IdentifyBook param) {
        return service.loadBook(param.getId());
    }

    /** 書籍を検索する */
    @GetMapping("")
    public List<Book> findBook() {
        return service.findBook();
    }

    /** 書籍を読書記録の多い順に検索する */
    @GetMapping("/ranking")
    public List<Book> findBookOrderByRecordCount() {
        return service.findBookOrderByRecordCount();
    }

    /** 書籍を登録する */
    @PostMapping("")
    public Book registerBook(@RequestBody @Valid RegisterBook param) {
        return service.registerBook(param);
    }

    /** 書籍を編集する */
    @PostMapping("/edit")
    public Book editBook(@RequestBody @Valid EditBook param) {
        return service.editBook(param);
    }

}
