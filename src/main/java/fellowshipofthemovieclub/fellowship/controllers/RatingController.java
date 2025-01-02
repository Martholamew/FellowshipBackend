package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.dtos.RatingDTO;
import fellowshipofthemovieclub.fellowship.dtos.RatingUserMovieDTO;
import fellowshipofthemovieclub.fellowship.services.RatingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/getRating")
    public double gerRatingForMovieByUser(@RequestBody RatingDTO ratingDTO){
        return ratingService.getMovieRatingForUser(ratingDTO);
    }

    @PostMapping("/saveRating")
    public boolean saveRating(@RequestBody RatingDTO rating) {
           return ratingService.saveOrUpdateRating(rating);
    }

    @GetMapping("/getAllRatingsWithUser")
    public List<RatingUserMovieDTO> getAllRatings(){
        return ratingService.getAllRatingsWithUserAndMovie();
    }
}

