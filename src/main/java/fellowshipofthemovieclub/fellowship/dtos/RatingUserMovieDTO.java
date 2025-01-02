package fellowshipofthemovieclub.fellowship.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingUserMovieDTO {

    private long userId; // From UserInfo
    private String userName; // From UserInfo
    private String movieTitle; // From CurrentMovie
    private String posterURL; // From CurrentMovie
    private String category;  // From CurrentMovie
    private double rating;    // From Rating
}