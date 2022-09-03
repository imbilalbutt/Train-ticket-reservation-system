package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STATION")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Station extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="STATION_ID")
    private Long id;

    @Column(name="STATION_CODE")
    private String stationCode;

    @Column(name="CITY")
    private String city;

    // List of Trains which are initiating from here
    @OneToMany(mappedBy = "origin", fetch = FetchType.LAZY)
    private List<Train> trainOrigin;

    // List of Trains reaching to this final destination
    @OneToMany(mappedBy = "destination", fetch = FetchType.LAZY)
    private List<Train> trainDestination;
}
