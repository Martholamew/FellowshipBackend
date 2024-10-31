package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.TextDisplayValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<TextDisplayValue, Long> {
     TextDisplayValue findByNameOfValue(String nameOfValue);
}
