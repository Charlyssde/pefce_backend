package com.devx.software.ferias.Services.Tasks;
import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Enums.TipoAgendaEnum;
import com.devx.software.ferias.Mappers.Tasks.FormResourceTaskMapper;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Repositories.Agenda.AgendaRepository;
import com.devx.software.ferias.Repositories.Tasks.TasksRepository;
import com.devx.software.ferias.Services.Agenda.AgendaService;
import com.devx.software.ferias.Services.Users.UserService;
import com.devx.software.ferias.Entities.Users.UserEntity;
//import com.devx.software.ferias.Repositories.Agenda.AgendaTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.devx.software.ferias.Repositories.Profiles.ProfileRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    private final UserService userService;
    private final AgendaService agendaService;

    private final ProfileRepository profileRepository;

    @Autowired
    public TasksService(
            TasksRepository tasksRepository,
            UserService userService,
            AgendaService agendaService,
            ProfileRepository profileRepository
    ) {
        this.tasksRepository = tasksRepository;
        this.userService = userService;
        this.agendaService = agendaService;
        this.profileRepository = profileRepository;
    }


    //    READ
    public List<TaskEntity> getAll() {
        return this.tasksRepository.findAll();
    }

    public List<TaskEntity> getAllByUsuario(Long usuarioId) {
        return this.tasksRepository.findAllByUsuarioId(usuarioId);
    }

    public List<TaskEntity> getAllByUsuarioId(Long usuarioId) {
        return this.tasksRepository.findAllByUsuarioIdId(usuarioId);
    }

    public Optional<TaskEntity> findById(Long id) {
        return this.tasksRepository.findById(id);
    }


    public TaskEntity save(TaskEntity taskNew) throws Exception{

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity usuario = this.userService.findByEmail( email );
        taskNew.setUsuarioId( usuario );

        TaskEntity task =  this.tasksRepository.save(taskNew);
        task.addUsuario(usuario);
        task = this.tasksRepository.save(task);
        this.agendaService.createAgendaTask(task);
        return task;
    }

    public TaskEntity update(TaskEntity entity) throws Exception {
        TaskEntity taskEntity = this.tasksRepository.findById(entity.getId()).get();
        if(taskEntity != null){

            entity.setUsuarios(taskEntity.getUsuarios());
            TaskEntity task = this.tasksRepository.save(entity);
            if( taskEntity.getTareasAgenda().isEmpty() ){
                this.agendaService.createAgendaTask(task);
            }else{
                this.agendaService.updateAgendaTask(task);
            }
            return this.tasksRepository.save(task);
        }
        throw new Exception("No existe la tarea");
    }

    public Boolean delete(Long id) {
        try {
            Optional<TaskEntity> model = this.findById(id);
            if (model.isPresent()) {
                this.tasksRepository.delete(model.get());
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Page<TaskEntity> pageTasks(
            Pageable pageable,
            UserEntity usuario_id,
            String tarea,
            String descripcion,
            String entregable,
            String fechaInicio,
            String fechaTermino,
            String estatus
    ) throws Exception{
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);
        usuario_id = this.userService.findByEmail( email );
        //if(usuario_id != null ){

        //}
        //if(activeProfileEntity != null){
            //if( tarea != null || descripcion != null || entregable != null || fechaInicio != null || fechaTermino != null || estatus != null){
                Page<TaskEntity> respuesta = this.tasksRepository.findAllByOrderByTaskAscAndMiscFilters(
                        pageable,
                        usuario_id,
                        tarea
                        /*
                        descripcion,
                        entregable,
                        fechaInicio,
                        fechaTermino,
                        estatus

                        */
                );
                return respuesta;
            //}
            //return this.tasksRepository.findAllByOrderByTareaDesc(pageable);
        //}
        //throw new Exception("El usuario no tiene una sesión iniciada");
    }

    public TaskEntity updateStatusTask(Long taskId, Boolean status)  throws Exception{
        TaskEntity task = this.tasksRepository.findById(taskId).get();
        if(task != null){
            task.setEstatus(status);
            return this.tasksRepository.save(task);
        }
        throw new Exception("Esta tarea no existe");
    }    
    
}
