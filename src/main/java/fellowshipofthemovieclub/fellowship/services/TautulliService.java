package fellowshipofthemovieclub.fellowship.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Service
public class TautulliService {

    private static final String TAUTULLI_API_KEY = "bbea46fe0d64478bb70120b37b433d82";  // Replace with your actual API key
    private static final String TAUTULLI_BASE_URL = "https://iseeyou.mattflixrequester.com/api/v2?"; // Replace with your Tautulli URL

    private final RestTemplate restTemplate;

    public TautulliService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCurrentActivity() {
        URI uri = UriComponentsBuilder.fromHttpUrl(TAUTULLI_BASE_URL)
                .queryParam("apikey", TAUTULLI_API_KEY)
                .queryParam("cmd", "get_activity")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    public String getPlayCount(int ratingKey) {
        URI uri = UriComponentsBuilder.fromHttpUrl(TAUTULLI_BASE_URL)
                .queryParam("apikey", TAUTULLI_API_KEY)
                .queryParam("cmd", "get_item_watch_time_stats")
                .queryParam("rating_key", ratingKey)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }
}
