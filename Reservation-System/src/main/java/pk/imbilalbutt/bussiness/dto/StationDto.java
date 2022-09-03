package pk.imbilalbutt.bussiness.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class StationDto extends BaseDto{

    private Long id;
    private String stationCode;
    private String city;

//    private List<TrainDto> trainOrigin;
//    private List<TrainDto> trainDestination;

}
