package com.devx.software.ferias.Services.Projects;

import com.devx.software.ferias.Repositories.Projects.ProjectsHistoryRepository;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectHistoryService {

    private final ProjectsService projectsService;
    private final ProjectsRepository projectsRepository;
    private final ProjectsHistoryRepository proyectoColaboradorRepository;
    private final UserService usuariosService;

    @Autowired
    public ProjectHistoryService(
            ProjectsService projectsService,
            ProjectsRepository projectsRepository,
            ProjectsHistoryRepository proyectoColaboradorRepository,
            UserService usuariosService
    ) {
        this.projectsService = projectsService;
        this.projectsRepository = projectsRepository;
        this.proyectoColaboradorRepository = proyectoColaboradorRepository;
        this.usuariosService = usuariosService;
    }


}
