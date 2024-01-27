package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Projects.ProjectEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Repositories.Events.EventsRepository;
import com.devx.software.ferias.Repositories.Projects.ProjectsRepository;
import com.devx.software.ferias.Repositories.Tasks.TasksRepository;
import com.devx.software.ferias.Services.Events.EventsService;
import com.devx.software.ferias.Entities.Agenda.AgendaEntity;
import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Repositories.Agenda.AgendaRepository;
import com.devx.software.ferias.Repositories.Notifications.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@PreAuthorize("isAuthenticated()")
public class DashboardController {
    private final AgendaRepository agendaRepositories;
    private final ProjectsRepository projectsRepository;
    private final NotificationsRepository notificacionesRepository;
    private final TasksRepository tasksRepository;

    private final EventsRepository eventsRepository;

    private final EventsService eventsService;

    @Autowired
    public DashboardController(
            AgendaRepository agendaRepositories,
            ProjectsRepository projectsRepository,
            NotificationsRepository notificacionesRepository,
            TasksRepository tasksRepository,
            EventsRepository eventsRepository,
            EventsService eventsService
    ) {
        this.agendaRepositories = agendaRepositories;
        this.projectsRepository = projectsRepository;
        this.notificacionesRepository = notificacionesRepository;
        this.tasksRepository = tasksRepository;
        this.eventsRepository = eventsRepository;
        this.eventsService = eventsService;
    }

    @GetMapping("/home/{usuarioId}/{perfil}")
    public ResponseEntity<HashMap<String, Object>> home(@PathVariable Long usuarioId, @PathVariable String perfil) {
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();

            //List<AgendaEntity> agenda = agendaRepositories.findAllByUsuarioId(usuarioId);
            List<AgendaEntity> agenda = agendaRepositories.findAllByUsuariosAgenda(usuarioId);
            response.put("agenda", agenda);

            List<NotificationsEntity> notificaciones = new ArrayList<>();
            notificaciones = (perfil == "Superadministrador" || perfil == "Administrador") ? notificacionesRepository.findAll() : notificacionesRepository.findAllNotificationsByDestinatario(usuarioId);
            response.put("notificaciones", notificaciones);

            List<TaskEntity> tareas = new ArrayList<>();
            tareas = (perfil == "Superadministrador" || perfil == "Administrador") ? tasksRepository.findAllPendingTasks() : tasksRepository.findPendingTasksByUser(usuarioId);
            response.put("tareas", tareas);

            List<EventEntity> eventos = this.eventsRepository.findAll();
            response.put("eventos", eventos);

            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
