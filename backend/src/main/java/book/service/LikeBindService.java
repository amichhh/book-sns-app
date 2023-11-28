package book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import book.model.LikeBind;
import book.repository.LikeBindRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LikeBindService {
    private final LikeBindRepository repository;
    private final AuthService authService;

    /** 読書記録に紐付くいいねを検索する */
    public List<LikeBind> findBookLikeBind(Long recordId) {
        return repository.findAll().stream().filter(v -> v.getRecordId() == recordId).toList();
    }

    /** アカウントに紐付くいいねを検索する */
    public List<LikeBind> findAccountLikeBind(String accountId) {
        return repository.findAll().stream().filter(v -> v.getAccountId().equals(accountId)).toList();
    }

    /** 自身のアカウントに紐付くいいねを検索する */
    public List<LikeBind> findMyAccountLikeBind() {
        return findAccountLikeBind(authService.getUser().getAccountId());
    }

    /** いいね紐付けを登録する */
    public LikeBind registerLikeBind(Long recordId, String accountId) {
        LikeBind likeBind = new LikeBind();
        likeBind.setRecordId(recordId);
        likeBind.setAccountId(accountId);
        return repository.save(likeBind);
    }

    /** いいね紐付けを削除する */
    public void deleteLikeBind(Long id) {
        repository.deleteById(id);
    }

    public Boolean isLiked(Long recordId, String accountId) {
        return findAccountLikeBind(accountId).stream().map(v -> v.getRecordId()).toList().contains(recordId);
    }

}
