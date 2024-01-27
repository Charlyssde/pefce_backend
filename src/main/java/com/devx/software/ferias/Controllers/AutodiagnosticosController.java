package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Entities.Autodiagnosticos.AutodiagnosticoEntity;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Services.Autodiagnosticos.AutodiagnosticosService;
import com.devx.software.ferias.Repositories.Users.UsersRepository;

import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RestController
@RequestMapping("/autodiagnostico")
@PreAuthorize("isAuthenticated()")
public class AutodiagnosticosController {

    private final HttpHeaders headers = new HttpHeaders();
    private final AutodiagnosticosService autodiagnosticosService;
    //private final AutodiagnosticoListMapper autodiagnosticoListMapper;

    @Autowired
    public AutodiagnosticosController(
            AutodiagnosticosService autodiagnosticosService,
            CatalogsService catalogsService,
            //AutodiagnosticoListMapper autodiagnosticoListMapper,
            UsersRepository usersRepository) {
        this.autodiagnosticosService = autodiagnosticosService;
        //this.autodiagnosticoListMapper = autodiagnosticoListMapper;
    }

    @PostMapping("")
    public ResponseEntity<EnterpriseEntity> createAutodiagnostico(
            @RequestParam(name = "empresa") String enterprise,
            @RequestParam(required = false, name = "cif") String cif,
            @RequestParam(required = false, name = "opinion") String opinion,
            @RequestParam(required = false, name = "cifArchivo") MultipartFile cifArchivo,
            @RequestParam(required = false, name = "opinionArchivo") MultipartFile opinionArchivo

            ){ //@RequestBody AutodiagnosticoEntity autodiagnostico
        try{

            ObjectMapper objectMapper = new ObjectMapper();

            EnterpriseEntity enterpriseTmp = objectMapper.readValue(enterprise, EnterpriseEntity.class);

            FileEntity cifTmp = new FileEntity();
            if( cifArchivo != null  ) {
                cifTmp = objectMapper.readValue(cif, FileEntity.class);
            }

            FileEntity opinionTmp = new FileEntity();
            if( opinionArchivo != null ){
                opinionTmp = objectMapper.readValue(opinion, FileEntity.class);
            }
            this.headers.set("200","Registro exitoso");

            ResponseEntity responseEntity = new ResponseEntity(this.autodiagnosticosService.saveAutodiagnostico(
                    enterpriseTmp,
                    cifTmp,
                    opinionTmp,
                    cifArchivo,
                    opinionArchivo),this.headers,HttpStatus.OK);

            return responseEntity;
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @PreAuthorize("permitAll()")
    @GetMapping("/{autodiagnosticoId}")
    public ResponseEntity<AutodiagnosticoEntity> findByEnterpriseId(@PathVariable Long autodiagnosticoId) {
        try {
            this.headers.set("200", "Consulta exitosa");
            AutodiagnosticoEntity responseAutodiagnostico = this.autodiagnosticosService.findByEnterpriseId(autodiagnosticoId);

            return new ResponseEntity<>( responseAutodiagnostico, this.headers, HttpStatus.OK);

            //return response;
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/

/*
    @PatchMapping("/{autodiagnosticoId}/status/{status}")
    public ResponseEntity<AutodiagnosticoListDTO> updateStatusUser(@PathVariable Long autodiagnosticoId, @PathVariable Boolean status){
        try{
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity<>(this.autodiagnosticoListMapper.entityToDTO(this.autodiagnosticosService.updateStatusAutodiagnostico(autodiagnosticoId,status)),this.headers,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{autodiagnosticoId}")
    public ResponseEntity<AutodiagnosticoEntity> updateAutodiagnostico(@PathVariable Long autodiagnosticoId, @RequestBody AutodiagnosticoEntity tarea){
        try{
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity(this.autodiagnosticosService.update( tarea ),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*
    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<AutodiagnosticoListDTO>> getAllAutodiagnosticos(){
        try{
            this.headers.set("200","Consulta exitosa");
            return new ResponseEntity(
                    this.autodiagnosticoListMapper.listEntityToDto(this.autodiagnosticosService.getAll()),
                    this.headers,
                    HttpStatus.OK
                    );
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<PaginationResponse> pageAutodiagnosticos(
            @RequestParam(required = false) UserEntity usuario_id,
            @RequestParam(required = false) String tarea,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String entregable,
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaTermino,
            @RequestParam(required = false) String estatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pegeable = PageRequest.of(page, size);
            Page<AutodiagnosticoEntity> pageDataset = this.autodiagnosticosService.pageAutodiagnosticos(
                    pegeable,
                    usuario_id,
                    tarea,
                    descripcion,
                    entregable,
                    fechaInicio,
                    fechaTermino,
                    estatus
            );
            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.autodiagnosticoListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{autodiagnosticoId}")
    public ResponseEntity<HashMap<String,Object>> deleteAutodiagnostico(@PathVariable Long autodiagnosticoId){
        try{
            this.headers.set("200","Borrado exitoso");
            return new ResponseEntity(this.autodiagnosticosService.delete(autodiagnosticoId),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

}
