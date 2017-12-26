package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;

import java.util.Date;
import java.util.List;
import java.util.Set;


public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    List<Event> findByPlace_Id(Long placeId);
    Event findTopByStartDateGreaterThanEqualOrderByStartDateAsc(Date startDate);
    Event findTopByStartDateGreaterThanEqualAndIdNotOrderByStartDateAsc(Date startDate, Long eventId);

    @Query("select e from Event e where " +
            "extract(epoch from (e.startDate - now())) < ?1 and " +
            "extract(epoch from (e.startDate - now())) > 0")
    Set<Event> findCurrentEvents(Integer allowedTime);
}
