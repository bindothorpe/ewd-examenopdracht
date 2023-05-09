package service;

import domain.Location;

public interface LocationService {
    Location findByLocationCode1AndLocationCode2AndLocationName(int locationCode1, int locationCode2, String locationName);

    void save(Location location);

    void saveAll(Iterable<Location> locations);

}
