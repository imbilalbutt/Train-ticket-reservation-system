package pk.imbilalbutt.bussiness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="TRAVELLER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Traveller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVELLER_ID")
    private Long id;


//    Jiske paas @JoinColumn hoga, it means uske table mein foreign key hogi
//    yahan JoinColumn TRAVELLER class mein hai. DB mein jab TRAVELLER ka table
//    create hoga tou wahan par User ka id number as foreign key (TRAVELLER table me) pari hogi.
//    And TRAVELLER class (which has foreign key in its table) is called "Owning side/entity"
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_TRAVELLER_ID", referencedColumnName = "USER_ID")
    private User user;
}
