//package pk.imbilalbutt.spring.real.starter.service.impl;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.ObjectUtils;
//import BaseConverter;
//import BaseDto;
//import BaseEntity;
//import User;
//import BaseRepository;
//import BaseService;
//
//import java.util.List;
//import java.util.Optional;
//
//public class BaseServiceImpl<ENTITY extends BaseEntity, DTO extends BaseDto> implements BaseService<ENTITY, DTO> {
//
//    private static final Logger LOG = LoggerFactory.getLogger(BaseService.class);
//
//    private BaseRepository<ENTITY> repository;
//    private BaseConverter<ENTITY, DTO> converter;
//
//    public BaseServiceImpl(BaseRepository<ENTITY> repository, BaseConverter<ENTITY, DTO> converter) {
//        this.repository = repository;
//        this.converter = converter;
//    }
//
//    @Override
//    public DTO getById(Long id) {
//
//        if(!ObjectUtils.isEmpty(id)){
//
//            ENTITY entity = repository.findById(id).orElse(null);
//
//            if(ObjectUtils.isEmpty(entity)) {
//                LOG.error("Failed to save the entity {} " , entity.getClass());
//                return null;
//            }
//
////            if(user.isPresent()) {
////                return userConverter.convertToDtoUsingLambda.apply(user.get());
////            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<DTO> getAll() {
//        return null;
//    }
//
//    @Override
//    public Boolean delete(Long id) {
//        return null;
//    }
//
//    @Override
//    public DTO saveOrUpdate(DTO dto) {
//        return null;
//    }
//}
