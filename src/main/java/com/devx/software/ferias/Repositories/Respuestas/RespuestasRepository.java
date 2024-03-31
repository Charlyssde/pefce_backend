package com.devx.software.ferias.Repositories.Respuestas;

import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Respuestas.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestasRepository extends JpaRepository<Respuesta, Long> {
    List<Respuesta> findAllByEncuestaAndEmpresa(Encuestas encuesta, EnterpriseEntity empresa);

    void deleteAllByEncuestaAndEmpresa(Encuestas encuesta, EnterpriseEntity empresa);

}
