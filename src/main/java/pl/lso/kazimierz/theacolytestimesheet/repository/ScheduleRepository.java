package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;

import java.util.Date;
import java.util.Set;


public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long> {

    Set<Schedule> findByUser_Id(Long id);

    Schedule findTopByEvent_StartDateGreaterThanEqualOrderByEvent_StartDateAsc(Date startDate);
}
