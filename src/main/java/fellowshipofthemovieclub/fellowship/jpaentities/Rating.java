package fellowshipofthemovieclub.fellowship.jpaentities;


import fellowshipofthemovieclub.fellowship.dtos.RatingDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long movieId;

    @Column(nullable = false)
    private double rating;

    public Rating(RatingDTO rating) {
        this.userId = rating.getUserId();
        this.movieId = rating.getMovieId();
        this.rating = rating.getRating();
    }
}
