package toolcallTest.functions.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import toolcallTest.functions.Entity.User;
import toolcallTest.functions.Entity.UserRealmMstrEntity;
import toolcallTest.functions.userRepository.UserRealmMstrRepository;
import toolcallTest.functions.userRepository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRealmMstrRepository userRealmMstrRepository;

    private final KeycloakService keycloakService;

    public record Request(String email, String env) {}
    public record Response(String response) {}


    public String disableUserforEnv(String email, String env) {
        Map<String, String> envConfig = getEnvConfig(env);
        Optional<User> user = getUserId(email);

        if (user.isPresent() ) {
            Optional<UserRealmMstrEntity> realmName = getUserRealm(user.get().getId());
            if(realmName.isPresent()){
                keycloakService.disableUser(envConfig, user.get().getUserId(), realmName.get().getRealmMstrEntity().getRealmName());
                return email + " disabled";
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Response disableUserforEnv(Request r) {
        Map<String, String> envConfig = getEnvConfig(r.env);
        Optional<User> user = getUserId(r.email);

        if (user.isPresent() ) {
            Optional<UserRealmMstrEntity> realmName = getUserRealm(user.get().getId());
            if(realmName.isPresent()){
                keycloakService.disableUser(envConfig, user.get().getUserId(), realmName.get().getRealmMstrEntity().getRealmName());
                return new Response(r.email + " disabled");
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    private static Map<String, String> getEnvConfig(String env) {
        Map<String, String> envConfig = new HashMap<>();

        if ("qa".equalsIgnoreCase(env)) {
            envConfig.put("keycloak_server_url", "http://keycloak.qaone.carscan.ai:8080/auth/");
            envConfig.put("keycloak_username", "admin");
            envConfig.put("keycloak_password", "admin@123");
            envConfig.put("verify_ssl", "true");
            envConfig.put("user_realm_name", "master"); // Optional, change as needed
        } else if ("staging".equalsIgnoreCase(env)) {
            envConfig.put("keycloak_server_url", "http://keycloak.staging.carscan.ai:8080/auth/");
            envConfig.put("keycloak_username", "admin");
            envConfig.put("keycloak_password", "ev5zf8Ew9R9tqNXP");
            envConfig.put("verify_ssl", "true");
            envConfig.put("user_realm_name", "master"); // Optional, change as needed
        } else {
            throw new IllegalArgumentException("Invalid environment specified: " + env);
        }

        return envConfig;
    }

    public Optional<User> getUserId(String email) {
        List<User> li = userRepository.findAll();
        return userRepository.findFirstByUserNameEqualsIgnoreCaseOrEmailIdEqualsIgnoreCase(email.toLowerCase(),email.toLowerCase());
    }

    public Optional<UserRealmMstrEntity> getUserRealm(Integer id) {
        return userRealmMstrRepository.findFirstByUserMstrEntity_Id(id);
    }
}
