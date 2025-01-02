package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.dtos.RatingUserMovieDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.Rating;
import fellowshipofthemovieclub.fellowship.jpaentities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserIdAndMovieId(Long userId, Long movieId);

    @Query("""
    SELECT new fellowshipofthemovieclub.fellowship.dtos.RatingUserMovieDTO(
        u.id, u.userName, m.originalTitle, m.posterURL, m.category, r.rating
    )
    FROM Rating r
    JOIN UserInfo u ON r.userId = u.id
    JOIN CurrentMovie m ON r.movieId = m.id
""")
    List<RatingUserMovieDTO> findRating();

}