package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

   //dont be lazy List<UserInfo> findByNameAndEmail(String name, String email);
   UserInfo findFirstByNameAndEmail(String name, String email);
}
