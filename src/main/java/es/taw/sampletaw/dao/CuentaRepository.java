package es.taw.sampletaw.dao;

import es.taw.sampletaw.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    @Query("select cuenta from CuentaEntity cuenta  where cuenta.activa=0 and cuenta.iban<>:miIban")
    List<CuentaEntity> listaCuentasActivas(@Param("miIban") String string);

    @Query("select cuenta from CuentaEntity cuenta where cuenta.iban=:iban")
    CuentaEntity cuentaPorIban (@Param("iban") String string);
}
