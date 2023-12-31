package book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
