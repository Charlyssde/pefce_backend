package com.devx.software.ferias.Services.Autodiagnosticos;

//import com.devx.software.ferias.DTOs.Enterprises.EnterpriseListUserFilterDTO;
import com.devx.software.ferias.Entities.Autodiagnosticos.AutodiagnosticoEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import com.devx.software.ferias.Repositories.Sistema.SistemaRepository;
import com.devx.software.ferias.Services.Notifications.NotificationsService;

import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Misc.MinIO;
import com.devx.software.ferias.Repositories.Autodiagnosticos.AutodiagnosticosRepository;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Services.Enterprises.EnterprisesService;
import com.devx.software.ferias.Repositories.Enterprises.EnterprisesRepository;

import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Services.Users.UserService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AutodiagnosticosService {

    private final AutodiagnosticosRepository autodiagnosticosRepository;

    private final UserService userService;
    private final EnterprisesService enterprisesService;
    private final EnterprisesRepository enterprisesRepository;
    private final SistemaRepository sistemaRepository;

    private final NotificationsService notificationsService;

    @Autowired
    public AutodiagnosticosService(
            AutodiagnosticosRepository autodiagnosticosRepository,
            UserService userService,
            EnterprisesService enterprisesService,
            SistemaRepository sistemaRepository,
            NotificationsService notificationsService,
            EnterprisesRepository enterprisesRepository) {
        this.autodiagnosticosRepository = autodiagnosticosRepository;
        this.userService = userService;
        this.enterprisesService = enterprisesService;
        this.sistemaRepository = sistemaRepository;
        this.notificationsService = notificationsService;
        this.enterprisesRepository = enterprisesRepository;
    }

    public EnterpriseEntity saveAutodiagnostico(
            EnterpriseEntity enterprise,
            FileEntity cif,
            FileEntity opinion,
            MultipartFile cifArchivo,
            MultipartFile opinionArchivo
    ) throws Exception {
        MinIO minio = new MinIO();
        String needle = "{autodiagnosticoId}";

            //String email = SecurityContextHolder.getContext().getAuthentication().getName();
            //UserEntity usuario = this.userService.findByEmail(email);
            //EnterpriseListUserFilterDTO empresaContactos = this.enterprisesService.findByUserId( usuario.getId() );
            //EnterpriseEntity empresa = this.enterprisesService.findById( empresaContactos.getId() );
            //autodiagnostico.setEmpresa(empresa);
            //autodiagnostico.setContesto(usuario);

            System.out.println( enterprise.getAutodiagnostico().getNombre_comercial() );
            enterprise = this.enterprisesRepository.save(enterprise);
            System.out.println( enterprise.getAutodiagnostico().getNombre_comercial() );

            Boolean a = false;
            if (cif != null && cifArchivo != null) {
                a = true;
                FileEntity file = cif;
                file.setUrl(file.getUrl().replace(needle, Long.toString(enterprise.getAutodiagnostico().getId())));
                file.setCreatedAt(new Date());
                enterprise.getAutodiagnostico().addCif(file);
                minio.updateObject(file.getUrl(), cifArchivo);
            }

            if (opinion != null && opinionArchivo != null) {
                a = true;
                FileEntity fileo = opinion;
                fileo.setUrl(fileo.getUrl().replace(needle, Long.toString(enterprise.getAutodiagnostico().getId())));
                fileo.setCreatedAt(new Date());
                enterprise.getAutodiagnostico().addOpinion(fileo);
                minio.updateObject(fileo.getUrl(), opinionArchivo);
            }

            if (a) {
                enterprise = this.enterprisesRepository.save(enterprise);
            }

            try {
                List<SistemaEntity> sistemaEntity = this.sistemaRepository.findAll();
                UserEntity responsable = sistemaEntity.get(0).getResponableDce();
                Mailgun mailgun = new Mailgun();
                mailgun.sendBasicEmail("Se ha creado o actualizado un autodiagnostico", responsable.getEmail(), "Se le informa que se ha llenado o actualizado el formulario del autodiagnostico de la empresa: " + enterprise.getEmpresa() + "<br><br>Acceda al panel de empresas para poder acceder a esta información");

                NotificationsEntity ne = new NotificationsEntity();
                ne.setTexto("Se le informa que se ha llenado o actualizado el formulario del autodiagnostico de la empresa: " + enterprise.getEmpresa());
                ne.setVista(Boolean.FALSE);
                ne.setCreatedAt(new Date());
                ne.setTipo(Long.valueOf(1));
                ne.setDestinatario(responsable);
                this.notificationsService.create(ne);

                return enterprise;
            } catch (Exception e) {
                return enterprise;
            }
        //}
        //System.out.println("G");
        //return autodiagnostico;
    }
/*
    public AutodiagnosticoEntity findByEnterpriseId(Long enterpriseId) throws Exception {
        AutodiagnosticoEntity autodiagnostico = new AutodiagnosticoEntity();
        try {
            autodiagnostico = this.autodiagnosticosRepository.findByEmpresaId(enterpriseId);
            if (autodiagnostico.getId() != null ) {
                return autodiagnostico;
            }
        } catch (Exception e) {
            return autodiagnostico;
        }
        return autodiagnostico;
    }
*/
}
