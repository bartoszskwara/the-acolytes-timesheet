package pl.lso.kazimierz.theacolytestimesheet.model.entity;

/**
 * Created by Bartosz on 01.12.2017.
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    // TODO: think about format of the coordinates
    @Column(nullable = false, name = "coordinates")
    private String coordinates;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


}
