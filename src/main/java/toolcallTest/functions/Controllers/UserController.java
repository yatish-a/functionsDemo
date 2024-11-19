package toolcallTest.functions.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toolcallTest.functions.Entity.User;
import toolcallTest.functions.Entity.UserRealmMstrEntity;
import toolcallTest.functions.services.KeycloakService;
import toolcallTest.functions.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KeycloakService keycloakService;

    @PostMapping("/disable")
    public ResponseEntity<String> disableUser(@RequestParam String env, @RequestParam String email) {
        return ResponseEntity.ok(userService.disableUserforEnv(email, env));
    }




}
