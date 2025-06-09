package pk.imbilalbutt.Train;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.repository.TrainRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import pk.imbilalbutt.bussiness.service.TrainService;
import pk.imbilalbutt.bussiness.service.impl.TrainServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TrainTest {


    @Mock
    private TrainRepository trainRepository;

    @InjectMocks
    private TrainServiceImpl trainService;

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
        assertEquals(1, train.getId());
    }

//    @Test
//    public void testSearchTrains() {
//        List<Train> results = trainService.searchTrains("Express");
//        assertNotNull(results);
//        assertTrue(results.stream().allMatch(t -> t.getName().contains("Express")));
//    }
}