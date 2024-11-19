package toolcallTest.functions.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import toolcallTest.functions.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findFirstByUserNameEqualsIgnoreCaseOrEmailIdEqualsIgnoreCase(String userName, String emailId);

}
