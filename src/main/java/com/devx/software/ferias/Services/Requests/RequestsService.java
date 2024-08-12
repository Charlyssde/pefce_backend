package com.devx.software.ferias.Services.Requests;

import com.devx.software.ferias.Entities.Minutas.MinutaTemaEntity;
import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Entities.Requests.RequestEntity;
import com.devx.software.ferias.Mappers.Catalogs.CatalogsMapper;
import com.devx.software.ferias.Mappers.Proyectos.ProyectosMapper;
import com.devx.software.ferias.Mappers.Users.UserMapper;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.Services.Minutas.MinutaTemaService;
import com.devx.software.ferias.Services.Minutas.MinutasService;
import com.devx.software.ferias.Services.Projects.ProjectsService;
import com.devx.software.ferias.Repositories.Requests.RequestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestsService {

    private final RequestsRepository requestsRepository;
    private final MinutasService minutasService;
    private final ProjectsService projectsService;
    private final ProyectosMapper proyectosMapper;
    private final CatalogsService catalogsService;
    private final CatalogsMapper catalogosMapper;
    private final MinutaTemaService minutaTemaService;
    private final UserMapper usuariosMapper;

    @Autowired
    public RequestsService(
            RequestsRepository requestsRepository,
            MinutasService minutasService,
            ProjectsService projectsService,
            ProyectosMapper proyectosMapper,
            CatalogsService catalogsService,
            CatalogsMapper catalogosMapper, MinutaTemaService minutaTemaService,
            UserMapper usuariosMapper) {
        this.requestsRepository = requestsRepository;
        this.minutasService = minutasService;
        this.projectsService = projectsService;
        this.proyectosMapper = proyectosMapper;
        this.catalogsService = catalogsService;
        this.catalogosMapper = catalogosMapper;
        this.minutaTemaService = minutaTemaService;
        this.usuariosMapper = usuariosMapper;
    }

    public Page<RequestEntity> pageRequests(
            Pageable pageable,
            Long usuarioId,
            String perfil,
            Long perfilId
    ) throws Exception{
        Page<RequestEntity> respuesta;
        if( perfil.equals("Empresa") ){
            respuesta = this.requestsRepository.buscarSolicitudesPorSolicitante(
                    pageable,
                    usuarioId
            );
        }else{
            respuesta = this.requestsRepository.buscarSolicitudesPorPerfil(
                    pageable,
                    perfilId
            );
        }
        return respuesta;
    }

    public List<Object[]> obtenerTotalPorSexo() throws Exception{
        return this.requestsRepository.obtenerTotalPorSexo();
    }

    public List<Object[]> obtenerTotalPorMunicipio() throws Exception{
        return this.requestsRepository.obtenerTotalPorMunicipio();
    }

    public List<Object[]> obtenerTotalPorsector() throws Exception{
        return this.requestsRepository.obtenerTotalPorSector();
    }

}
