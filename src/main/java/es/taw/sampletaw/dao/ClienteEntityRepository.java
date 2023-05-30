package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Integer> {
}
