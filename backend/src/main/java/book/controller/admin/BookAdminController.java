package book.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.service.admin.BookAdminService;
import book.service.form.book.IdentifyBook;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/admin/book")
@AllArgsConstructor
public class BookAdminController {
    private final BookAdminService service;

    /** 書籍を削除する */
    @PostMapping("/delete")
    public void deleteBook(@RequestBody @Valid IdentifyBook param) {
        service.deleteBook(param.getId());
    }

}
