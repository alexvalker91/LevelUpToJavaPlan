package alex.valker91.repository;

import alex.valker91.spring_boot.entity.TicketDb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface DbTicketRepository extends CrudRepository<TicketDb, Long> {

    Page<TicketDb> findAllByUserId(long userId, Pageable pageable);

    Page<TicketDb> findAllByEventId(long eventId, Pageable pageable);

    boolean existsByEventIdAndPlace(long eventId, int place);
}
