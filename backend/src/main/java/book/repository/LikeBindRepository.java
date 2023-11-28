package book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.model.LikeBind;

@Repository
public interface LikeBindRepository extends JpaRepository<LikeBind, Long> {
    boolean existsByAccountId(String accountId);

}
