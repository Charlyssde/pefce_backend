package com.devx.software.ferias.Services.Events;

import com.devx.software.ferias.Entities.Events.EventUsersEntity;
import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Mail.EmailService;
import com.devx.software.ferias.Mail.Adapter.SendgridAdapter;
import com.devx.software.ferias.Mail.Gateway.MailGateway;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Misc.MinIO;
import com.devx.software.ferias.Repositories.Events.EventsRepository;
import com.devx.software.ferias.Repositories.Events.EventsUserRepository;
import com.devx.software.ferias.Services.Users.UserService;
//import com.devx.software.ferias.zx_mappers.TiposReunionMapper;
//import com.devx.software.ferias.zx_repositories.TiposReunionRepository;
//import com.devx.software.ferias.zx_services.VideollamadasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventsService {

    private final EventsRepository eventsRepository;
    private final EventsUserRepository eventsUserRepository;
//    private final VideollamadasService vService;
//
//    private final TiposReunionRepository trRepository;
//    private final TiposReunionMapper trMapper;

    private final UserService userService;

    MailGateway mailAdapter;

    @Autowired 
    EmailService emailService;

    @Autowired
    public EventsService(EventsRepository eventsRepository,
//                         VideollamadasService vService,
//                         EventosMapper eMapper,
//                         TiposReunionRepository trRepository,
//                         TiposReunionMapper trMapper,
                         UserService userService,
                         EventsUserRepository eventsUserRepository) {
        this.eventsRepository = eventsRepository;
//        this.vService = vService;
//        this.eMapper = eMapper;
//        this.trRepository = trRepository;
//        this.trMapper = trMapper;
        this.userService = userService;
        this.eventsUserRepository = eventsUserRepository;
    }


    public EventEntity create(
            EventEntity entity,
            FileEntity datosimagen,
            MultipartFile archivoimagen
            ) throws Exception {
        try {
            MinIO minio = new MinIO();
            String needle = "MIID";
            entity.setArchivo( new ArrayList<>() );

            EventEntity eventoEntity = new EventEntity();
            eventoEntity = eventsRepository.save(entity);

            System.out.println(archivoimagen);

            if( datosimagen != null && archivoimagen != null ) {
                FileEntity fileo = datosimagen;
                fileo.setCreatedAt(new Date());
                fileo.setUrl(datosimagen.getUrl().replace(needle, Long.toString(eventoEntity.getId())));
                minio.updateObject(datosimagen.getUrl(), archivoimagen);
                eventoEntity.addArchivo( fileo );
                //eventoEntity = eventsRepository.save(eventoEntity);
            }
            eventoEntity = eventsRepository.save(eventoEntity);
            return eventoEntity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<EventEntity> page() {
        return eventsRepository.findAll();
    }

    //
    public EventEntity findById(Long id) {
        return eventsRepository.findEventoById(id);
    }


    public EventEntity update(
            EventEntity entity,
            FileEntity datosimagen,
            MultipartFile archivoimagen
    ) throws Exception {
        try {
            MinIO minio = new MinIO();
            String needle = "MIID";
            entity.setArchivo( new ArrayList<>() );

            EventEntity eventoEntity = entity;

            FileEntity fileo = datosimagen;
            if( datosimagen != null && archivoimagen != null ) {

                fileo.setCreatedAt(new Date());
                fileo.setUrl(datosimagen.getUrl().replace(needle, Long.toString(eventoEntity.getId())));
                minio.updateObject(datosimagen.getUrl(), archivoimagen);


            }
            eventoEntity.addArchivo( fileo );
            eventoEntity = eventsRepository.save(eventoEntity);
            return eventoEntity;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<EventEntity> getLastTenEvents(){
        return this.eventsRepository.getLastTenEvents();
    }

//
//    public void deleteById(Long id) {
//        eventosRepository.deleteById(id);
//    }
//
//    public Long count() {
//        return eventosRepository.countAllByActivoTrue();
//    }
//
    public List<EventsRepository.EventosHoy> getByFechaFinTimeAfterToday() {
        return eventsRepository.getByFechaFinTimeAfterToday();
    }


    public List<EventEntity> listByIdUsuario(long id){
        return eventsRepository.getAllByUser(id);
    }

    @Transactional
    public EventUsersEntity agregarParticipante(EventUsersEntity entity) throws Exception {
        EventEntity eventEntity = this.findById(entity.getEvento().getId());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity usuario = this.userService.findByEmail( email );
        entity.setUsuario(usuario);
        //eventEntity.addUsuarios( usuario );
        try {
            eventsUserRepository.save(entity);
            //eventsRepository.save(eventEntity);
            /*Mailgun mailgun = new Mailgun();
            mailgun.sendBasicEmail(
                    "Registro de participante a evento: " + eventEntity.getNombreEvento(),
                    email,
                    "Se ha recibido, su solicitud para participar en el evento: " + eventEntity.getNombreEvento()
            );*/
            this.emailService.sendEmail(
                email, 
                "Registro de participante a evento: " + eventEntity.getNombreEvento(), 
                "Se ha recibido, su solicitud para participar en el evento: " + eventEntity.getNombreEvento()
                );

            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Void cancelarParticipacion(EventUsersEntity entity) throws Exception {
        EventEntity eventEntity = this.findById(entity.getEvento().getId());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity usuario = this.userService.findByEmail( email );
        try {
            Void entitySave = eventsRepository.cancelarParticipacion(eventEntity.getId(), usuario.getId());

            Mailgun mailgun = new Mailgun();
            mailgun.sendBasicEmail(
                    "Registro de participante cancelado al evento: " + eventEntity.getNombreEvento(),
                    email,
                    "Se ha cancelado su solicitud para participar en el evento: " + eventEntity.getNombreEvento()
            );
            return entitySave;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new Exception(e.getMessage());
        }
    }


    public List<Object[]> obtenerTotalPorSexo() throws Exception{
        return this.eventsRepository.obtenerTotalPorSexo();
    }

    public List<Object[]> obtenerTotalPorMunicipio() throws Exception{
        return this.eventsRepository.obtenerTotalPorMunicipio();
    }

    public List<Object[]> obtenerTotalPorsector() throws Exception{
        return this.eventsRepository.obtenerTotalPorSector();
    }

}
