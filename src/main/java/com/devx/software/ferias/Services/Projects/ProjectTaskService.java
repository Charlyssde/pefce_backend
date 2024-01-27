package com.devx.software.ferias.Services.Projects;

import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Repositories.Tasks.TasksRepository;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {

    private final TasksRepository tasksRepository;
    private final ProjectsRepository projectsRepository;
    private final UserService usuariosService;

    @Autowired
    public ProjectTaskService(
            TasksRepository tasksRepository,
            ProjectsRepository projectsRepository,
            UserService usuariosService) {
        this.tasksRepository = tasksRepository;
        this.projectsRepository = projectsRepository;
        this.usuariosService = usuariosService;
    }

    public TaskEntity save(TaskEntity entity, Long id, Long idUsuario) throws Exception {
        try {
            ProjectEntity proyecto = projectsRepository.findById(id).get();
            proyecto.agregarTarea(entity);
            projectsRepository.save(proyecto);

            // Crear histórico
            String accion = "Se ha creado la tarea \"" + entity.getTarea() + "\" para \"" + entity.getUsuarioId().getNombre() + "\"";
//            this.crearHistorico(accion,idUsuario,proyecto,entity.getCreatedAt());

            return entity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public TaskEntity update(TaskEntity entity, Long id, long idUsuario) throws Exception {
        try {
            ProjectEntity proyecto = projectsRepository.findById(id).get();
            TaskEntity tarea = tasksRepository.save(entity);

            String accion = "Se ha actualizado la tarea \"" + entity.getTarea() + "\" para \"" + entity.getUsuarioId().getNombre() + "\"";
//            this.crearHistorico(accion,idUsuario,proyecto,entity.getUpdatedAt());

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
    public List<TaskEntity> findByProyectosId(Long id) {
        ProjectEntity proyecto = projectsRepository.findById(id).get();
        List<TaskEntity> listProyectosTareas = proyecto.getProyectoTareas();
        return listProyectosTareas;
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
