package book.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.model.Record;
import book.repository.RecordRepository;
import book.service.form.record.EditRecord;
import book.service.form.record.RegisterRecord;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RecordService {
    private final RecordRepository repository;
    private final LikeBindService likeBindService;
    private final BookService bookService;
    private final AuthService authService;

    /** 読書記録を取得する */
    private Record loadRecord(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("読書記録が見つかりませんでした。"));
    }

    /** 自身の読書記録を検索する */
    public List<Record> findRecord() {
        return repository.findAll().stream().filter(
                v -> v.getAccountId().equals(authService.getUser().getAccountId())).toList();
    }

    /** 公開されている読書記録を検索する */
    public List<Record> findPublicRecord() {
        return repository.findAll().stream().filter(v -> v.getIsPublic()).toList();
    }

    /** 自身がいいねした読書記録を検索する */
    public List<Record> findLikeRecord() {
        var likeRecords = likeBindService.findMyAccountLikeBind().stream().map(v -> v.getRecordId()).toList();
        return repository.findAll().stream().filter(v -> likeRecords.contains(v.getId())).toList();
    }

    /** 読書記録を登録する */
    public Record registerRecord(RegisterRecord param) {
        Record record = new Record();
        record.setAccountId(authService.getUser().getAccountId());
        record.setBookId(param.getBookId());
        record.setEvaluation(param.getEvaluation());
        record.setImpression(param.getImpression());
        record.setIsPublic(param.getIsPublic());
        record.setLikeCount(0);
        record.setRegisterDate(LocalDate.now());
        record.setUpdateDate(LocalDate.now());
        // 書籍のいいね数を1増やす
        bookService.loadBook(param.getBookId()).plusRecordCount();
        return repository.save(record);
    }

    /** 自身の読書記録を編集する */
    public Record editRecord(EditRecord param) {
        Record record = loadRecord(param.getId());
        // 自身の読書記録のみ操作可能
        if (!record.getAccountId().equals(authService.getUser().getAccountId())) {
            throw new IllegalArgumentException("あなたの読書記録ではありません。");
        }
        record.setEvaluation(param.getEvaluation());
        record.setImpression(param.getImpression());
        record.setUpdateDate(LocalDate.now());
        return repository.save(record);
    }

    /** 自身の読書記録を削除する */
    public void deleteRecord(Long id) {
        Record record = loadRecord(id);
        // 自身の読書記録のみ操作可能
        if (!record.getAccountId().equals(authService.getUser().getAccountId())) {
            throw new IllegalArgumentException("あなたの読書記録ではありません。");
        }
        // 読書記録に紐付くいいねを削除する
        likeBindService.findBookLikeBind(id).forEach(v -> likeBindService.deleteLikeBind(v.getId()));
        // 書籍のいいね数を1減らす
        bookService.loadBook(record.getBookId()).minusRecordCount();
        repository.deleteById(id);
    }

    /** 読書記録にいいねする */
    public void likeRecord(Long id) {
        Record record = loadRecord(id);
        String loginUserId = authService.getUser().getAccountId();
        // 自身の読書記録へのいいねは不可
        if (record.getAccountId().equals(loginUserId)) {
            throw new IllegalArgumentException("自分の読書記録はいいねできません。");
        }
        // いいね済の読書記録へのいいねは不可
        if (likeBindService.isLiked(id, loginUserId)) {
            throw new IllegalArgumentException("この読書記録は既にいいねしています。");
        }
        likeBindService.registerLikeBind(id, loginUserId);
        record.plusLikeCount();
        repository.save(record);
    }

    /** 読書記録のいいねを解除する */
    public void unlikeRecord(Long id) {
        Record record = loadRecord(id);
        String loginUserId = authService.getUser().getAccountId();
        // いいねしていない読書記録のいいね解除は不可
        if (!likeBindService.isLiked(id, loginUserId)) {
            throw new IllegalArgumentException("この読書記録はいいねしていません。");
        }
        likeBindService.deleteLikeBind(id);
        record.minusLikeCount();
        repository.save(record);
    }

    /** 自身の読書記録を公開する */
    public void publishRecord(Long id) {
        Record record = loadRecord(id);
        // 自身の読書記録のみ操作可能
        if (!record.getAccountId().equals(authService.getUser().getAccountId())) {
            throw new IllegalArgumentException("あなたの読書記録ではありません。");
        }
        // 非公開の読書記録のみ公開可能
        if (record.getIsPublic()) {
            throw new IllegalArgumentException("この読書記録は既に公開されています。");
        }
        record.setIsPublic(true);
        repository.save(record);
    }

    /** 自身の読書記録を非公開にする */
    public void secretRecord(Long id) {
        Record record = loadRecord(id);
        // 自身の読書記録のみ操作可能
        if (!record.getAccountId().equals(authService.getUser().getAccountId())) {
            throw new IllegalArgumentException("あなたの読書記録ではありません。");
        }
        // 公開されている読書記録のみ非公開可能
        if (!record.getIsPublic()) {
            throw new IllegalArgumentException("この読書記録は既に非公開です。");
        }
        record.setIsPublic(false);
        // 読書記録に紐付くいいねを削除する
        likeBindService.findBookLikeBind(id).forEach(v -> likeBindService.deleteLikeBind(v.getId()));
        record.setLikeCount(0);
        repository.save(record);
    }

    public Boolean exsistsByBookId(Long bookId) {
        return repository.existsByBookId(bookId);
    }

}
