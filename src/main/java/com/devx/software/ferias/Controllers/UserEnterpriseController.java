/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Services.Users.UserEnterpriseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author blopez
 */
@RestController
@RequestMapping("/userEnterprise")
public class UserEnterpriseController {
    
    private final UserEnterpriseService userEnterpriseService;

    @Autowired
    public UserEnterpriseController(UserEnterpriseService userEnterpriseService) {
        this.userEnterpriseService = userEnterpriseService;
    }
    
     @CrossOrigin(origins = "*")
    @RequestMapping(value = "/evento", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@RequestParam("eventoIds") List<Long> eventoIds) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userEnterpriseService.findAllUserEnterprise(eventoIds));
    }
    

}
