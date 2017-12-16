package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;

import java.util.List;


public interface PointsRepository extends PagingAndSortingRepository<Points, Long> {
    List<Points> findByUser_Id(Long userId);
}
