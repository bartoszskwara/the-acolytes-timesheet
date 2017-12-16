package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Event;

import java.util.List;


public interface EventRepository extends PagingAndSortingRepository<Event, Long> {
    List<Event> findByPlace_Id(Long placeId);
}
