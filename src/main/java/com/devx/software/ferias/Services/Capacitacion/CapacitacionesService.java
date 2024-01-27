package com.devx.software.ferias.Services.Capacitacion;

import com.devx.software.ferias.Entities.Capacitacion.CapacitacionesEntity;
import com.devx.software.ferias.Repositories.Capacitacion.CapacitacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapacitacionesService {

    private final CapacitacionesRepository capacitacionesRepository;

    @Autowired
    public CapacitacionesService(CapacitacionesRepository capacitacionesRepository) {
        this.capacitacionesRepository = capacitacionesRepository;
    }

    public CapacitacionesEntity create(CapacitacionesEntity entity) throws Exception {
        try {
            return capacitacionesRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<CapacitacionesEntity> page() {
        return capacitacionesRepository.findAll();
    }

    public List<CapacitacionesEntity> getLastTenCourses(){return this.capacitacionesRepository.getLastTenCourses();}

    public CapacitacionesEntity findById(Long id) {
        return capacitacionesRepository.findCapacitacionById(id);
    }

    public CapacitacionesEntity update(CapacitacionesEntity entity) {
        return capacitacionesRepository.save(entity);
    }

    public void deleteById(Long id) {
        capacitacionesRepository.deleteById(id);
    }

    public Long count() {
        return capacitacionesRepository.countAllByActivoTrue();
    }

    public List<CapacitacionesEntity> findCapacitacionesRegistradas(Long idUsuario) {
        return capacitacionesRepository.findCapacitacionesRegistradas(idUsuario);
    }

    public List<CapacitacionesEntity> findCapacitacionesNoRegistradas(Long idUsuario) {
        return capacitacionesRepository.findCapacitacionesNoRegistradas(idUsuario);
    }

    public Long countPreguntasPorCapacitacion(Long idCapacitacion) {
        return capacitacionesRepository.countPreguntasPorCapacitacion(idCapacitacion);
    }

    public Long countPreguntasRespondidasPorCapacitacion(Long idCapacitacion, Long idUsuario) {
        return capacitacionesRepository.countPreguntasRespondidasPorCapacitacion(idCapacitacion, idUsuario);
    }

    public List<CapacitacionesRepository.CapacitacionesHoy> getByFechaFinTimeAfterToday() {
        return capacitacionesRepository.getByFechaFinTimeAfterToday();
    }

    public Long checkIfCapacitacionRegistrada(Long idCapacitacion, Long idUsuario) {
        return capacitacionesRepository.checkIfCapacitacionRegistrada(idCapacitacion, idUsuario);
    }


    public List<Object[]> obtenerTotalPorSexo() throws Exception{
        return this.capacitacionesRepository.obtenerTotalPorSexo();
    }

    public List<Object[]> obtenerTotalPorMunicipio() throws Exception{
        return this.capacitacionesRepository.obtenerTotalPorMunicipio();
    }

    public List<Object[]> obtenerTotalPorsector() throws Exception{
        return this.capacitacionesRepository.obtenerTotalPorSector();
    }
}
