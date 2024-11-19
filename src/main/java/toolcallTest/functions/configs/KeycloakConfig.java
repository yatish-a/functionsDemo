package toolcallTest.functions.configs;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class KeycloakConfig {

    public Keycloak getKeycloakClient(Map<String, String> envConfig, String realmName) {
        return KeycloakBuilder.builder()
                .serverUrl(envConfig.get("keycloak_server_url"))
                .realm(envConfig.getOrDefault("user_realm_name", "master"))
                .username(envConfig.get("keycloak_username"))
                .password(envConfig.get("keycloak_password"))
                .clientId("admin-cli")
                .resteasyClient(new ResteasyClientBuilderImpl().disableTrustManager().build()) // Customize SSL if needed
                .build();
    }
}
