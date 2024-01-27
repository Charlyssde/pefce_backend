package com.devx.software.ferias.Services.MasiveMail;

import com.devx.software.ferias.DTOs.PromocionDigital.EmailDTO;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasiveMailService {

    private final UserService userService;

    @Autowired
    public MasiveMailService(
            UserService userService) {
        this.userService = userService;
    }


    //    READ

    public EmailDTO sendMasiveMail(EmailDTO correo) throws Exception{
        List<String> plantillas = correo.getMensaje();
        final int[] contador = {0};
        correo.getDestinos().forEach( correos -> {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            UserEntity usuario = this.userService.findByEmail( correos );
            Mailgun mailgun = new Mailgun();
            try {
                mailgun.sendBasicEmail( correo.getAsunto(), correos , plantillas.get(contador[0]) );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            contador[0]++;
        });

        return correo;
    }

}
