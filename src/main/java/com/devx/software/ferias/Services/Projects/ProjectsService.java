package com.devx.software.ferias.Services.Projects;

import com.devx.software.ferias.DTOs.Projects.ProjectColaboratorDTO;
import com.devx.software.ferias.DTOs.Projects.ProjectRequest;
import com.devx.software.ferias.Entities.Projects.ProjectColaboratorEntity;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Mappers.Catalogs.CatalogsMapper;
import com.devx.software.ferias.Mappers.Projects.ProjectBasicFormRequestMapper;
import com.devx.software.ferias.Mappers.Projects.ProjectColaboratorMapper;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Repositories.Projects.ProjectsColaboratorsRepository;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Repositories.Sistema.SistemaRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProjectsService {

    private final UsersRepository usersRepository;
    private final ProjectsRepository projectsRepository;
    private final ProjectsColaboratorsRepository projectsColaboratorsRepository;
    private final CatalogsMapper catalogsMapper;
    private final ProjectBasicFormRequestMapper projectBasicFormRequestMapper;
    private final ProjectColaboratorMapper projectColaboratorMapper;
    private final SistemaRepository sistemaRepository;

    @Autowired
    public ProjectsService(
            UsersRepository usersRepository,
            ProjectsRepository projectsRepository,
            ProjectsColaboratorsRepository projectsColaboratorsRepository,
            CatalogsMapper catalogsMapper,
            ProjectBasicFormRequestMapper projectBasicFormRequestMapper,
            ProjectColaboratorMapper projectColaboratorMapper,
            SistemaRepository sistemaRepository) {
        this.usersRepository = usersRepository;
        this.projectsRepository = projectsRepository;
        this.projectsColaboratorsRepository = projectsColaboratorsRepository;
        this.catalogsMapper = catalogsMapper;
        this.projectBasicFormRequestMapper = projectBasicFormRequestMapper;
        this.projectColaboratorMapper = projectColaboratorMapper;
        this.sistemaRepository = sistemaRepository;
    }

    private String folioProyecto(Long id, Date createdAt) {
        String idProyecto = Long.toString(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String fecha = sdf.format(createdAt);
        return "P/" + fecha + "/" + idProyecto;
    }

    public ProjectEntity create( ProjectRequest projectRequest ) throws Exception {
        ProjectEntity project = this.projectBasicFormRequestMapper.dtoToEntity(projectRequest.getProject());
        System.out.println( projectRequest.getInstitutionResponsible().getId() );
        if(projectRequest.getInstitutionResponsible().getId() != null){
            project.agregarColaborador(this.projectColaboratorMapper.dtoToEntity(projectRequest.getInstitutionResponsible()));
        }else{
            List<SistemaEntity> sistemaEntity = this.sistemaRepository.findAll();
            UserEntity responsable = sistemaEntity.get(0).getResponableSubin();
            System.out.println( responsable.getId() );
            ProjectColaboratorEntity colaborador = new ProjectColaboratorEntity();
            colaborador.setRol("Responsable");
            colaborador.setUsuarioId( responsable );
            colaborador.setCreatedAt(new Date());
            colaborador.setUpdatedAt(new Date());
            colaborador.setActivo(true);
            System.out.println( colaborador.getUsuarioId().getNombre() );
            project.agregarColaborador( colaborador  );
            project.setArea( responsable.getPerfiles().get(0).getArea() );
        }
        if(projectRequest.getEnterpriseResponsible() != null){
            project.agregarColaborador(this.projectColaboratorMapper.dtoToEntity(projectRequest.getEnterpriseResponsible()));

            //Mailgun mailgun = new Mailgun();
            //mailgun.sendBasicEmail("SEDECOP - Se ha creado un proyecto de seguimiento", projectRequest.getEnterpriseResponsible().getUsuarioId().getEmail() , "Se le informa que se ha creado un proyecto de seguimiento del cual es parte su empresa, le invitamos a ingresar al portal Veracruz tu Estado Industrial!");

        }
        project = this.projectsRepository.save(project);
        project.setFolio(this.folioProyecto(project.getId(),project.getCreatedAt()));
        return this.projectsRepository.save(project);
    }

    public Page<ProjectEntity> pageProjects(
            Pageable pageable,
            // Global specific filters
            String filtro,
            String activo,
            String fechaInicio,
            String fechaFin,
            // User|Profile filters
            String perfil,
            String usuario,
            // Module specific filters
            String prioridad,
            String estatus,
            String conResponsable,
            // Enterprise specific filters
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector,
            String empresa,
            // Institution specific filters
            String area,
            Long empresaid
    ) throws Exception{
        if( empresaid == 1){

            return this.projectsRepository.findAllByEmpresaOrderByIdDesc(
                    pageable,
                    filtro,
                    activo,
                    fechaInicio,
                    fechaFin,
                    usuario,
                    perfil,
                    prioridad,
                    estatus,
                    conResponsable,
                    regimenFiscal,
                    categoria,
                    sector,
                    subsector,
                    empresa,
                    area,
                    empresaid.toString()
            );
        }else {
            if( empresaid == 0 ) {
                return this.projectsRepository.findAllByOrderByIdDesc(
                        pageable,
                        filtro,
                        activo,
                        fechaInicio,
                        fechaFin,
                        usuario,
                        perfil,
                        prioridad,
                        estatus,
                        conResponsable,
                        regimenFiscal,
                        categoria,
                        sector,
                        subsector,
                        empresa,
                        area
                );
            }else{
                return this.projectsRepository.findByMesasByOrderByIdDesc(
                        pageable,
                        filtro,
                        activo,
                        fechaInicio,
                        fechaFin,
                        usuario,
                        perfil,
                        prioridad,
                        estatus,
                        conResponsable,
                        regimenFiscal,
                        categoria,
                        sector,
                        subsector,
                        empresa,
                        area
                );
            }
        }
    }
    public List<ProjectEntity> getAll() {
        return this.projectsRepository.findAll();
    }


    public ProjectEntity findById(Long id) throws Exception {
        ProjectEntity project = this.projectsRepository.findProyectosById(id);
        if (project != null) {
            if (project.getFolio() == null) {
                project.setFolio(this.folioProyecto(project.getId(), project.getCreatedAt()));
                project = this.projectsRepository.save(project);
            }
            return project;
        }
        throw new Exception("El proyecto no existe");
    }


    public ProjectEntity update(ProjectRequest projectRequest,Long id) throws Exception {
        ProjectEntity project = this.projectsRepository.findProyectosById(id);
        if(project != null){
            ProjectEntity projectEntity = this.projectBasicFormRequestMapper.dtoToEntity(projectRequest.getProject());
            projectEntity.setColaboradores(project.getColaboradores());
            projectEntity.setHistorico(project.getHistorico());
            projectEntity.setProyectoTareas(project.getProyectoTareas());
            if(projectRequest.getInstitutionResponsible() != null){
                ProjectColaboratorEntity institutionResponsible = this.projectColaboratorMapper.dtoToEntity(projectRequest.getInstitutionResponsible());
                if(institutionResponsible.getId()!=null){
                    this.projectsColaboratorsRepository.save(institutionResponsible);
                }
                else{
                    projectEntity.agregarColaborador(institutionResponsible);
                }
            }
            if(projectRequest.getEnterpriseResponsible() != null){
                ProjectColaboratorEntity enterpriseResponsible = this.projectColaboratorMapper.dtoToEntity(projectRequest.getEnterpriseResponsible());
                if(enterpriseResponsible.getId()!=null){
                    this.projectsColaboratorsRepository.save(enterpriseResponsible);
                }
                else{
                    projectEntity.agregarColaborador(enterpriseResponsible);
                }
            }
            projectEntity.setTipoId(this.catalogsMapper.dtoToEntity(projectRequest.getProject().getTipoId()));
            return this.projectsRepository.save(projectEntity);
        }
        throw new Exception("El proyecto no existe");
    }

    public ProjectEntity updateByPanel(ProjectEntity projectEntity,Long id) throws Exception {
        System.out.println("B");
        ProjectEntity project = this.projectsRepository.findProyectosById(id);
        System.out.println("C");
        if(project != null){
            System.out.println("D");
            projectEntity = this.projectsRepository.save(projectEntity);
            System.out.println("·");
            return projectEntity;
        }
        throw new Exception("El proyecto no existe");
    }

    public void deleteById(Long id) {
        this.projectsRepository.deleteById(id);
    }


    // Scopes
    public List<ProjectEntity> getLastActiveProjects(Long number){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = this.usersRepository.userAuthentication(email);
        return this.projectsRepository.findAllByActivoTrueOrderByCreatedAtDesc(number,userEntity.getId());
    }


    public List<Object[]> obtenerTotalPorSexo() throws Exception{
        return this.projectsRepository.obtenerTotalPorSexo();
    }

    public List<Object[]> obtenerTotalPorMunicipio() throws Exception{
        return this.projectsRepository.obtenerTotalPorMunicipio();
    }

    public List<Object[]> obtenerTotalPorsector() throws Exception{
        return this.projectsRepository.obtenerTotalPorSector();
    }

    public List<ProjectEntity> findByTipoId(Long id){
        return projectsRepository.findProyectosByTipoId_Id(id);
    }


}


/*
package com.devx.software.ferias.services;

import com.devx.software.ferias.entities.ProyectosEntity;
import com.devx.software.ferias.repositories.ProyectosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProyectosService {

    private final ProyectosRepository pRepo;

    @Autowired
    public ProyectosService(
        ProyectosRepository pRepo
    ){
        this.pRepo = pRepo;
    }

    public List<ProyectosEntity> page(){
        return pRepo.findAll();
    }

    public ProyectosEntity create(ProyectosEntity model) throws Exception{
        try{
            return pRepo.save(model);
        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public ProyectosEntity findById (Long id) {
        return pRepo.findProyectosById(id);
    }

    public List<ProyectosEntity> findByEstatusId(Long id){
        return pRepo.findProyectosByEstatusId(id);
    }

    public ProyectosEntity update (ProyectosEntity entity) {
        return pRepo.save(entity);
    }

    public void deleteById(Long id) {
        pRepo.deleteById(id);
    }
}
 */