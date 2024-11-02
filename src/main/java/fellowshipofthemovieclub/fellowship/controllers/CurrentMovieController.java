package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.CurrentMovie;
import fellowshipofthemovieclub.fellowship.repositories.CurrentMovieRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RequestMapping("/currentmovie")
@RestController
public class CurrentMovieController {

    private final CurrentMovieRepository currentMovieRepository;
    public CurrentMovieController(CurrentMovieRepository currentMovieRepository) {
        this.currentMovieRepository = currentMovieRepository;
    }

    @PostMapping("/moviebyuser")
    CurrentMovie getCurrentMovie(@RequestBody UserDTO userDTO) {
        return currentMovieRepository.findTopByUserIdOrderByDateAddedDesc(userDTO.getUserId());
    }

    @PostMapping("/savemovie")
    boolean loginUser(@RequestBody CurrentMovie thisWeeksSelectedMovie) {
        thisWeeksSelectedMovie.setDateAdded(LocalDateTime.now());
        currentMovieRepository.save(thisWeeksSelectedMovie);
        return true;
    }
}
