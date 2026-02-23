package alex.valker91.repository;

import alex.valker91.spring_boot.entity.UserAccountDb;
import org.springframework.data.repository.CrudRepository;

public interface DbUserAccountRepository extends CrudRepository<UserAccountDb, Long> {
}
