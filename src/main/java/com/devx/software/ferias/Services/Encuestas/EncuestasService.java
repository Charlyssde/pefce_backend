package com.devx.software.ferias.Services.Encuestas;

import com.devx.software.ferias.DTOs.Encuestas.EncuestaRequestDTO;
import com.devx.software.ferias.DTOs.Files.FileDTO;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import com.devx.software.ferias.Mappers.Encuestas.EncuestaMapper;
import com.devx.software.ferias.Mappers.Files.FilesMapper;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Mappers.Encuestas.FormResourceEncuestaMapper;
import com.devx.software.ferias.Misc.MinIO;
import com.devx.software.ferias.Repositories.Encuestas.EncuestasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EncuestasService {

    private final EncuestasRepository encuestasRepository;
    private final FormResourceEncuestaMapper formResourceEncuestaMapper;
    private final EncuestaMapper encuestaMapper;
    private final FilesMapper filesMapper;

    @Autowired
    public EncuestasService(
            EncuestasRepository encuestasRepository,
            FormResourceEncuestaMapper formResourceEncuestaMapper, EncuestaMapper encuestaMapper, FilesMapper filesMapper) {
        this.encuestasRepository = encuestasRepository;
        this.formResourceEncuestaMapper = formResourceEncuestaMapper;
        this.encuestaMapper = encuestaMapper;
        this.filesMapper = filesMapper;
    }


    //    READ
    public List<EncuestaEntity> getAll() {
        return this.encuestasRepository.findAll();
    }



    public EncuestaEntity saveEncuesta(
            EncuestaEntity encuestaRequestDTO,
            FileEntity archivo,
            MultipartFile archivoDTO
    ) throws Exception {
        EncuestaEntity encuesta = encuestaRequestDTO;
        encuesta = this.encuestasRepository.save(encuesta);
        if( archivoDTO != null ) {
            MinIO minio = new MinIO();
            String needle = "{encuestaId}";
            encuesta.setArchivo(new ArrayList<>());
            FileEntity file = archivo;
            file.setUrl(file.getUrl().replace(needle, Long.toString(encuesta.getId())));
            file.setCreatedAt(new Date());
            encuesta.addArchivo(file);
            minio.updateObject(file.getUrl(), archivoDTO);
            encuesta = this.encuestasRepository.save(encuesta);
        }

        return encuesta;
    }


    public EncuestaEntity update(EncuestaEntity entity) throws Exception {
        EncuestaEntity encuestaEntity = this.encuestasRepository.findById(entity.getId()).get();
        if(encuestaEntity != null){
            EncuestaEntity encuesta = this.encuestasRepository.save(entity);
            return this.encuestasRepository.save(encuesta);
        }
        throw new Exception("No existe la tarea");
    }

    public Boolean delete(Long id) {
        try {
            EncuestaEntity model = this.findById(id);
            if (model.getId() > 0 ) {
                this.encuestasRepository.delete( model );
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public Page<EncuestaEntity> pageEncuestas(
            Pageable pageable,
            String titulo
            /*
            String descripcion,
            String entregable,
            String fechaInicio,
            String fechaTermino,
            String estatus

             */
    ) throws Exception{
        //if(usuario_id != null ){

        //}
        //if(activeProfileEntity != null){
            //if( tarea != null || descripcion != null || entregable != null || fechaInicio != null || fechaTermino != null || estatus != null){
                Page<EncuestaEntity> respuesta = this.encuestasRepository.findAllByOrderByTituloAsc(
                        pageable,
                        titulo
                        /*
                        descripcion,
                        entregable,
                        fechaInicio,
                        fechaTermino,
                        estatus

                        */
                );
                return respuesta;
            //}
            //return this.encuestasRepository.findAllByOrderByTareaDesc(pageable);
        //}
        //throw new Exception("El usuario no tiene una sesión iniciada");
    }

    public EncuestaEntity updateStatusEncuesta(Long encuestaId, Boolean status)  throws Exception{
        EncuestaEntity encuesta = this.encuestasRepository.findById(encuestaId).get();
        if(encuesta != null){
            return this.encuestasRepository.save(encuesta);
        }
        throw new Exception("Esta tarea no existe");
    }


    public EncuestaEntity findById(Long encuestaId) throws Exception {
        EncuestaEntity encuesta = this.encuestasRepository.findById(encuestaId).get();
        if (encuesta != null) {

            return encuesta;
        }
        throw new Exception("La encuesta no existe");
    }

}
