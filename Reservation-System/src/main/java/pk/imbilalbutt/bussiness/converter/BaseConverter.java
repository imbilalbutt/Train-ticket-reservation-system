package pk.imbilalbutt.bussiness.converter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import pk.imbilalbutt.bussiness.dto.BaseDto;
import pk.imbilalbutt.bussiness.model.BaseEntity;

import java.time.LocalDateTime;

public class BaseConverter<ENTITY extends BaseEntity, DTO extends BaseDto> {

    final private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    protected ENTITY convertToModel(ENTITY entity, DTO dto) {

        if (!ObjectUtils.isEmpty(dto)) {

            entity.setCreatedBy(dto.getCreatedBy() == null ? authentication.getName() : dto.getCreatedBy());
            entity.setCreatedDate(dto.getCreatedDate() == null ? LocalDateTime.now() : dto.getCreatedDate());
            entity.setStatus(dto.getStatus() == null || dto.getStatus());
            entity.setModifiedBy(dto.getModifiedBy() == null ? authentication.getName() : dto.getModifiedBy());
            entity.setModifiedDate(dto.getModifiedDate() == null ? LocalDateTime.now() : dto.getModifiedDate());
            entity.setIsNonlocked(dto.getIsNonlocked() == null || dto.getIsNonlocked());
        }
        return entity;
    }

    protected ENTITY convertToModel(DTO dto) {

        ENTITY entity = null;

        if (!ObjectUtils.isEmpty(dto)) {

            entity.setCreatedBy(dto.getCreatedBy() == null ? authentication.getName() : dto.getCreatedBy());
            entity.setCreatedDate(dto.getCreatedDate() == null ? LocalDateTime.now() : dto.getCreatedDate());
            entity.setStatus(dto.getStatus() == null || dto.getStatus());
            entity.setModifiedBy(dto.getModifiedBy() == null ? authentication.getName() : dto.getModifiedBy());
            entity.setModifiedDate(dto.getModifiedDate() == null ? LocalDateTime.now() : dto.getModifiedDate());
            entity.setIsNonlocked(dto.getIsNonlocked() == null || dto.getIsNonlocked());
        }
        return entity;
    }

    protected DTO convertToDTO(ENTITY entity, DTO dto) {

        if (!ObjectUtils.isEmpty(entity)) {
            dto.setCreatedBy(entity.getCreatedBy() == null ? authentication.getName() : entity.getCreatedBy());
            dto.setCreatedDate(entity.getCreatedDate() == null ? LocalDateTime.now() : entity.getCreatedDate());
            dto.setStatus(entity.getStatus() == null || entity.getStatus());
            dto.setModifiedBy(entity.getModifiedBy() == null ? authentication.getName() : entity.getModifiedBy());
            dto.setModifiedDate(entity.getModifiedDate() == null ? LocalDateTime.now() : entity.getModifiedDate());
            dto.setIsNonlocked(entity.getIsNonlocked() == null || entity.getIsNonlocked());
        }
        return dto;
    }

    protected DTO convertToDTO(ENTITY entity) {

        DTO dto = null;

        if (!ObjectUtils.isEmpty(entity)) {

            dto.setCreatedBy(entity.getCreatedBy() == null ? authentication.getName() : entity.getCreatedBy());
            dto.setCreatedDate(entity.getCreatedDate() == null ? LocalDateTime.now() : entity.getCreatedDate());
            dto.setStatus(entity.getStatus() == null || entity.getStatus());
            dto.setModifiedBy(entity.getModifiedBy() == null ? authentication.getName() : entity.getModifiedBy());
            dto.setModifiedDate(entity.getModifiedDate() == null ? LocalDateTime.now() : entity.getModifiedDate());
            dto.setIsNonlocked(entity.getIsNonlocked() == null || entity.getIsNonlocked());
        }
        return dto;
    }


}
