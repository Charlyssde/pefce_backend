package com.devx.software.ferias.Services.Logs;
//
//import com.devx.software.ferias.DTOs.Logs.SessionDTO;
//import com.devx.software.ferias.Entities.Logs.ErrorEntity;
//import com.devx.software.ferias.Entities.Logs.OperacionEntity;
//import com.devx.software.ferias.Entities.Logs.SessionEntity;
//import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Mappers.Logs.SessionMapper;
import com.devx.software.ferias.Repositories.Logs.ErrorRepository;
//import com.devx.software.ferias.Repositories.Logs.OperacionRepository;
import com.devx.software.ferias.Repositories.Logs.SesionRepository;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LogsService {

    private final ErrorRepository logsErrorRepository;

    private final SesionRepository logsSesionRepository;

    //private final OperacionRepository logsOperacionRepository;

    private final UserService usuariosService;

    private final SessionMapper logsSesionMapper;

    @Autowired
    public LogsService(ErrorRepository logsErrorRepository,
                       SesionRepository logsSesionRepository,
                       //OperacionRepository logsOperacionRepository,
                       UserService usuariosService,
                       SessionMapper logsSesionMapper) {
        this.logsErrorRepository = logsErrorRepository;
        this.logsSesionRepository = logsSesionRepository;
        //this.logsOperacionRepository = logsOperacionRepository;
        this.usuariosService = usuariosService;
        this.logsSesionMapper = logsSesionMapper;
    }
//
//    public ErrorEntity saveLogError(ErrorEntity ErrorEntity) {
//        return ErrorRepository.save(ErrorEntity);
//    }
//
//    public List<LogsErrorEntity> getAllLogError() {
//        return logsErrorRepository.findAll();
//    }
//
//    public LogsSesionEntity saveLogSesion(LogsSesionEntity logsSesionEntity) {
//        return logsSesionRepository.save(logsSesionEntity);
//    }
//
//    public LogsSesionEntity closeSesion(Long id) {
//        LogsSesionEntity logsSesionEntity = logsSesionRepository.findByIdAndActivoTrue(id);
//        if (logsSesionEntity != null) {
//            logsSesionEntity.setActivo(false);
//            logsSesionEntity.setFechaFin(new Date());
//            return logsSesionRepository.save(logsSesionEntity);
//        }
//        return null;
//    }
//
//    public LogsSesionEntity findSesionById(Long id) {
//        return logsSesionRepository.findByIdAndActivoTrue(id);
//    }
//
//    public Boolean closeAllSesionsByIdUsuario(Long idUsuario) {
//        Boolean sesionesCerradas = true;
//        try {
//            LogsSesionEntity sesionActiva = logsSesionRepository
//                    .findLogsSesionEntityByIdUsuarioAndActivoTrue(idUsuario);
//            if (sesionActiva != null) {
//                sesionActiva.setActivo(false);
//                sesionActiva.setFechaFin(new Date());
//                logsSesionRepository.save(sesionActiva);
//            }
//        } catch (Exception e) {
//            sesionesCerradas = false;
//        }
//        return sesionesCerradas;
//    }
//
//    public Boolean findSesionActivaByIdUsuario(Long id) {
//        LogsSesionEntity logsSesionEntity = logsSesionRepository.findLogsSesionByCorreoNative(id);
//        return logsSesionEntity != null;
//    }
//
//    public List<LogsSesionDTO> getAllSessionActive() {
//        List<LogsSesionDTO> logsSesionPageDTOList = new ArrayList<>();
//        List<LogsSesionEntity> logsSesionEntityList = logsSesionRepository.getAllByActivoTrue();
//        if (!logsSesionEntityList.isEmpty()) {
//            logsSesionEntityList.forEach(sesion -> {
//                UsuariosEntity usuariosEntity = usuariosService.findById(sesion.getIdUsuario());
//                LogsSesionDTO logsSesionDTO = logsSesionMapper.entityToDTO(sesion);
//                logsSesionDTO.setUsuario(usuariosEntity.getCorreo());
//                logsSesionPageDTOList.add(logsSesionDTO);
//            });
//        }
//        return logsSesionPageDTOList;
//    }
//
//    public LogsOperacionEntity saveLogOperacion(LogsOperacionEntity logsOperacionEntity) {
//        return logsOperacionRepository.save(logsOperacionEntity);
//    }
//
//    public List<LogsOperacionEntity> getAllLogOperacion() {
//        return this.logsOperacionRepository.findAll();
//    }
}
