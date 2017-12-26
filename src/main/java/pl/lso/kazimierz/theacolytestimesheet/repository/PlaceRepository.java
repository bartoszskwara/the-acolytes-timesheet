package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Place;

import java.util.Set;


public interface PlaceRepository extends PagingAndSortingRepository<Place, Long> {
    Set<Place> findAll();
}
