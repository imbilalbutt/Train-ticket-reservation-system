package pk.imbilalbutt.bussiness.repository;

import org.springframework.stereotype.Repository;
import pk.imbilalbutt.bussiness.model.Train;

@Repository(value= "TrainRepository")
public interface TrainRepository extends BaseRepository<Train> {
}
