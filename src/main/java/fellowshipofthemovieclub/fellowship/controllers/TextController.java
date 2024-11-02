package fellowshipofthemovieclub.fellowship.controllers;

import fellowshipofthemovieclub.fellowship.jpaentities.TextDisplayValue;
import fellowshipofthemovieclub.fellowship.repositories.TextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//don't leave this as all
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/text")
public class TextController {

    private final TextRepository textRepository;

    public TextController(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    @GetMapping("/get")
    public List<TextDisplayValue> getAllText() {
        return textRepository.findAll();
    }

    @GetMapping("/textbyname")
    public TextDisplayValue getTextByName(@RequestParam(value = "nameOfValue", required = true) String nameOfValue){
        return textRepository.findByNameOfValue(nameOfValue);

    }

    @PostMapping("/updatetext")
    public boolean updateText(@RequestBody TextDisplayValue textDisplayValue){
        try {
            textRepository.save(textDisplayValue);
            return true;
        } catch (Exception e) {
            return false;
        }}
}
