package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.DTOs.Meetings.MeetingAccountSelectDTO;
import com.devx.software.ferias.DTOs.Meetings.MeetingsAccountCredentialsRequest;
import com.devx.software.ferias.DTOs.Meetings.MeetingsAccountCredentialsResponse;
import com.devx.software.ferias.Entities.Meetings.*;
import com.devx.software.ferias.Mappers.Meetings.MeetingAccountSelectMapper;
import com.devx.software.ferias.Misc.Meetings;
import com.devx.software.ferias.Repositories.Meetings.*;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/meetings")
@PreAuthorize("isAuthenticated()")
public class MeetingsController {
    private final MeetingCuentasRepository meetingCuentasRepository;
    private final MeetingsRepository meetingsRepository;
    private final MeetingZoomRepository meetingZoomRepository;
    private final MeetingZoomRecurrenceRepository meetingZoomRecurrenceRepository;
    private final MeetingZoomSettingsRepository meetingZoomSettingsRepository;
    private final CatalogsService catalogsService;
    private final MeetingAccountSelectMapper meetingAccountSelectMapper;

    @Autowired
    public MeetingsController(
            MeetingCuentasRepository meetingCuentasRepository,
            MeetingsRepository meetingsRepository,
            MeetingZoomRepository meetingZoomRepository,
            MeetingZoomRecurrenceRepository meetingZoomRecurrenceRepository,
            MeetingZoomSettingsRepository meetingZoomSettingsRepository,
            CatalogsService catalogsService,
            MeetingAccountSelectMapper meetingAccountSelectMapper
    ) {
        this.meetingCuentasRepository = meetingCuentasRepository;
        this.meetingsRepository = meetingsRepository;
        this.meetingZoomRepository = meetingZoomRepository;
        this.meetingZoomRecurrenceRepository = meetingZoomRecurrenceRepository;
        this.meetingZoomSettingsRepository = meetingZoomSettingsRepository;
        this.catalogsService = catalogsService;
        this.meetingAccountSelectMapper = meetingAccountSelectMapper;
    }

