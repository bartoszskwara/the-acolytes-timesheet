package pl.lso.kazimierz.theacolytestimesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.location.Location;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;
import pl.lso.kazimierz.theacolytestimesheet.repository.EventRepository;
import pl.lso.kazimierz.theacolytestimesheet.repository.PlaceRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class LocationService {

    @Value("${location.radius.allowed}")
    private Double allowedRadius;

    @Value("${location.time.allowed}")
    private Integer allowedTime;

    private PlaceRepository placeRepository;
    private EventRepository eventRepository;

    @Autowired
    public LocationService(PlaceRepository placeRepository, EventRepository eventRepository) {
        this.placeRepository = placeRepository;
        this.eventRepository = eventRepository;
    }

    public Set<Event> getAnEventNearbyIfExists(Location location) {

        System.out.println(location.getCoords());

        Set<Place> places = placeRepository.findAll();
        Set<Event> events = new HashSet<>();
        for(Place p : places) {
            double distance = distance(location.getCoords().getLatitude(), p.getLatitude(),
                    location.getCoords().getLongitude(), p.getLongitude(),
                    location.getCoords().getAltitude(), p.getAltitude());
            System.out.println(distance);
            if(distance <= allowedRadius) {
                Set<Event> currentEvents = eventRepository.findCurrentEvents(allowedTime);
                events.addAll(currentEvents);
            }
        }

        return events;

    }

    private double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }
}
