package fellowshipofthemovieclub.fellowship.services;

import fellowshipofthemovieclub.fellowship.repositories.CurrentMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final CurrentMovieRepository currentMovieRepository;

    public MovieService(CurrentMovieRepository currentMovieRepository) {
        this.currentMovieRepository = currentMovieRepository;
    }
}
