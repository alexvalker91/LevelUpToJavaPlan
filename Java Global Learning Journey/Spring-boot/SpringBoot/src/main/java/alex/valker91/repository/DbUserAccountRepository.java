package alex.valker91.repository;

import alex.valker91.entity.UserAccountDb;
import org.springframework.data.repository.CrudRepository;

public interface DbUserAccountRepository extends CrudRepository<UserAccountDb, Long> {
}
