package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Encuestas.EncuestaRequestDTO;
import com.devx.software.ferias.DTOs.Encuestas.FormResourceEncuestaDTO;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Encuestas.EncuestaEntity;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Mappers.Encuestas.EncuestaListMapper;
import com.devx.software.ferias.Mappers.Encuestas.FormResourceEncuestaMapper;
import com.devx.software.ferias.Services.Encuestas.EncuestasService;
import com.devx.software.ferias.DTOs.Encuestas.EncuestaListDTO;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/encuestas")
@PreAuthorize("isAuthenticated()")
public class EncuestasController {

    private final HttpHeaders headers = new HttpHeaders();
    private final EncuestasService encuestasService;
    private final EncuestaListMapper encuestaListMapper;

    private final FormResourceEncuestaMapper formResourceEncuestaMapper;

    @Autowired
    public EncuestasController(
            EncuestasService encuestasService,
            UsersRepository usersRepository, EncuestaListMapper encuestaListMapper, FormResourceEncuestaMapper formResourceEncuestaMapper) {
        this.encuestasService = encuestasService;
        this.encuestaListMapper = encuestaListMapper;
        this.formResourceEncuestaMapper = formResourceEncuestaMapper;
    }

    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<EncuestaEntity>> getAllEncuestas(){
        try{
            this.headers.set("200","Consulta exitosa");
            return new ResponseEntity(
                    this.encuestaListMapper.listEntityToDto(this.encuestasService.getAll()),
                    this.headers,
                    HttpStatus.OK
                    );
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<PaginationResponse> pageEncuestas(
            @RequestParam(required = false) UserEntity usuario_id,
            @RequestParam(required = false) String titulo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pegeable = PageRequest.of(page, size);
            Page<EncuestaEntity> pageDataset = this.encuestasService.pageEncuestas(
                    pegeable,
                    titulo
            );
            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.encuestaListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PostMapping("")
    public ResponseEntity<EncuestaEntity> createEncuesta(@RequestBody FormResourceEncuestaDTO formResourceEncuestaDTO){
        System.out.println( "Encuesta Controller 1" );
        try{
            System.out.println( "Encuesta Controller 1" );
            this.headers.set("200","Registro exitoso");
            return new ResponseEntity(this.encuestasService.save(formResourceEncuestaDTO),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

     */

    @RequestMapping(path = "", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EncuestaEntity> saveEncuesta(
            @RequestParam(name = "encuesta") String encuesta,
            @RequestParam(name = "archivo") String archivo,
            @RequestParam(name = "archivoDTO", required = false) MultipartFile archivoDTO
    ) throws JsonProcessingException {
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            EncuestaEntity encuestaRequestDTO = objectMapper.readValue(encuesta, EncuestaEntity.class);
            FileEntity archivoTmp = objectMapper.readValue(archivo, FileEntity.class);

            this.headers.set("200", "Guardado exitoso");
            return new ResponseEntity<>(this.encuestasService.saveEncuesta(
                    encuestaRequestDTO,
                    archivoTmp,
                    archivoDTO
            ), this.headers, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{encuestaId}")
    public ResponseEntity<HashMap<String,Object>> deleteEncuesta(@PathVariable Long encuestaId){
        try{
            this.headers.set("200","Borrado exitoso");
            return new ResponseEntity(this.encuestasService.delete(encuestaId),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{encuestaId}/status/{status}")
    public ResponseEntity<EncuestaListDTO> updateStatusUser(@PathVariable Long encuestaId, @PathVariable Boolean status){
        try{
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity<>(this.encuestaListMapper.entityToDTO(this.encuestasService.updateStatusEncuesta(encuestaId,status)),this.headers,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{encuestaId}")
    public ResponseEntity<EncuestaEntity> updateEncuesta(@PathVariable Long encuestaId, @RequestBody EncuestaEntity encuesta){
        try{
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity(this.encuestasService.update( encuesta ),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{encuestaId}/formResources")
    public ResponseEntity<HashMap<String, Object>> formResources(
            @PathVariable(required = false) Long encuestaId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            FormResourceEncuestaDTO encuestaDTO = null;
            encuestaDTO = this.formResourceEncuestaMapper.entityToDTO( this.encuestasService.findById(encuestaId) );

            formResources.put("encuesta", encuestaDTO);
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{encuestaId}")
    public ResponseEntity<EncuestaEntity> findById(@PathVariable Long encuestaId) {

        try {
            this.headers.set("200", "Consulta exitosa");
            ResponseEntity response = new ResponseEntity(this.encuestasService.findById(encuestaId), this.headers, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
