package pk.imbilalbutt.bussiness.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pk.imbilalbutt.bussiness.dto.BaseDto;
import pk.imbilalbutt.bussiness.model.BaseEntity;

import java.util.List;

public interface BaseService<ENTITY extends BaseEntity, DTO extends BaseDto> {

    @Transactional(propagation = Propagation.REQUIRED)
    DTO getById(Long id);

    @Transactional(propagation = Propagation.REQUIRED)
    List<DTO> getAll();

    Boolean delete(Long id) throws RuntimeException;

    @Transactional(propagation = Propagation.REQUIRED)
    DTO saveOrUpdate(DTO dto) throws RuntimeException;
}
