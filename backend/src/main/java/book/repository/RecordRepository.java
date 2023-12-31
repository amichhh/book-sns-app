package book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    boolean existsByBookId(Long bookId);
}
