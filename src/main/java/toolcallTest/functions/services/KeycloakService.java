package toolcallTest.functions.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toolcallTest.functions.configs.KeycloakConfig;

import java.util.Map;

@Service
public class KeycloakService {

    @Autowired
    private KeycloakConfig keycloakConfig;

    public void disableUser(Map<String, String> envConfig, String userId, String realmName) {
        Keycloak keycloak = keycloakConfig.getKeycloakClient(envConfig, realmName);
        try {
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEnabled(false);
            keycloak.realm(realmName).users().get(userId).update(userRepresentation);
            System.out.println("User " + userId + " disabled in realm " + realmName);
        } catch (Exception e) {
            System.out.println("Error disabling user: " + e.getMessage());
        } finally {
            keycloak.close();
        }
    }

    public void enableUser(Map<String, String> envConfig, String userId, String realmName) {
        Keycloak keycloak = keycloakConfig.getKeycloakClient(envConfig, realmName);
        try {
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEnabled(true);
            keycloak.realm(realmName).users().get(userId).update(userRepresentation);
            System.out.println("User " + userId + " enabled in realm " + realmName);
        } catch (Exception e) {
            System.out.println("Error enabling user: " + e.getMessage());
        } finally {
            keycloak.close();
        }
    }
}
