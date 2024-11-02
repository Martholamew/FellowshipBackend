package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

   UserInfo findByUserName(String userName);

   boolean existsByUserName(String userName);
}
