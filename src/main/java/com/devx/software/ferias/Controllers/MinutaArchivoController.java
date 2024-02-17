/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Services.MinutaArchivo.MinutaArchivoService2;
import com.devx.software.ferias.Services.Minutas.MinutaArchivoService;
import java.io.IOException;
import java.io.InputStream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


/**
 *
 * @author blopez
 */
@RestController
@RequestMapping("/minuta-archivo")
public class MinutaArchivoController {
    
private final   MinutaArchivoService2 minutaArchivoService2;

     @Autowired
    public MinutaArchivoController(
          MinutaArchivoService2 minutaArchivoService2
    ) {
        this.minutaArchivoService2 = minutaArchivoService2;
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createMinutaArchivo(@Valid @RequestParam("file") MultipartFile file , @RequestParam("idminuta") long idminuta, long idusuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.minutaArchivoService2.save(file, idminuta, idusuario));
    }
    
    
@CrossOrigin(origins = "*")
@RequestMapping(value = "/byMinuta/{id}", method = RequestMethod.GET)
public ResponseEntity<?> getList(@Valid @PathVariable("id") long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.minutaArchivoService2.findAllByMinutaId(id));
}
    
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{nombre}", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity<byte[]> getUrl(@Valid @PathVariable("nombre") String nombre) {
        try {
            InputStream stream = this.minutaArchivoService2.getUrl(nombre);
            byte[] bytes = IOUtils.toByteArray(stream); // Utiliza la biblioteca Apache Commons IO para convertir InputStream a byte[]
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Maneja la excepción según sea necesario
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    
}
