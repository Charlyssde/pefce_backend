package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.PromocionDigital.EmailDTO;
import com.devx.software.ferias.Services.MasiveMail.MasiveMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masiveMail")
@PreAuthorize("isAuthenticated()")
public class MasiveMailController {

    private final HttpHeaders headers = new HttpHeaders();
    private final MasiveMailService masiveMailService;
    //private final TaskListMapper taskListMapper;

    @Autowired
    public MasiveMailController(
            MasiveMailService masiveMailService) {
        this.masiveMailService = masiveMailService;
    }


    @PostMapping("sendMasiveMail")
    public ResponseEntity<EmailDTO> createTask(@RequestBody EmailDTO correo){
        try{
            this.headers.set("200","Registro exitoso");
            System.out.println("A");
            return new ResponseEntity( this.masiveMailService.sendMasiveMail(correo) ,this.headers,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
