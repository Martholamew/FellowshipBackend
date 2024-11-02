package fellowshipofthemovieclub.fellowship.controllers;


import fellowshipofthemovieclub.fellowship.services.TautulliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//dont leave this as all
@CrossOrigin(origins = "*")
@RequestMapping("/tautulli")
@RestController
public class TautulliController {

    @Autowired
    private TautulliService tautulliService;

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
