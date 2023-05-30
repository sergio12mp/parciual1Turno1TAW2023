package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.TipooperacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoOperacionRepository extends JpaRepository<TipooperacionEntity, Integer> {
}
