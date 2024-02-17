/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Services.MinutaArchivo;

import com.devx.software.ferias.Entities.Minutas.MinutaArchivoEntity;
import java.io.InputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author blopez
 */
public interface MinutaArchivoService2 {

    MinutaArchivoEntity save(MultipartFile file, long idminuta, long idusuario);

    MinutaArchivoEntity findById(String id);
    
    List<MinutaArchivoEntity> findAll();
    
    MinutaArchivoEntity delete(String id);

    InputStream getUrl(String nombre);
    
      List<MinutaArchivoEntity> findAllByMinutaId( long id);
    
}
