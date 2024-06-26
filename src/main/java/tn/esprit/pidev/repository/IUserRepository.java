package tn.esprit.pidev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.pidev.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    User findUserByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prename);

    @Query("SELECT u FROM User u WHERE u.id <> :commentingUserId")
    List<User> findAllExceptCommentingUser(@Param("commentingUserId") Long commentingUserId);

}
