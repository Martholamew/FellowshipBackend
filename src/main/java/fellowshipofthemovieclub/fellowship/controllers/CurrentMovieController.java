package fellowshipofthemovieclub.fellowship.controllers;

//import fellowshipofthemovieclub.fellowship.jpaentities.SelectedMovie;
import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.CurrentMovie;
import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import fellowshipofthemovieclub.fellowship.repositories.CurrentMovieRepository;
import fellowshipofthemovieclub.fellowship.repositories.UserRepository;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CurrentMovieController {

    private final CurrentMovieRepository currentMovieRepository;
    public CurrentMovieController(CurrentMovieRepository currentMovieRepository) {
        this.currentMovieRepository = currentMovieRepository;
    }

    @PostMapping("/movieByUser")
    CurrentMovie getCurrentMovie(@RequestBody UserDTO userDTO) {
        return currentMovieRepository.findTopByUserIdOrderByDateAddedDesc(userDTO.getUserId());
    }

    @PostMapping("/saveMovie")
    boolean loginUser(@RequestBody CurrentMovie thisWeeksSelectedMovie) {
        thisWeeksSelectedMovie.setDateAdded(LocalDateTime.now());
        currentMovieRepository.save(thisWeeksSelectedMovie);
        return true;
    }
}
