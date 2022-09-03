package pk.imbilalbutt.bussiness.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TrainDto extends BaseDto {

    private Long id;
    private String trainCode;

    private StationDto origin;
    private StationDto destination;
}
