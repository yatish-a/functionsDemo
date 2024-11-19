package toolcallTest.functions.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class BaseEntity implements java.io.Serializable {

    @Column(name = "CREATED_BY", length = 50)
    @JsonIgnore
    private String createdBy;

    @Column(name = "UPDATED_BY", length = 50)
    @JsonIgnore
    private String updatedBy;

    @Column(name = "CREATED_DATE")
    @Basic
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    @Basic
    private LocalDateTime updatedDate;

    @Column(name = "DELETED", nullable = false, columnDefinition = "TINYINT(1) NULL DEFAULT '0'")
    @JsonIgnore
    private boolean deleted;

    @Column(name = "VERSION")
    @Version
    @JsonIgnore
    private Long version = 0L;

}