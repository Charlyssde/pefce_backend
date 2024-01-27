package com.devx.software.ferias.Repositories.Agenda;


import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
    //List<AgendaEntity> findAllByUsuarioId(Long id_usuario);
    List<AgendaEntity> findAllByUsuariosAgenda(Long id_usuario);
    List<AgendaEntity> findAllByInicioGreaterThanEqualAndFinIsLessThan(Date startDate, Date endDate);
    List<AgendaEntity> findAllByInicioGreaterThanEqualAndFinIsLessThanAndAndUsuariosAgenda(Date startDate, Date endDate, UserEntity userEntity);
    AgendaEntity findAgendaById(Long id);

}
