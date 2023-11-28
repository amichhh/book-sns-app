package book.service;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.model.Book;
import book.model.type.BookGenre;
import book.repository.BookRepository;
import book.service.form.book.EditBook;
import book.service.form.book.RegisterBook;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class BookService {
    private final BookRepository repository;

    /** 書籍を取得する */
    public Book loadBook(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("書籍が見つかりませんでした。"));
    }

    /** 書籍を検索する */
    public List<Book> findBook() {
        return repository.findAll();
    }

    /** 書籍を読書記録の多い順に検索する */
    public List<Book> findBookOrderByRecordCount() {
        return findBook().stream().sorted((a, b) -> (b.getRecordCount() - a.getRecordCount())).toList();
    }

    /** 書籍を登録する */
    public Book registerBook(RegisterBook param) {
        Book book = new Book();
        // 既に登録済みのタイトルは登録不可
        if (repository.existsByTitle(param.getTitle())) {
            throw new DuplicateKeyException("タイトルが既に登録されています。");
        }
        book.setTitle(param.getTitle());
        book.setAuthor(param.getAuthor());
        book.setPublisher(param.getPublisher());
        book.setOutline(param.getOutline());
        book.setGenre(param.getGenre().stream().map(v -> BookGenre.valueOf(v)).toList());
        book.setIsbn(param.getIsbn());
        book.setImagePath(param.getImagePath());
        book.setProductPath(param.getProductPath());
        book.setRecordCount(0);
        return repository.save(book);
    }

    /** 書籍を編集する */
    public Book editBook(EditBook param) {
        Book book = loadBook(param.getId());
        // 既に登録済みのタイトルは登録不可
        if (!param.getTitle().equals(book.getTitle()) && repository.existsByTitle(param.getTitle())) {
            throw new DuplicateKeyException("タイトルが既に登録されています。");
        }
        book.setTitle(param.getTitle());
        book.setAuthor(param.getAuthor());
        book.setPublisher(param.getPublisher());
        book.setOutline(param.getOutline());
        book.setGenre(param.getGenre().stream().map(v -> BookGenre.valueOf(v)).toList());
        book.setIsbn(param.getIsbn());
        book.setImagePath(param.getImagePath());
        book.setProductPath(param.getProductPath());
        return repository.save(book);
    }
}
