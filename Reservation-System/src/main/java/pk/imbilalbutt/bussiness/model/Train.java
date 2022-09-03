package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TRAIN")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Train extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TRAIN_ID")
    private Long id;

    @Column(name="TRAIN_CODE")
    private String trainCode;

    @ManyToOne(targetEntity = Station.class, fetch = FetchType.EAGER)
    @JoinColumn(name="FK_ORIGIN_STATION_ID")
    private Station origin;

    @ManyToOne(targetEntity = Station.class, fetch = FetchType.EAGER)
    @JoinColumn(name="FK_DESTINATION_STATION_ID")
    private Station destination;

}
