package com.devx.software.ferias.Services.Minutas;

import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Repositories.Agenda.AgendaRepository;
import com.devx.software.ferias.Repositories.Tasks.TasksRepository;
import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Agenda.AgendaTareaEntity;
import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Repositories.Agenda.AgendaTareaRepository;
import com.devx.software.ferias.Repositories.Minutas.MinutasRepository;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinutasTareasService {

    private final TasksRepository tasksRepository;
    private final MinutasRepository minutaRepository;
    private final UserService usuariosService;
    private final AgendaRepository agendaRepositories;
    private final AgendaTareaRepository agendaTareaRepository;

    @Autowired
    public MinutasTareasService(
            TasksRepository tasksRepository,
            MinutasRepository minutaRepository,
            UserService usuariosService,
            AgendaRepository agendaRepositories,
            AgendaTareaRepository agendaTareaRepository) {
        this.tasksRepository = tasksRepository;
        this.minutaRepository = minutaRepository;
        this.usuariosService = usuariosService;
        this.agendaRepositories = agendaRepositories;
        this.agendaTareaRepository = agendaTareaRepository;
    }

    public TaskEntity save(TaskEntity entity, Long id, Long idUsuario) throws Exception {
        try {
            MinutasEntity minuta = minutaRepository.findMinutaById(id);
            //minuta.agregarTarea(entity);
            minutaRepository.save(minuta);

            AgendaEntity ae = new AgendaEntity();
            ae.setTitulo(entity.getTarea());
            ae.setDescripcion(entity.getTarea());
            ae.setInicio(entity.getFechaInicio());
            ae.setFin(entity.getFechaTermino());
            ae.setDiaCompleto(true);
            this.agendaRepositories.save(ae);

            entity = tasksRepository.findLastInsertedTareaOnMinutas(id);

            AgendaTareaEntity ate = new AgendaTareaEntity();
            ate.setAgenda(ae);
            ate.setTarea(entity);
            this.agendaTareaRepository.save(ate);

            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TaskEntity update(TaskEntity entity, Long id, long idUsuario) throws Exception {
        try {
            MinutasEntity minuta = minutaRepository.findMinutaById(id);
            TaskEntity tarea = tasksRepository.save(entity);

            AgendaTareaEntity ate = agendaTareaRepository.findByTareaId(tarea.getId());

            AgendaEntity ae = ate.getAgenda();
            ae.setTitulo(entity.getTarea());
            ae.setDescripcion(entity.getTarea());
            ae.setInicio(entity.getFechaInicio());
            ae.setFin(entity.getFechaTermino());
            ae.setDiaCompleto(true);
            //ae.setUsuario(entity.getUsuarioId());
            this.agendaRepositories.save(ae);

            //String accion = "Se ha actualizado la tarea \""+entity.getTarea()+"\" para \""+entity.getUsuarioId().getNombreCompleto()+"\"";
            //this.crearHistorico(accion,idUsuario,proyecto,entity.getUpdatedAt());

            return tarea;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


//    public ProyectosTareasDTO update(ProyectosTareasDTO proyectosTareasDTO) throws Exception {
//        try{
//            ProyectosTareasEntity proyectosTareas = proyectosTareasMapper.dtoToEntity(proyectosTareasDTO);
//            ProyectosTareasDTO resultSet = proyectosTareasMapper.entityToDTO(proyectosTareasRespository.save(proyectosTareas));
//            return resultSet;
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }
//    public ProyectosTareasDTO show(Long id)  throws Exception{
//        try{
//            Optional<ProyectosTareasEntity> proyectosTareas  = proyectosTareasRespository.findById(id);
//            if(!proyectosTareas.isPresent()) {
//                throw new Exception("No se ha encontro el elemnto");
//            }else {
//                ProyectosTareasDTO resultSet = proyectosTareasMapper.entityToDTO(proyectosTareas.get());
//                return resultSet;
//            }
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }

    //    public String  delete(Long id)  throws Exception{
//        try{
//            proyectosTareasRespository.deleteById(id);
//            return "Se ha eliminado el archivo correctamente";
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }
//    
    public List<TaskEntity> findByMinutaId(Long id) {
        MinutasEntity minuta = minutaRepository.findMinutaById(id);
        List<TaskEntity> listMinutasTareas = minuta.getMinutaTareas();
        return listMinutasTareas;
    }
    
    
    /*private void crearHistorico(String accion, Long idUsuario, ProyectosEntity proyecto, Date fecha){
        UserEntity usuario = usuariosService.findById(idUsuario);
        
        ProyectosHistoricoEntity registroAccion = new ProyectosHistoricoEntity();
        
        registroAccion.setUsuario(usuario);
        registroAccion.setProyecto(proyecto);
        registroAccion.setAccion(accion);
        registroAccion.setCreatedAt(fecha);
        registroAccion.setUpdatedAt(fecha);

        proyectosHistoricoRepository.save(registroAccion);
    }*/
}
