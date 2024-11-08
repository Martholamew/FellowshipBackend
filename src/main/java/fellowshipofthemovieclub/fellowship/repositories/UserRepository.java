package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

   @EntityGraph(attributePaths = "roles")
   UserInfo findByUserName(String userName);
   @Query("SELECT u FROM UserInfo u LEFT JOIN FETCH u.roles WHERE u.userName = :userName")
   UserInfo findByUserNameWithRoles(@Param("userName") String userName);


   boolean existsByUserName(String userName);
}
