package com.devx.software.ferias.Repositories.Capacitacion;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Entities.Capacitacion.ContactosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactosRepository extends JpaRepository<ContactosEntity, Long> {
    ContactosEntity findContactoById(Long id);

    ContactosEntity findByCorreo(String correo);

}
