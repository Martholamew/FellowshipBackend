package fellowshipofthemovieclub.fellowship.services;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class TMDBService {

    private final RestTemplate restTemplate;
    private static final String TMDB_API_KEY = "80f74cb55ee1276c456f94b19f1e8445";
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3/search/";

    public TMDBService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getMovieDetails(String movieName) {
        String url = TMDB_BASE_URL + "movie?query=" + movieName + "&api_key=" + TMDB_API_KEY;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
         return response.getBody();
    }
}
