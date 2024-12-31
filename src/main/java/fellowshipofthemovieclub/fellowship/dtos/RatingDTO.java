package fellowshipofthemovieclub.fellowship.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingDTO {

    private long userId;
    private long movieId;
    private double rating;
    private String message;

    public RatingDTO(long userId, long movieId, double rating, String message) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.message = message;
    }

    public RatingDTO(String message) {
        this.message = message;
    }
}
