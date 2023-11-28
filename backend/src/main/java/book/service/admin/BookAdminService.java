package book.service.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.repository.BookRepository;
import book.service.RecordService;
import lombok.AllArgsConstructor;

@Service
@Transactional
@PreAuthorize("hasRole('ADMIN')")
@AllArgsConstructor
public class BookAdminService {
    private final BookRepository repository;
    private final RecordService recordService;

    /** 書籍を削除する */
    public void deleteBook(Long id) {
        // 読書記録がある場合は削除不可
        if (recordService.exsistsByBookId(id)) {
            throw new IllegalArgumentException("読書記録が存在するため削除できません。");
        }
        repository.deleteById(id);
    }
}
