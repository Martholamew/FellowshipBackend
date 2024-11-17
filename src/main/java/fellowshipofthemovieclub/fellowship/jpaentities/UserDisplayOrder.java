package fellowshipofthemovieclub.fellowship.jpaentities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class UserDisplayOrder {

    @Id
    private long id;

    @Column(nullable = false)
    private int displayOrder;

    @Column(nullable = false)
    private int UserId;
}
