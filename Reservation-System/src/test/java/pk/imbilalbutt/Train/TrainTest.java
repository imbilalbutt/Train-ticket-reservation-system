package pk.imbilalbutt.Train;

import org.junit.jupiter.api.Test;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.service.TrainService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrainTest {

    private TrainService trainService;

    @Test
    public void testGetAllTrains() {
        List<TrainDto> trains = trainService.getAll();
        assertNotNull(trains);
        assertFalse(trains.isEmpty());
    }

    @Test
    public void testGetTrainById() {
        TrainDto train = trainService.getById(1L);
        assertNotNull(train);
        assertEquals(1L, train.getId());
    }

//    @Test
//    public void testSearchTrains() {
//        List<Train> results = trainService.searchTrains("Express");
//        assertNotNull(results);
//        assertTrue(results.stream().allMatch(t -> t.getName().contains("Express")));
//    }
}