package toolcallTest.functions.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "USER_REALM")
public class UserRealmMstrEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "USER_MSTR_ENTITY_ID")
    private User userMstrEntity;

    @ManyToOne
    @JoinColumn(name = "REALM_MSTR_ENTITY_ID")
    private RealmMstrEntity realmMstrEntity;
}

