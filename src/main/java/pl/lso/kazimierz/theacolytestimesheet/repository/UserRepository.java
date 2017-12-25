package pl.lso.kazimierz.theacolytestimesheet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

    User findOneByEmail(String email);

    User findOneByUsername(String username);

    User findOneByEmailOrUsername(String email, String username);

    @Query(value = "select u from User u " +
            "left join u.points p " +
            "left join p.activity a " +
            "group by u.id " +
            "order by sum(a.value) desc ",
    countQuery = "select count(*) from User u " +
            "left join u.points p " +
            "left join p.activity a " +
            "group by u.id " +
            "order by sum(a.value) desc ")
    Page<User> findRanking(Pageable pageable);
}
