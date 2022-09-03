package pk.imbilalbutt.bussiness.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.StationDto;
import pk.imbilalbutt.bussiness.model.Station;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component(value = "StationConverter")
public class StationConverter extends BaseConverter<Station, StationDto> {


    private static final Logger logger = Logger.getLogger(StationConverter.class.getName());


    /**
     * To Model Convert
     */

    public Station convertToModel(StationDto dto) {
        return this.convertToModelUsingLambda.apply(dto);
    }

    public Function<StationDto,Station > convertToModelUsingLambda = (dto) -> {
        Station entity = new Station();
        if(!ObjectUtils.isEmpty(dto)) {
            entity.setId(dto.getId() != null ? dto.getId() : null);
            entity.setStationCode(dto.getStationCode());
            entity.setCity(dto.getCity());

            super.convertToModel(entity, dto);

        }
        return entity;
    };

    public List<Station> convertToModelList(final Collection<StationDto> dtos) {
        return dtos.stream()
                .map(convertToModelUsingLambda)
                .collect(Collectors.toList());
    }

    /**
     * To DTO Convert
     */

    public StationDto convertToDto(Station entity){
        return this.convertToDtoUsingLambda.apply(entity);
    }

    public Function<Station, StationDto> convertToDtoUsingLambda = (entity) -> {
        StationDto dto = new StationDto();
        if (!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(entity.getId())) {
            dto.setId(entity.getId());
            dto.setStationCode(entity.getStationCode());
            dto.setCity(entity.getCity());

            super.convertToDTO(entity, dto);
        }
        return dto;
    };

    public StationDto convertToDTO(Station entity) {
        StationDto dto = new StationDto();
        if (!ObjectUtils.isEmpty(entity) && !ObjectUtils.isEmpty(entity.getId())) {
            dto.setId(entity.getId());
            dto.setStationCode(entity.getStationCode());
            dto.setCity(entity.getCity());

            super.convertToDTO(entity, dto);
        }
        return dto;
    }

    public List<StationDto> convertToDtoList(final Collection<Station> modelList) {
        return modelList.stream()
                .map(convertToDtoUsingLambda)
                .collect(Collectors.toList());
    }
}
