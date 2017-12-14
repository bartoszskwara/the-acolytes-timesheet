package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;


public interface PointsRepository extends PagingAndSortingRepository<Points, Long> {

}
