package fellowshipofthemovieclub.fellowship.services;

import fellowshipofthemovieclub.fellowship.dtos.RatingDTO;
import fellowshipofthemovieclub.fellowship.dtos.RatingUserMovieDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.Rating;
import fellowshipofthemovieclub.fellowship.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public double getMovieRatingForUser(RatingDTO ratingDTO){
        System.out.println(ratingDTO);
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndMovieId(ratingDTO.getUserId(), ratingDTO.getMovieId());

        return existingRating.map(Rating::getRating).orElse(2.5);         //setting default, should be in constant

    }

    public List<RatingUserMovieDTO> getAllRatingsWithUserAndMovie(){
        return ratingRepository.findRating();
    }

    public boolean saveOrUpdateRating(RatingDTO ratingRequest) {
        try {
            Optional<Rating> existingRating = ratingRepository.findByUserIdAndMovieId(
                    ratingRequest.getUserId(),
                    ratingRequest.getMovieId()
            );

            if (existingRating.isPresent()) {
                Rating rating = existingRating.get();
                rating.setRating(ratingRequest.getRating());
                ratingRepository.save(rating);
            } else {
                Rating newRating = new Rating(ratingRequest);
                ratingRepository.save(newRating);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
