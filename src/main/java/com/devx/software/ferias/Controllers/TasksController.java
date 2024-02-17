package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.DTOs.Tasks.*;
import com.devx.software.ferias.Services.Tasks.TasksService;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Mappers.Tasks.TaskListMapper;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@PreAuthorize("isAuthenticated()")
public class TasksController {

    private final HttpHeaders headers = new HttpHeaders();
    private final TasksService tasksService;
    private final TaskListMapper taskListMapper;

    @Autowired
    public TasksController(
            TasksService tasksService,
            CatalogsService catalogsService,
            TaskListMapper taskListMapper,
            UsersRepository usersRepository) {
        this.tasksService = tasksService;
        this.taskListMapper = taskListMapper;
    }

    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<TaskListDTO>> getAllTasks(){
        try{
            this.headers.set("200","Consulta exitosa");
            return new ResponseEntity(
                    this.taskListMapper.listEntityToDto(this.tasksService.getAll()),
                    this.headers,
                    HttpStatus.OK
                    );
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<PaginationResponse> pageTasks(
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
            Page<TaskEntity> pageDataset = this.tasksService.pageTasks(
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
            response.setDataset(this.taskListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity formResourceTaskDTO){
        try{
            this.headers.set("200","Registro exitoso");
            return new ResponseEntity(this.tasksService.save(formResourceTaskDTO),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<HashMap<String,Object>> deleteTask(@PathVariable Long taskId){
        try{
            this.headers.set("200","Borrado exitoso");
            return new ResponseEntity(this.tasksService.delete(taskId),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{taskId}/status/{status}")
    public ResponseEntity<TaskListDTO> updateStatusUser(@PathVariable Long taskId, @PathVariable Boolean status){
        try{
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity<>(this.taskListMapper.entityToDTO(this.tasksService.updateStatusTask(taskId,status)),this.headers,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long taskId, @RequestBody TaskEntity tarea){
        try{
            this.headers.set("200","Actualización exitosa");
            return new ResponseEntity(this.tasksService.update( tarea ),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @GetMapping("/minuta/{idminuta}")
    public ResponseEntity<List<TaskEntity>> findAltaskbyminuta(@PathVariable long idminuta) { 
         try{
            this.headers.set("200"," exitosa");
            return new ResponseEntity(this.tasksService.findallTaskbyMinuta(idminuta),this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
