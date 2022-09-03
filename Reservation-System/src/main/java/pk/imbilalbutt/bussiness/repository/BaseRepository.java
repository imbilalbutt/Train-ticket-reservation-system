package pk.imbilalbutt.bussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import pk.imbilalbutt.bussiness.model.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<ENTITY extends BaseEntity> extends JpaRepository<ENTITY, Long> {
}
