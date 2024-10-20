package fellowshipofthemovieclub.fellowship.jpaentities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CurrentMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "original_title")
    private String originalTitle;

    @Column(name = "overview", columnDefinition="TEXT")
    private String overview;

    @Column(name = "poster_url")
    private String posterURL;

    @Column(name = "date_added", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateAdded;
    //this should be a join to the user table. it should convert the object to a dto to avoid passing the password around
    @Column(name = "user_id")
    private Long userId;

}
