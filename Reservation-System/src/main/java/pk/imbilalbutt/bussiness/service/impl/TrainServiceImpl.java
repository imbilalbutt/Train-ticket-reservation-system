package pk.imbilalbutt.bussiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.TrainConverter;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.model.Train;
import pk.imbilalbutt.bussiness.repository.TrainRepository;
import pk.imbilalbutt.bussiness.service.TrainService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service(value="TrainService")
public class TrainServiceImpl implements TrainService {

    private static final Logger logger = Logger.getLogger(TrainService.class.getName());

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    TrainConverter trainConverter;

    @Override
    public TrainDto getById(Long id) {

        logger.log(Level.INFO, "TrainServiceImpl - getById() called id : {0}.", id);

        if(!ObjectUtils.isEmpty(id)){

            Optional<Train> train = trainRepository.findById(id);

            if(train.isPresent()) {

                logger.log(Level.INFO, "TrainServiceImpl - getById() Found : {0}.", train.get().getId());

                return trainConverter.convertToDtoUsingLambda.apply(train.get());
            }
        }

        logger.log(Level.INFO, "TrainServiceImpl - getById() NOT found.");

        return null;
    }

    @Override
    public List<TrainDto> getAll() {

        logger.log(Level.INFO, "TrainServiceImpl - getAll() - called in.");

        List<Train> trains = new ArrayList<>();
        try{
            trains = trainRepository.findAll();

            logger.log(Level.INFO, "TrainServiceImpl - getAll() - trains.size() : {0}.", trains.size());

            return !ObjectUtils.isEmpty(trains) ? trainConverter.convertToDtoList(trains) : new ArrayList<>();

        } catch (NullPointerException npe) {

            logger.log(Level.WARNING, "TrainServiceImpl - getAll() - Not Found.", trains.size());

            throw new NullPointerException("Not Found Any Single One.");

        }
    }

    @Override
    public Boolean delete(Long id) throws EntityNotFoundException {

        logger.log(Level.INFO, "TrainServiceImpl - delete() id : {0}.", id);

        try{

            Train train = trainRepository.getById(id);

            trainRepository.delete(train);

            logger.log(Level.INFO, "TrainServiceImpl - delete() successfully.");

            return Boolean.TRUE;

        } catch (EntityNotFoundException enf) {

            logger.log(Level.WARNING, "TrainServiceImpl - delete() failed.");

            return Boolean.FALSE;
        }

    }

    @Override
    public TrainDto saveOrUpdate(TrainDto dto) {

        logger.log(Level.INFO, "TrainServiceImpl - saveOrUpdate() called in." + dto);

        Train train = new Train();
        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                train = trainRepository.findById(dto.getId()).get();

                if (!ObjectUtils.isEmpty(train) && !ObjectUtils.isEmpty(train.getId())) {
                    train = trainConverter.convertToModelUsingLambda.apply(dto);
                    train = trainRepository.save(train);

                    logger.log(Level.INFO, "TrainServiceImpl - saveOrUpdate() - update(): {0} success.", train.getId());
                }
            } else {
                train = trainConverter.convertToModelUsingLambda.apply(dto);
                train = trainRepository.save(train);

                if (!ObjectUtils.isEmpty(train) && !ObjectUtils.isEmpty(train.getId())) {
                    dto.setId(train.getId());
                }
                logger.log(Level.INFO, "TrainServiceImpl - saveOrUpdate() - save(): {0} success." , train.getId());
            }
        }
        return dto;
    }
}
