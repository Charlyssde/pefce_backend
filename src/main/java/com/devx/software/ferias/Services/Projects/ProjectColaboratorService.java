package com.devx.software.ferias.Services.Projects;

import com.devx.software.ferias.Entities.Projects.ProjectColaboratorEntity;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import com.devx.software.ferias.Repositories.Projects.ProjectsColaboratorsRepository;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectColaboratorService {

    private final ProjectsService projectsService;
    private final ProjectsRepository projectsRepository;

    private final ProjectsColaboratorsRepository projectsColaboratorsRepository;

    private final UserService UserService;

    @Autowired
    public ProjectColaboratorService(
            ProjectsService projectsService,
            ProjectsRepository projectsRepository,
            ProjectsColaboratorsRepository projectsColaboratorsRepository,
            UserService UserService) {
        this.projectsService = projectsService;
        this.projectsRepository = projectsRepository;
        this.projectsColaboratorsRepository = projectsColaboratorsRepository;
        this.UserService = UserService;
    }


    //    CREATE
    public ProjectEntity save(ProjectColaboratorEntity entity, Long id, Long idUsuario) throws Exception {
        try {
            ProjectEntity proyecto = projectsService.findById(id);
            proyecto.agregarColaborador(entity);

            projectsRepository.save(proyecto);

            String[] roles = {"Responsable", "Contacto", "Mediador", "Proveedor", "Comprador", "Colaborador"};
            String accion = "Se ha registrado al colaborador \"" + entity.getUsuarioId().getNombre() + "\" con el rol \"" + roles[Integer.parseInt((entity.getRol())) - 1] + "\"";
//            this.crearHistorico(accion, idUsuario, proyecto, entity.getCreatedAt());

            return proyecto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //    READ
    public List<ProjectColaboratorEntity> findByIdProyectosId(Long id) throws Exception {
        ProjectEntity proyecto = projectsService.findById(id);
        List<ProjectColaboratorEntity> listColaboradores = proyecto.getColaboradores();
        return listColaboradores;
    }

    public Optional<ProjectColaboratorEntity> findById(Long id) {
        Optional<ProjectColaboratorEntity> entity = projectsColaboratorsRepository.findById(id);
        return entity;
    }

    //    UPDATE
    public ProjectEntity update(ProjectColaboratorEntity entity, Long id, Long idUsuario) throws Exception {
        try {
            ProjectEntity proyecto = projectsService.findById(id);
            projectsColaboratorsRepository.save(entity);

            String[] roles = {"Responsable", "Contacto", "Mediador", "Proveedor", "Comprador", "Colaborador"};
            String accion = "Se ha actualizado la información del colaborador \"" + entity.getUsuarioId().getNombre() + "\" con el rol \"" + roles[Integer.parseInt((entity.getRol())) - 1] + "\"";
//            this.crearHistorico(accion, idUsuario, proyecto, entity.getUpdatedAt());

            return proyecto;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //    DELETE
    public Boolean delete(Long id) throws Exception {
        try {
            projectsColaboratorsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    //    ADDED
//    private void crearHistorico(String accion, Long idUsuario, ProyectosEntity proyecto, Date fecha){
//        UserEntity usuario = UserService.findById(idUsuario);
//
//        ProyectosHistoricoEntity registroAccion = new ProyectosHistoricoEntity();
//
//        registroAccion.setUsuario(usuario);
//        registroAccion.setProyecto(proyecto);
//        registroAccion.setAccion(accion);
//        registroAccion.setCreatedAt(fecha);
//        registroAccion.setUpdatedAt(fecha);
//
//        proyectosHistoricoRepository.save(registroAccion);
//    }
}
