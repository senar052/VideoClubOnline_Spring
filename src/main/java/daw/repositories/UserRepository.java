package daw.repositories;

import daw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByNameLike(@Param("name") String name );

    User findByEmailLike(@Param("email") String email );



}
