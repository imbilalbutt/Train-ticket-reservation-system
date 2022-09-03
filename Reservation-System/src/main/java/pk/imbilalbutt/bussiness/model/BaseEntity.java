package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass // -> this annotation does not create a table of this
public class BaseEntity {

    @Column(name = "ACTIVE_STATUS")
    protected Boolean status;

    @Column(name = "CREATED_DATE")
    protected LocalDateTime createdDate;

    @Column(name = "CREATED_BY")
    protected String createdBy;

    @Column(name = "MODIFIED_DATE")
    protected LocalDateTime modifiedDate;

    @Column(name = "MODIFIED_BY")
    protected String modifiedBy;

    @Column(name = "IS_NON_LOCKED")
    protected Boolean isNonlocked;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
