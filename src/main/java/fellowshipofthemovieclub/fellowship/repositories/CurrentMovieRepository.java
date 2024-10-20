package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.CurrentMovie;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentMovieRepository extends JpaRepository<CurrentMovie, Long> {
    CurrentMovie findTopByUserIdOrderByDateAddedDesc(Long userId);
}
