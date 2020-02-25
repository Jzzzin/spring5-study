package ch8;

import ch8.entities.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
