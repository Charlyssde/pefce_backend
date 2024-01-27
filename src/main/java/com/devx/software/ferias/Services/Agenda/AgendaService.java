package com.devx.software.ferias.Services.Agenda;

import com.devx.software.ferias.DTOs.Agenda.AgendaDTO;
import com.devx.software.ferias.DTOs.Agenda.AgendaListRequest;
import com.devx.software.ferias.DTOs.Agenda.AgendaRequest;
import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Enums.TipoAgendaEnum;
import com.devx.software.ferias.Mappers.Agenda.AgendaMapper;
import com.devx.software.ferias.Repositories.Agenda.AgendaRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final UsersRepository usersRepository;
    private final AgendaMapper agendaMapper;

    @Autowired
    public AgendaService(
            AgendaRepository agendaRepository,
            UsersRepository usersRepository,
            AgendaMapper agendaMapper
    ) {
        this.agendaRepository = agendaRepository;
        this.usersRepository = usersRepository;
        this.agendaMapper = agendaMapper;
    }

    public List<AgendaDTO> pageAgenda(AgendaListRequest agendaListRequest) throws Exception {
        List<AgendaEntity> pageAgendaEntity = new ArrayList<>();
        if ((agendaListRequest.getOwner()).equals(false)) {
            pageAgendaEntity = this.agendaRepository.findAllByInicioGreaterThanEqualAndFinIsLessThan(agendaListRequest.getStartDate(), agendaListRequest.getEndDate());
        }

        if ((agendaListRequest.getOwner()).equals(true)) {
            UserEntity user = this.usersRepository.findUserById(agendaListRequest.getOwnerId());
            pageAgendaEntity = this.agendaRepository.findAllByInicioGreaterThanEqualAndFinIsLessThanAndAndUsuariosAgenda(agendaListRequest.getStartDate(), agendaListRequest.getEndDate(), user);
        }
        return this.agendaMapper.listEntityToDTO(pageAgendaEntity);
    }

    public AgendaEntity create(AgendaRequest agendaRequest) throws Exception {
        AgendaEntity agenda = this.agendaMapper.dtoToEntity(agendaRequest.getAgenda());
        UserEntity user = this.usersRepository.findUserById(agendaRequest.getUserId());
        if (!user.equals(null)) {
            agenda = this.agendaRepository.save(agenda);
            agenda.addUsuario(user);
            agenda = this.agendaRepository.save(agenda);
            return agenda;
        }
        throw new Exception("No se ha asignado un usuario para este evento de agenda");
    }

    public AgendaEntity update(Long agendaId, AgendaRequest agendaRequest) throws Exception {
        AgendaEntity agendaEntity = this.agendaRepository.findById(agendaId).get();
        if (agendaEntity != null) {
            AgendaEntity agenda = this.agendaMapper.dtoToEntity(agendaRequest.getAgenda());
            return this.agendaRepository.save(agenda);
        }
        throw new Exception("El evento de agenda no existe");
    }

    public HashMap<String, Object> delete(Long agendaId) throws Exception {
        AgendaEntity agendaEntity = this.agendaRepository.findById(agendaId).get();
        if (agendaEntity != null) {
            this.agendaRepository.deleteById(agendaId);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Evento de agenda eliminado");
            return response;
        }
        throw new Exception("El evento de agenda no existe");
    }

    public void createAgendaTask(TaskEntity taskEntity) throws Exception{
        Date startDate = taskEntity.getFechaTermino();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        AgendaEntity agendaEntity = new AgendaEntity(
                null,
                "Tarea "+taskEntity.getId().toString(),
                taskEntity.getDescripcion(),
                startDate,
                taskEntity.getFechaTermino(),
                true,
                TipoAgendaEnum.TAREA.getTipo(),
                true,
                new Date(),
                null,
                new ArrayList<UserEntity>(),
                new ArrayList<TaskEntity>()
        );
        agendaEntity.addUsuario(taskEntity.getUsuarioId());
        agendaEntity.addTarea(taskEntity);
        this.agendaRepository.save(agendaEntity);
    }

    public void updateAgendaTask(TaskEntity taskEntity) throws Exception{
        AgendaEntity agendaEntity = taskEntity.getTareasAgenda().get(0);
        Date startDate = taskEntity.getFechaTermino();
        startDate.setHours(0);
        startDate.setMinutes(0);
        startDate.setSeconds(0);
        agendaEntity.setInicio(startDate);
        agendaEntity.setFin(taskEntity.getFechaTermino());
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(taskEntity.getUsuarioId());
        agendaEntity.setUsuariosAgenda(userEntities);
        agendaEntity.setDescripcion(taskEntity.getTarea());
        this.agendaRepository.save(agendaEntity);
    }
}
