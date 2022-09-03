package pk.imbilalbutt.bussiness.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass // -> this annotation does not create a table of this
public class BaseDto implements Serializable {

    protected Boolean status;
    protected LocalDateTime createdDate;
    protected String createdBy;
    protected LocalDateTime modifiedDate;
    protected String modifiedBy;
    protected Boolean isNonlocked;
}
