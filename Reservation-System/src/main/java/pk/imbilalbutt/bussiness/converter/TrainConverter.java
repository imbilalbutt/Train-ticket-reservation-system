package pk.imbilalbutt.bussiness.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.TrainDto;
import pk.imbilalbutt.bussiness.model.Station;
import pk.imbilalbutt.bussiness.model.Train;
import pk.imbilalbutt.bussiness.repository.StationRepository;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component(value = "TrainConverter")
public class TrainConverter extends BaseConverter<Train, TrainDto> {

    private static final Logger logger = Logger.getLogger(TrainConverter.class.getName());

    @Autowired
    @Qualifier(value="StationRepository")
    StationRepository stationRepository;

    @Autowired
    @Qualifier(value="StationConverter")
    StationConverter stationConverter;

    @Autowired
    public TrainConverter(StationRepository stationRepository, StationConverter stationConverter) {
        this.stationRepository = stationRepository;
        this.stationConverter = stationConverter;
    }

    /**
     * To Model Convert
     */

    public Train convertToModel(TrainDto dto) {
        return this.convertToModelUsingLambda.apply(dto);
    }

    public Function<TrainDto, Train> convertToModelUsingLambda = (dto) -> {
        Train entity = new Train();
        if(!ObjectUtils.isEmpty(dto)) {
            entity.setId(dto.getId() != null ? dto.getId() : null);
            entity.setTrainCode(dto.getTrainCode());

            if(!ObjectUtils.isEmpty(dto.getDestination()) && !ObjectUtils.isEmpty(dto.getDestination().getId())){
                Station destination = stationRepository.getById(dto.getDestination().getId());
                entity.setDestination(destination);
            }

            if(!ObjectUtils.isEmpty(dto.getOrigin()) && !ObjectUtils.isEmpty(dto.getOrigin().getId())){
                Station origin = stationRepository.getById(dto.getOrigin().getId());
                entity.setDestination(origin);
            }

            super.convertToModel(entity, dto);

        }
        return entity;
    };

    public List<Train> convertToModelList(final Collection<TrainDto> dtos) {
        return dtos.stream()
                .map(convertToModelUsingLambda)
                .collect(Collectors.toList());
    }

    /**
     * To DTO Convert
     */

    public TrainDto convertToDto(Train entity){
        return this.convertToDtoUsingLambda.apply(entity);
    }

    public Function<Train, TrainDto> convertToDtoUsingLambda = (entity) -> {
        TrainDto dto = new TrainDto();
        if (!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(entity.getId())) {
            dto.setId(entity.getId());
            dto.setTrainCode(entity.getTrainCode());

            if(!ObjectUtils.isEmpty(entity.getDestination()) && !ObjectUtils.isEmpty(entity.getDestination().getId())){
                dto.setDestination(stationConverter.convertToDto(entity.getDestination()));
            }

            if(!ObjectUtils.isEmpty(entity.getOrigin()) && !ObjectUtils.isEmpty(entity.getOrigin().getId())){
                dto.setOrigin(stationConverter.convertToDto(entity.getOrigin()));
            }

            super.convertToDTO(entity, dto);
        }
        return dto;
    };

    public List<TrainDto> convertToDtoList(final Collection<Train> modelList) {
        return modelList.stream()
                .map(convertToDtoUsingLambda)
                .collect(Collectors.toList());
    }
}
