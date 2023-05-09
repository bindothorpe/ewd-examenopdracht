package service;

import domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private LocationRepository locationRepository;;

    @Override
    public Location findByLocationCode1AndLocationCode2AndLocationName(int locationCode1, int locationCode2, String locationName) {
        return locationRepository.findByLocationCode1AndLocationCode2AndLocationName(locationCode1, locationCode2, locationName);
    }

    @Override
    public void save(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void saveAll(Iterable<Location> locations) {
        locationRepository.saveAll(locations);
    }


}
