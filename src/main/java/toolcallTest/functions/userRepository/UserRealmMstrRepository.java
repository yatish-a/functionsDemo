package toolcallTest.functions.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toolcallTest.functions.Entity.UserRealmMstrEntity;

import java.util.Optional;

@Repository
public interface UserRealmMstrRepository extends JpaRepository<UserRealmMstrEntity, Integer> {

    Optional<UserRealmMstrEntity> findFirstByUserMstrEntity_Id(Integer userEntityId);

}