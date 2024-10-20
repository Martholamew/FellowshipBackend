package fellowshipofthemovieclub.fellowship.controllers;


import fellowshipofthemovieclub.fellowship.services.TautulliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//dont leave this as all
@CrossOrigin(origins = "*")
@RestController
public class TautulliController {

    @Autowired
    private TautulliService tautulliService;

    //unused
    @GetMapping("/tautulli/activity")
    public String getTautulliActivity() {
        return tautulliService.getCurrentActivity();
    }

    @GetMapping("/tautulli/playcount")
    public String getTautulliPlayCount(@RequestParam(value = "movieTitle", required = true) String movieTitle){
        return tautulliService.getPlayCount(movieTitle);
    }

    //unused
    @GetMapping("/tautulli/ratingkey")
    public String getTautulliRatingKey(@RequestParam(value = "movieTitle", required = true) String movieTitle){
        return tautulliService.getRatingKey(movieTitle);
    }
}
