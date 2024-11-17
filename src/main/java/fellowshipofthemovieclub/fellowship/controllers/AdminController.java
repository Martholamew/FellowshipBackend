package fellowshipofthemovieclub.fellowship.controllers;


import fellowshipofthemovieclub.fellowship.dtos.UserDTO;
import fellowshipofthemovieclub.fellowship.jpaentities.CurrentMovie;
import fellowshipofthemovieclub.fellowship.jpaentities.TextDisplayValue;
import fellowshipofthemovieclub.fellowship.jpaentities.UserDisplayOrder;
import fellowshipofthemovieclub.fellowship.repositories.CurrentMovieRepository;
import fellowshipofthemovieclub.fellowship.repositories.UserDisplayOrderRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final UserDisplayOrderRepository userDisplayOrderRepository;

    public AdminController(UserDisplayOrderRepository userDisplayOrderRepository) {
        this.userDisplayOrderRepository = userDisplayOrderRepository;
    }


    @GetMapping("/getdisplayorder")
    List<UserDisplayOrder> getDisplayOrder() {
        return userDisplayOrderRepository.findAll();
    }

    @PostMapping("/updatedisplayorder")
    boolean updateDisplayOrder(@RequestBody List<UserDisplayOrder> userDisplayOrders){
        try {
            for (UserDisplayOrder usd : userDisplayOrders) {
                userDisplayOrderRepository.save(usd);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
