package fellowshipofthemovieclub.fellowship.jpaentities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class TextDisplayValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String textValue;
    private String nameOfValue;



}

