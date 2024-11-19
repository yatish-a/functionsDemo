package toolcallTest.functions.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
@Table(name = "REALM_MSTR")
public class RealmMstrEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "REAL_NAME", length = 100, unique = true)
    private String realmName;


    @Column(name = "REALM_ISSUER", length = 500)
    private String realmIssuer;

    @Column(name = "REALM_TOKEN_ENDPOINT", length = 500)
    private String realmTokenEndPoint;

    @Column(name = "REALM_LOGOUT_ENDPOINT", length = 500)
    private String realmLogoutEndPoint;

    @Column(name = "CLIENT_ID", length = 500)
    private String clientId;


    @OneToMany(mappedBy = "realmMstrEntity")
    private List<UserRealmMstrEntity> userRealmEntities;


    @Column(name = "CLIENT_SECRET", length = 500)
    private String clientSecret;
}