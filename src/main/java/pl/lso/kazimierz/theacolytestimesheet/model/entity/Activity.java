package pl.lso.kazimierz.theacolytestimesheet.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartosz on 01.12.2017.
 */

@Entity
@Table(schema = "public", name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "value")
    private String value;

    @OneToMany(mappedBy = "activityId", cascade = CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "activityId", cascade = CascadeType.ALL)
    private Set<Points> points = new HashSet<>();

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<Points> getPoints() {
        return points;
    }

    public void setPoints(Set<Points> points) {
        this.points = points;
    }
}
