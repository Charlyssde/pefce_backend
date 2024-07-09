/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devx.software.ferias.Services.Users;

import com.devx.software.ferias.Entities.UsuarioEmpresa.UsuarioEmpresa;
import java.util.List;

/**
 *
 * @author blopez
 */
public interface UserEnterpriseService {
    
    List<UsuarioEmpresa> findAllUserEnterprise(List<Long> eventoIds);
    
}
