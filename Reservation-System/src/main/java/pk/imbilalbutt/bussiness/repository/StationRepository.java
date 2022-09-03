package pk.imbilalbutt.bussiness.repository;

import org.springframework.stereotype.Repository;
import pk.imbilalbutt.bussiness.model.Station;

@Repository(value= "StationRepository")
public interface StationRepository extends BaseRepository<Station> {
}
