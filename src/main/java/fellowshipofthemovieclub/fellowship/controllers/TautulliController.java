package fellowshipofthemovieclub.fellowship.controllers;


import fellowshipofthemovieclub.fellowship.services.TautulliService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tautulli")
@RestController
public class TautulliController {


    private final TautulliService tautulliService;

    public TautulliController(TautulliService tautulliService) {
        this.tautulliService = tautulliService;
    }

    //unused
    @GetMapping("/activity")
    public String getTautulliActivity() {
        return tautulliService.getCurrentActivity();
    }

    @GetMapping("/playcount")
    public String getTautulliPlayCount(@RequestParam(value = "movieTitle", required = true) String movieTitle){
        return tautulliService.getPlayCount(movieTitle);
    }

    //unused
    @GetMapping("/ratingkey")
    public String getTautulliRatingKey(@RequestParam(value = "movieTitle", required = true) String movieTitle){
        return tautulliService.getRatingKey(movieTitle);
    }
}
