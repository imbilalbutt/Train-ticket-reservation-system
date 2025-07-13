package pk.imbilalbutt.Train;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.service.TrainService;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainTest {

    @Mock
    private TrainService trainService;

    @Test
    public void testGetAllTrains() {
        // Mock the service response
        when(trainService.getAll()).thenReturn(Collections.singletonList(new TrainDto()));

        List<TrainDto> trains = trainService.getAll();
        assertNotNull(trains);
        assertFalse(trains.isEmpty());
    }

    @Test
    public void testGetTrainById() {
        // Create a mock response
        TrainDto mockTrain = new TrainDto();
        mockTrain.setId(1L);

        // Mock the service response
        when(trainService.getById(1L)).thenReturn(mockTrain);

        TrainDto train = trainService.getById(1L);
        assertNotNull(train);
        assertEquals(1L, train.getId());
    }
}


//package pk.imbilalbutt.Train;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import pk.imbilalbutt.bussiness.dto.TrainDto;
//import pk.imbilalbutt.bussiness.service.TrainService;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//public class TrainTest {
//
//    private TrainService trainService;
//
//    @Test
//    public void testGetAllTrains() {
//        List<TrainDto> trains = trainService.getAll();
//        assertNotNull(trains);
//        assertFalse(trains.isEmpty());
//    }
//
//    @Test
//    public void testGetTrainById() {
//        TrainDto train = trainService.getById(1L);
//        assertNotNull(train);
//        assertEquals(1L, train.getId());
//    }
//}