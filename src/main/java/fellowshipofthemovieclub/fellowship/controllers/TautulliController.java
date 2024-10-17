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

    @GetMapping("/tautulli/activity")
    public String getTautulliActivity() {
        return tautulliService.getCurrentActivity();
    }

    @GetMapping("/tautulli/playcount")
    public String getTautulliPlayCount(@RequestParam(value = "ratingKey", required = true) String ratingKey){
        return tautulliService.getPlayCount(Integer.parseInt(ratingKey));
    }
}
