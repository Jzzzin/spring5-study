package ch9.repos;

import ch9.entities.Singer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
    @Query("SELECT COUNT(s) FROM Singer s")
    Long countAllSingers();
}
