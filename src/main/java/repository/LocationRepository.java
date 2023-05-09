package repository;

import domain.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends CrudRepository<Location, Long> {

    Location findByLocationCode1AndLocationCode2AndLocationName(int locationCode1, int locationCode2, String locationName);
}
