package fellowshipofthemovieclub.fellowship.repositories;

import fellowshipofthemovieclub.fellowship.jpaentities.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}