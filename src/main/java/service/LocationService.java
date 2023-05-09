package service;

import domain.Location;

public interface LocationService {
    Location findByLocationCode1AndLocationCode2AndLocationName(int locationCode1, int locationCode2, String locationName);

}
