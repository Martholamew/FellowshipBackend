package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.jpaentities.TextDisplayValue;
import fellowshipofthemovieclub.fellowship.repositories.TextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//don't leave this as all
@CrossOrigin(origins = "*")
@RestController
public class TextController {

    private final TextRepository textRepository;

    public TextController(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @GetMapping("/text")
    public List<TextDisplayValue> getAllText() {
        return textRepository.findAll();
    }

    @GetMapping("/textByname")
    public TextDisplayValue getTextByName(@RequestParam(value = "nameOfValue", required = true) String nameOfValue){
        System.out.println("name of value "+nameOfValue);
        return textRepository.findByNameOfValue(nameOfValue);

    }

    @PostMapping("/updateText")
    public boolean updateText(@RequestBody TextDisplayValue textDisplayValue){
        try {
            textRepository.save(textDisplayValue);
            return true;
        } catch (Exception e) {
            return false;
        }}
}
