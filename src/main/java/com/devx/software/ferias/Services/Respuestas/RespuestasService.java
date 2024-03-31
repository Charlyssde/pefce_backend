package com.devx.software.ferias.Services.Respuestas;

import com.devx.software.ferias.Entities.Encuestas.Encuestas;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Respuestas.Respuesta;
import com.devx.software.ferias.Repositories.Respuestas.RespuestasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RespuestasService {

    private final RespuestasRepository respuestasRepository;

    public RespuestasService(RespuestasRepository respuestasRepository) {
        this.respuestasRepository = respuestasRepository;
    }

    public Respuesta save(Respuesta respuesta) {
        return respuestasRepository.save(respuesta);
    }

    public List<Respuesta> saveAll(List<Respuesta> respuestas) {
        return respuestasRepository.saveAll(respuestas);
    }

    public List<Respuesta> findAllByEncuestaAndEmpresa(Encuestas encuestaId, EnterpriseEntity empresaId) {
        return respuestasRepository.findAllByEncuestaAndEmpresa(encuestaId, empresaId);
    }

    @Transactional
    public void deleteAllByEncuestaAndEmpresa(Encuestas encuestaId, EnterpriseEntity empresaId) {
        respuestasRepository.deleteAllByEncuestaAndEmpresa(encuestaId, empresaId);
    }
}
