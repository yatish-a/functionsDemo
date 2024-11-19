package toolcallTest.functions.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@Table(name = "USER_MSTR")
@NoArgsConstructor
@SuperBuilder
public class User extends BaseEntity implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME", length = 100, unique = true)
    private String userName;

    @Column(name = "EMAIL_ID", length = 100, unique = true)
    private String emailId;

    @Column(name = "USER_ID", length = 100, unique = true)
    private String userId;


    @Column(name = "FIRST_TIME_LOGIN", nullable = false, columnDefinition = "TINYINT(1) NULL DEFAULT '0'")
    private boolean firstTimeLogin;
}