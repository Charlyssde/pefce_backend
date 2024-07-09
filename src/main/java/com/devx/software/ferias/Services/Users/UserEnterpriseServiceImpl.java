/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Services.Users;

import com.devx.software.ferias.Entities.UsuarioEmpresa.UsuarioEmpresa;
import com.devx.software.ferias.Repositories.Users.UserEnterpriseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author domingo
 */
@Service
public class UserEnterpriseServiceImpl implements UserEnterpriseService{

    private final UserEnterpriseRepository userEnterpriseRepository;

    @Autowired
    public UserEnterpriseServiceImpl(UserEnterpriseRepository userEnterpriseRepository) {
        this.userEnterpriseRepository = userEnterpriseRepository;
    }
    
    
    
    @Override
    public List<UsuarioEmpresa> findAllUserEnterprise(List<Long> eventoIds) {
        return userEnterpriseRepository.findUsuariosByEventoIds(eventoIds);
    }
    
}
