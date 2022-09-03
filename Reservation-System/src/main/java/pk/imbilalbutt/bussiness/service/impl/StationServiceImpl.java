package pk.imbilalbutt.bussiness.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.converter.StationConverter;
import pk.imbilalbutt.bussiness.dto.StationDto;
import pk.imbilalbutt.bussiness.model.Station;
import pk.imbilalbutt.bussiness.repository.StationRepository;
import pk.imbilalbutt.bussiness.service.StationService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service(value="StationService")
public class StationServiceImpl implements StationService {

    private static final Logger logger = Logger.getLogger(StationServiceImpl.class.getName());

    @Autowired
    StationRepository stationRepository;

    @Autowired
    StationConverter stationConverter;

    @Override
    public StationDto getById(Long id) {

        logger.log(Level.INFO, "StationServiceImpl - getById() called id : {0}.", id);

        if(!ObjectUtils.isEmpty(id)){

            Optional<Station> station = stationRepository.findById(id);

            if(station.isPresent()) {

                logger.log(Level.INFO, "StationServiceImpl - getById() Found : {0}.", station.get().getId());

                return stationConverter.convertToDtoUsingLambda.apply(station.get());
            }
        }

        logger.log(Level.INFO, "StationServiceImpl - getById() NOT found.");

        return null;
    }

    @Override
    public List<StationDto> getAll() {

        logger.log(Level.INFO, "StationServiceImpl - getAll() - called in.");

        List<Station> stations = new ArrayList<>();
        try{
            stations = stationRepository.findAll();

            logger.log(Level.INFO, "StationServiceImpl - getAll() - stations.size() : {0}.", stations.size());

            return !ObjectUtils.isEmpty(stations) ? stationConverter.convertToDtoList(stations) : new ArrayList<>();

        } catch (NullPointerException npe) {

            logger.log(Level.WARNING, "StationServiceImpl - getAll() - Not Found.", stations.size());

            throw new NullPointerException("Not Found Any Single One.");

        }
    }

    @Override
    public Boolean delete(Long id) throws EntityNotFoundException {

        logger.log(Level.INFO, "StationServiceImpl - delete() id : {0}.", id);

        try{

            Station station = stationRepository.getById(id);

            stationRepository.delete(station);

            logger.log(Level.INFO, "StationServiceImpl - delete() successfully.");

            return Boolean.TRUE;

        } catch (EntityNotFoundException enf) {

            logger.log(Level.WARNING, "StationServiceImpl - delete() failed.");

            return Boolean.FALSE;
        }

    }

    @Override
    public StationDto saveOrUpdate(StationDto dto) {

        logger.log(Level.INFO, "StationServiceImpl - saveOrUpdate() called in." + dto);

        Station station = new Station();
        if (!ObjectUtils.isEmpty(dto)) {

            if (!ObjectUtils.isEmpty(dto.getId())) {

                station = stationRepository.findById(dto.getId()).get();

                if (!ObjectUtils.isEmpty(station) && !ObjectUtils.isEmpty(station.getId())) {
                    station = stationConverter.convertToModelUsingLambda.apply(dto);
                    station = stationRepository.save(station);

                    logger.log(Level.INFO, "StationServiceImpl - saveOrUpdate() - update(): {0} success.", station.getId());
                }
            } else {
                station = stationConverter.convertToModelUsingLambda.apply(dto);
                station = stationRepository.save(station);

                if (!ObjectUtils.isEmpty(station) && !ObjectUtils.isEmpty(station.getId())) {
                    dto.setId(station.getId());
                }
                logger.log(Level.INFO, "StationServiceImpl - saveOrUpdate() - save(): {0} success." , station.getId());
            }
        }
        return dto;
    }
}
