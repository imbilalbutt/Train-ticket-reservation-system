package pk.imbilalbutt.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pk.imbilalbutt.bussiness.model.Station;
import pk.imbilalbutt.bussiness.model.Train;
import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {

    @Override
    Optional<Train> findById(Long aLong);

    List<Train> findByDestination(Station station);

    List<Train> findByOrigin(Station station);

    @Query("SELECT t FROM Train t WHERE t.destination.city = :stationName")
    List<Train> findByDestinationName(String stationName);

    @Query("SELECT t FROM Train t WHERE t.origin.city = :stationName")
    List<Train> findByOriginName(String stationName);

    List<Train> findByDestinationAndOrigin(Station destination, Station origin);

    @Override
    @Query("SELECT t FROM Train t")
    List<Train> findAll();

}


//package pk.imbilalbutt.bussiness.repository;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//import pk.imbilalbutt.bussiness.model.Station;
//import pk.imbilalbutt.bussiness.model.Train;
//
//import java.util.Optional;
//
//@Repository(value= "TrainRepository")
//public interface TrainRepository extends BaseRepository<Train> {
//
//    @Override
//    Optional<Train> findById(Long aLong);
//
//    Optional<Train> findByDestination(Station station);
//

//
//    Optional<Train> findByOrigin(String station);
//
//    Optional<Train> findByDestinationAndOrigin(Station station, Station origin);
//
//    @Query(" SELECT Train t " +
//            " FROM Train ")
//    Optional<Train> getAll();
//
//}
