package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Logs.SessionDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileListDTO;
import com.devx.software.ferias.DTOs.Profiles.ProfileWithoutPermissionsDTO;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Logs.SessionEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Mappers.Logs.SessionMapper;
import com.devx.software.ferias.Mappers.Profile.ProfileListMapper;
import com.devx.software.ferias.Repositories.Profiles.ProfileRepository;
import com.devx.software.ferias.Services.Logs.SessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logs")
@PreAuthorize("isAuthenticated()")
public class LogsController {
    private final HttpHeaders headers = new HttpHeaders();

    private final SessionsService sessionsService;
    private final SessionMapper sessionMapper;

    private final ProfileRepository profileRepository;
    private final ProfileListMapper profileListMapper;

    @Autowired
    public LogsController(
            SessionsService sessionsService,
            SessionMapper sessionMapper,
            ProfileRepository profileRepository,
            ProfileListMapper profileListMapper
    ) {
        this.sessionsService = sessionsService;
        this.sessionMapper = sessionMapper;
        this.profileRepository = profileRepository;
        this.profileListMapper = profileListMapper;
    }

    @GetMapping("/sessions")
    public ResponseEntity<PaginationResponse> pageSessions(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            Pageable pageable = PageRequest.of(page, size);
            Page<SessionEntity> pageDataset = this.sessionsService.pageSession(pageable, userId, nombre);
            PaginationResponse response = new PaginationResponse();
            List<SessionDTO> sessions = new ArrayList<>();
            for (SessionEntity sessionEntity: pageDataset.getContent()) {
                ProfileListDTO profileListDTO = this.profileListMapper.entityToDto(this.profileRepository.findById(sessionEntity.getPerfil()).get());
                SessionDTO sessionDTO = this.sessionMapper.entityToDto(sessionEntity);
                sessionDTO.setProfile(profileListDTO);
                sessions.add(sessionDTO);
            }
            response.setDataset(sessions);
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());

            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
