package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.TextDisplayValue;
import fellowshipofthemovieclub.fellowship.jpaentities.UserDisplayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDisplayOrderRepository extends JpaRepository<UserDisplayOrder, Long> {
    TextDisplayValue findByDisplayOrder(int displayOrder);
}
