package alex.valker91.repository;

import alex.valker91.spring_boot.entity.UserDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DbUserRepository extends CrudRepository<UserDb, Long> {

    Optional<UserDb> findByEmail(String email);

    Page<UserDb> findAllByName(String name, Pageable pageable);
}
