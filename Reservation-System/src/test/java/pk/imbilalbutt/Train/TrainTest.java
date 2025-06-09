package pk.imbilalbutt.Train;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pk.imbilalbutt.bussiness.configuration.PasswordConfiguration;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.repository.TrainRepository;
import pk.imbilalbutt.bussiness.service.TrainService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
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
        assertEquals(1L, train.getId());
    }

//    @Test
//    public void testSearchTrains() {
//        List<Train> results = trainService.searchTrains("Express");
//        assertNotNull(results);
//        assertTrue(results.stream().allMatch(t -> t.getName().contains("Express")));
//    }
}