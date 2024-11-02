package fellowshipofthemovieclub.fellowship.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TautulliService {

    private static final String TAUTULLI_API_KEY = "bbea46fe0d64478bb70120b37b433d82";  // Replace with your actual API key
    private static final String TAUTULLI_BASE_URL = "https://iseeyou.mattflixrequester.com/api/v2?"; // Replace with your Tautulli URL

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;


    public TautulliService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getCurrentActivity() {
        URI uri = UriComponentsBuilder.fromHttpUrl(TAUTULLI_BASE_URL)
                .queryParam("apikey", TAUTULLI_API_KEY)
                .queryParam("cmd", "get_activity")
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    public String getPlayCount(String movieTitle) {

        int ratingKey=Integer.parseInt(getRatingKey(movieTitle));
        URI uri = UriComponentsBuilder.fromHttpUrl(TAUTULLI_BASE_URL)
                .queryParam("apikey", TAUTULLI_API_KEY)
                .queryParam("cmd", "get_item_watch_time_stats")
                .queryParam("rating_key", ratingKey)
                .build()
                .toUri();

        return restTemplate.getForObject(uri, String.class);
    }

    public String getRatingKey(String movieTitle){
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(TAUTULLI_BASE_URL)
                    .queryParam("apikey", TAUTULLI_API_KEY)
                    .queryParam("cmd", "get_library_media_info")
                    .queryParam("section_id", 1)//this is found by looking at the id in tautulli
                    .queryParam("search", movieTitle)
                    .build()
                    .toUri();
            String response = restTemplate.getForObject(uri, String.class);
            // Parse the JSON response using Jackson
            JsonNode jsonResponse = objectMapper.readTree(response);
            JsonNode dataNode = jsonResponse.path("response").path("data").path("data");
            // Check if the data array has at least one element
            if (dataNode.isArray() && !dataNode.isEmpty()) {
                JsonNode firstMovie = dataNode.get(0);
                return firstMovie.path("rating_key").asText();
            } else {
                System.out.println("No matching movie found."); // Log when no movies are found
            }
        } catch (Exception e) {
            e.printStackTrace();//do better
        return null;
    }
        return null;
    }
}
