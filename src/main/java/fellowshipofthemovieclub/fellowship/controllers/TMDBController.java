package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.services.TMDBService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/tmdb")
@RestController
public class TMDBController {

    private final TMDBService tmdbService;

    public TMDBController(TMDBService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/searchmovie/{movieName}")
    public String getMovieDetails(@PathVariable String movieName) {
        return tmdbService.getMovieDetails(movieName);
    }
}