    @GetMapping("/cuentas")
    public ResponseEntity<List<MeetingAccountEntity>> getCuentas() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<MeetingAccountEntity> meetingCuentas = meetingCuentasRepository.findAll();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(meetingCuentas, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cuentas/{id}")
    public ResponseEntity<MeetingAccountEntity> findCuenta(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            MeetingAccountEntity response = this.meetingCuentasRepository.findById(id).get();
            if (response != null) {
                headers.set("200", "Actualización exitosa");
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
            throw new Exception("La cuenta no se encuentra registrada");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cuentas/formRequiredData/{idCuentaMeeting}")
    public ResponseEntity<HashMap<String, Object>> formRequiredData(@PathVariable Long idCuentaMeeting) {
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();

            List<CatalogsDTO> areas = this.catalogsService.getAllCatalogosByTipoCatalogo("AREAS");
            response.put("areas", areas);

            if (idCuentaMeeting == 0) {
                response.put("cuentaMeeting", null);
            } else {
                MeetingAccountEntity cuentaMetting = this.meetingCuentasRepository.findById(idCuentaMeeting).get();
                response.put("cuentaMeeting", cuentaMetting);
            }

            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cuentas")
    public ResponseEntity<MeetingAccountEntity> createCuenta(@RequestBody MeetingAccountEntity meetingCuenta) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("200", "Registro exitoso");
            MeetingAccountEntity response = this.meetingCuentasRepository.save(meetingCuenta);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cuentas/{id}")
    public ResponseEntity<MeetingAccountEntity> updateCuenta(@PathVariable Long id, @RequestBody MeetingAccountEntity meetingCuenta) {
        try {
            HttpHeaders headers = new HttpHeaders();
            MeetingAccountEntity cuenta = this.meetingCuentasRepository.findById(id).get();
            if (cuenta != null) {
                headers.set("200", "Actualización exitosa");
                MeetingAccountEntity response = this.meetingCuentasRepository.save(meetingCuenta);
                return new ResponseEntity<>(response, headers, HttpStatus.OK);
            }
            throw new Exception("La cuenta no se encuentra registrada");
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/cuentas/{idCuentaMeeting}/meetingFormRequiredData/{idMeeting}")
    public ResponseEntity<HashMap<String, Object>> meetingFormRequiredData(@PathVariable Long idCuentaMeeting, @PathVariable Long idMeeting) {
        HttpHeaders headers = new HttpHeaders();
        try {
            HashMap<String, Object> response = new HashMap<>();
            MeetingAccountEntity cuentaMeeting = this.meetingCuentasRepository.findById(idCuentaMeeting).get();
            response.put("cuentaMeeting", cuentaMeeting);

            if (idMeeting == 0) {
                response.put("meeting", null);
            } else {
                MeetingEntity meeting = this.meetingsRepository.findById(idMeeting).get();
                response.put("meeting", meeting);
            }

            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cuentas/{idCuentaMeeting}/zoom")
    public ResponseEntity<HashMap<String, Object>> getMeetingsList(@PathVariable Long idCuentaMeeting) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HashMap response = new HashMap();
            MeetingAccountEntity cuenta = this.meetingCuentasRepository.findById(idCuentaMeeting).get();
            response.put("cuenta", cuenta);
            List<MeetingEntity> meetings = this.meetingsRepository.findAllByMeetingCuentaId_Id(idCuentaMeeting);
            response.put("meetings", meetings);

            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cuentas/{idCuentaMeeting}/zoom")
    public ResponseEntity<MeetingEntity> createMeeting(
            @PathVariable Long idCuentaMeeting,
            @RequestBody MeetingEntity meetingEntity) {
        try {
            //  Start response variables
            HttpHeaders headers = new HttpHeaders();
            // MeetingEntity meeting = meetingEntity;
            //  Save data in new variables
//            MeetingZoomEntity meetingZoom = meetingEntity.getMeetingZoom();
//            meeting.setMeetingZoom(null);
//            MeetingZoomRecurrenceEntity meetingZoomRecurrence = meetingZoom.getRecurrence();
//            meetingZoom.setRecurrence(null);
//            MeetingZoomSettingsEntity meetingZoomSettings = meetingZoom.getSettings();
//            meetingZoom.setSettings(null);
//            //  Save meeting entity
//            meeting = this.meetingsRepository.save(meeting);
//            //  Prepare/save meeting zoom entity
//            meetingZoom.setMeetingId(meeting);
//            meetingZoom = this.meetingZoomRepository.save(meetingZoom);
//            // Prepare/save meeting zoom recurrence entity
//            if (meetingZoom.getRecurrence() != null) {
//                meetingZoomRecurrence.setMeetingZoomId(meetingZoom);
//                meetingZoomRecurrence = this.meetingZoomRecurrenceRepository.save(meetingZoomRecurrence);
//            }
//            // Prepare/save meeting zoom settings entity
//            if (meetingZoom.getSettings() != null) {
//                meetingZoomSettings.setMeetingZoomId(meetingZoom);
//                meetingZoomSettings = this.meetingZoomSettingsRepository.save(meetingZoomSettings);
//            }
//            //  Update meeting zoom entity
//            meetingZoom.setRecurrence(meetingZoomRecurrence);
//            meetingZoom.setSettings(meetingZoomSettings);
//            meetingZoom = meetingZoomRepository.save(meetingZoom);
            meetingEntity = this.meetingsRepository.save(meetingEntity);
            //  Create zoom meetint using API
            Meetings meetingsAPI = new Meetings();


            String apiResponse = meetingsAPI.createMeeting( meetingEntity.getMeetingZoom(), meetingEntity.getMeetingCuentaId());
            // Update meeting entity
            meetingEntity.setApiResponse(apiResponse);
            meetingEntity = this.meetingsRepository.save(meetingEntity);
            System.out.println( meetingEntity.getMeetingZoom().getMeetingId().getApiResponse() );
            // Set response variables and return response
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(meetingEntity, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cuentas/{idCuentaMeeting}/zoom/{idMeeting}")
    public ResponseEntity<MeetingEntity> updateMeeting(
            @PathVariable Long idCuentaMeeting,
            @PathVariable Long idMeeting,
            @RequestBody MeetingEntity meetingEntity) {
        try {
            //  Start response variables
            HttpHeaders headers = new HttpHeaders();
            MeetingEntity meeting = meetingEntity;
            //  Save data in new variables
            MeetingZoomEntity meetingZoom = meetingEntity.getMeetingZoom();
            meeting.setMeetingZoom(null);
            MeetingZoomRecurrenceEntity meetingZoomRecurrence = meetingZoom.getRecurrence();
            meetingZoom.setRecurrence(null);
            MeetingZoomSettingsEntity meetingZoomSettings = meetingZoom.getSettings();
            meetingZoom.setSettings(null);

            //  Save meeting entity
            meeting = this.meetingsRepository.save(meeting);
            //  Prepare/save meeting zoom entity
//            meetingZoom.setMeetingId(meeting);
//            meetingZoom = this.meetingZoomRepository.save(meetingZoom);

//            // Prepare/save meeting zoom recurrence entity
            if (meetingZoom.getRecurrence() != null) {
                this.meetingZoomRecurrenceRepository.save(meetingZoomRecurrence);
            }
//            // Prepare/save meeting zoom settings entity
            if (meetingZoom.getSettings() != null) {
                this.meetingZoomSettingsRepository.save(meetingZoomSettings);
            }
            //  Update meeting zoom entity
//            meetingZoom.setRecurrence(meetingZoomRecurrence);
//            meetingZoom.setSettings(meetingZoomSettings);
//            meetingZoom = meetingZoomRepository.save(meetingZoom);
            Meetings meetingsAPI = new Meetings();
            String apiResponse = meetingsAPI.updateMeeting(meetingZoom, meeting.getMeetingCuentaId());

//              Create zoom meeting using API
            // Update meeting entity
//            meeting.setMeetingZoom(meetingZoom);
            meeting.setApiResponse(apiResponse);
            meeting = this.meetingsRepository.save(meeting);
            // Set response variables and return response
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(meeting, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/cuentas/verificarCredenciales")
    public ResponseEntity<MeetingsAccountCredentialsResponse> verificarCredenciales(@RequestBody MeetingsAccountCredentialsRequest meetingsAccountCredentialsRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("200", "Consulta exitosa");
            Meetings meetings = new Meetings();
            MeetingsAccountCredentialsResponse response = meetings.getMeetingAuthTokenResponse(meetingsAccountCredentialsRequest);
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Scopes
    @GetMapping("/getAccountsBySelect")
    public ResponseEntity<List<MeetingEntity>> getSelectMeetingsAccounts(){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity(this.meetingCuentasRepository.findAll(), headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
