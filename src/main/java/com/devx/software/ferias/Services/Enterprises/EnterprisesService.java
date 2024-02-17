package com.devx.software.ferias.Services.Enterprises;

import com.devx.software.ferias.DTOs.Auth.PasswordRecoveryRequest;
import com.devx.software.ferias.DTOs.Enterprises.*;
import com.devx.software.ferias.DTOs.Users.UserRequestDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseAccessRequestEntity;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseTradeImageEntity;
import com.devx.software.ferias.Entities.Enterprises.ProductEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Entities.Notifications.NotificationsEntity;
import com.devx.software.ferias.Entities.Profiles.ProfileEntity;
import com.devx.software.ferias.Entities.Shared.DomicilioEntity;
import com.devx.software.ferias.Entities.Sistema.SistemaEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Enums.TipoPerfilEnum;
import com.devx.software.ferias.Mail.EmailService;
import com.devx.software.ferias.Mappers.Enterprises.EnterpriseListUserFilterMapper;
import com.devx.software.ferias.Mappers.Enterprises.FormResourceEnterpriseMapper;
import com.devx.software.ferias.Mappers.Enterprises.ProductMapper;
import com.devx.software.ferias.Mappers.Files.FilesMapper;
import com.devx.software.ferias.Mappers.Users.FormResourcesUserMapper;
import com.devx.software.ferias.Misc.Mailgun;
import com.devx.software.ferias.Misc.MinIO;
import com.devx.software.ferias.Repositories.Domicilios.DomiciliosRepository;
import com.devx.software.ferias.Repositories.Enterprises.EnterpriseAccessRequestRepository;
import com.devx.software.ferias.Repositories.Enterprises.EnterpriseTradeImageRepository;
import com.devx.software.ferias.Repositories.Enterprises.EnterprisesRepository;
import com.devx.software.ferias.Repositories.Enterprises.ProductRepository;
import com.devx.software.ferias.Repositories.Files.FilesRepository;
import com.devx.software.ferias.Repositories.Profiles.ProfileRepository;
import com.devx.software.ferias.Repositories.Sistema.SistemaRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import com.devx.software.ferias.Services.Notifications.NotificationsService;
import com.devx.software.ferias.Services.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class EnterprisesService {

    private final PasswordEncoder passwordEncoder;

    private final DomiciliosRepository domiciliosRepository;
    private final EnterpriseTradeImageRepository enterpriseTradeImageRepository;
    private final EnterpriseAccessRequestRepository enterpriseAccessRequestRepository;
    private final EnterprisesRepository enterprisesRepository;
    private final FilesRepository filesRepository;
    private final ProductRepository productRepository;
    private final ProfileRepository profileRepository;
    private final UsersRepository usersRepository;
    private final NotificationsService notificationsService;

    private final EnterpriseListUserFilterMapper enterpriseListUserFilterMapper;
    private final FilesMapper filesMapper;
    private final FormResourceEnterpriseMapper formResourceEnterpriseMapper;
    private final FormResourcesUserMapper formResourcesUserMapper;
    private final ProductMapper productMapper;
    private final UserService userService;
    private final SistemaRepository sistemaRepository;

    private final EmailService emailService;

    @Autowired
    public EnterprisesService(
            PasswordEncoder passwordEncoder,
            DomiciliosRepository domiciliosRepository,
            EnterpriseTradeImageRepository enterpriseTradeImageRepository,
            EnterpriseAccessRequestRepository enterpriseAccessRequestRepository,
            EnterprisesRepository enterprisesRepository,
            FilesRepository filesRepository,
            ProductRepository productRepository,
            ProfileRepository profileRepository,
            UsersRepository usersRepository,
            NotificationsService notificationsService, EnterpriseListUserFilterMapper enterpriseListUserFilterMapper,
            FilesMapper filesMapper,
            FormResourceEnterpriseMapper formResourceEnterpriseMapper,
            FormResourcesUserMapper formResourcesUserMapper,
            ProductMapper productMapper,
            UserService userService,
            SistemaRepository sistemaRepository,
            EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.domiciliosRepository = domiciliosRepository;
        this.enterpriseTradeImageRepository = enterpriseTradeImageRepository;
        this.enterpriseAccessRequestRepository = enterpriseAccessRequestRepository;
        this.enterprisesRepository = enterprisesRepository;
        this.filesRepository = filesRepository;
        this.productRepository = productRepository;
        this.profileRepository = profileRepository;
        this.usersRepository = usersRepository;
        this.notificationsService = notificationsService;

        this.enterpriseListUserFilterMapper = enterpriseListUserFilterMapper;
        this.filesMapper = filesMapper;
        this.formResourceEnterpriseMapper = formResourceEnterpriseMapper;
        this.formResourcesUserMapper = formResourcesUserMapper;
        this.productMapper = productMapper;
        this.userService = userService;
        this.sistemaRepository = sistemaRepository;
        this.emailService = emailService;
    }

    public static int getRandomIndex(int max) {
        return ThreadLocalRandom.current().nextInt(0, max);
    }

    public String createRandomPassword() {
        String randomPassword = "";
        String validPasswordCharacters = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int x = 0; x < 25; x++) {
            randomPassword += validPasswordCharacters.charAt(getRandomIndex(validPasswordCharacters.length()));
        }
        return randomPassword;
    }

    public List<EnterpriseListUserFilterDTO> getAllByTopic(String topic) throws Exception {
        if (!topic.equals(null)) {
            List<EnterpriseEntity> enterpriseEntityList = new ArrayList<>();
            if ("active".equals(topic)) {
                enterpriseEntityList = this.getAllActiveEnterprises();
            }

            return this.enterpriseListUserFilterMapper.listEntityToDTO(enterpriseEntityList);
        }
        throw new Exception("No hay tópico para filtrar");
    }


    public List<EnterpriseEntity> getAll() {
        return this.enterprisesRepository.findAll();
    }

    // Enterprises section
    public List<EnterpriseListUserFilterDTO> getAllWithFilter(
            String estatus,
            String categoria,
            String regimenFiscal,
            String sector,
            String subsector
    ) {
        List<EnterpriseEntity> enterpriseEntityList = this.enterprisesRepository.getAllEnterprises(
                estatus,
                categoria,
                regimenFiscal,
                sector,
                subsector
        );
        return this.enterpriseListUserFilterMapper.listEntityToDTO(enterpriseEntityList);

    }

    public Page<EnterpriseEntity> pageEnterprises(
            Pageable pageable,
            String nombre,
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector,
            String estatus
    ) throws Exception {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity usuario = this.userService.findByEmail(email);

        if (usuario.getPerfiles().get(0).getNombre().equals("Empresa")) {

            EnterpriseListUserFilterDTO enterprise = this.findByUserId(usuario.getId());
            return this.enterprisesRepository.findAllByEnterpriseId(pageable, enterprise.getId());
        } else {

            ProfileEntity activeProfileEntity = this.profileRepository.findByUserActiveSessionEmail(email);

            if (activeProfileEntity != null) {
                if (nombre != null || regimenFiscal != null || categoria != null || sector != null || subsector != null || estatus != null) {
                    return this.enterprisesRepository.findAllByOrderByCreatedAtDescAndMiscFilters(
                            pageable,
                            nombre,
                            regimenFiscal,
                            categoria,
                            sector,
                            subsector,
                            estatus
                    );
                }
                return this.enterprisesRepository.findAllByOrderByCreatedAtDesc(pageable);

            }
        }
        throw new Exception("El usuario no tiene una sesión iniciada");
    }

    public EnterpriseEntity findById(Long enterpriseId) throws Exception {
        EnterpriseEntity enterprise = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterprise != null) {
            return enterprise;
        }
        throw new Exception("La empresa no existe");
    }

    public EnterpriseEntity create(FormResourceEnterpriseDTO formResourceEnterpriseDTO) throws Exception {
        EnterpriseEntity enterprise = this.enterprisesRepository.findByRfc(formResourceEnterpriseDTO.getRfc());
        if (enterprise == null) {
            enterprise = this.formResourceEnterpriseMapper.dtoToEntity(formResourceEnterpriseDTO);
            ProfileEntity perfil = this.profileRepository.findProfileEntityByTipo(TipoPerfilEnum.EMPRESA.getTipo());
            List<UserEntity> contactos = enterprise.getContactos();
            for (UserEntity contacto : contactos) {
                contacto.setEstatus(true);
                contacto.addPerfil(perfil);
            }
            List<EnterpriseTradeImageEntity> imagenEmpresarial = new ArrayList<>();
            imagenEmpresarial.add(this.createEmptyEnterpriseTradeImage());
            enterprise.setImagenEmpresarial(imagenEmpresarial);

            List<SistemaEntity> sistemaEntity = this.sistemaRepository.findAll();
            UserEntity responsable = sistemaEntity.get(0).getResponableSubin();

            Mailgun mailgun = new Mailgun();
            mailgun.sendBasicEmail("Se ha creado o actualizado un registro de una empresa", responsable.getEmail(), "Se le informa que se ha llenado el formulario de registro de la empresa: " + enterprise.getEmpresa() + "<br><br>Acceda al panel de empresas para poder acceder a esta información");

            try {
                NotificationsEntity ne = new NotificationsEntity();
                ne.setTexto("Se le informa que se ha creado un registro nuevo de la empresa: " + enterprise.getEmpresa());
                ne.setVista(Boolean.FALSE);
                ne.setCreatedAt(new Date());
                ne.setTipo(Long.valueOf(1));
                ne.setDestinatario(responsable);
                this.notificationsService.create(ne);

            } catch (Exception e) {
                return null;
            }

            return this.enterprisesRepository.save(enterprise);
        }
        throw new Exception("Este RFC ya se encuentra registrada");
    }

    public EnterpriseEntity ownEnterpriseRegistration(EnterpriseEntity enterpriseEntity) throws Exception {
        if (this.enterprisesRepository.findByRfc(enterpriseEntity.getRfc()) == null) {
            ProfileEntity perfil = this.profileRepository.findProfileEntityByTipo(TipoPerfilEnum.EMPRESA.getTipo());
            List<UserEntity> contactos = enterpriseEntity.getContactos();
//            Mailgun mailgun = new Mailgun();
            enterpriseEntity.addImagenEmpresarial(this.createEmptyEnterpriseTradeImage());
            for (UserEntity contacto : contactos) {
                contacto.setEstatus(false);
                contacto.addPerfil(perfil);
//                mailgun.sendBasicEmail("SEDECOP Solicitud de registro recibida", contacto.getEmail() , "El registro de su empresa se ha recibido, en cuanto sus datos sean validados recibirá una notificación junto con sus datos de acceso.");
            }
            enterpriseEntity.setDomicilios(null);
            return this.enterprisesRepository.save(enterpriseEntity);
        }
        throw new Exception("Esta empresa ya se encuentra registrada");
    }

    public EnterpriseEntity update(Long enterpriseId, FormResourceEnterpriseDTO formResourceEnterpriseDTO) throws Exception {
        EnterpriseEntity enterprise = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterprise != null) {
            List<Long> contactsIds = enterprise.getContactos().stream().map(UserEntity::getId).collect(Collectors.toList());
            this.usersRepository.deleteAllByIdIn(contactsIds);
            enterprise = this.formResourceEnterpriseMapper.dtoToEntity(formResourceEnterpriseDTO);
            ProfileEntity perfil = this.profileRepository.findProfileEntityByTipo(TipoPerfilEnum.EMPRESA.getTipo());
            List<UserEntity> contactos = enterprise.getContactos();
            for (UserEntity contacto : contactos) {
                contacto.setEstatus(true);
                contacto.addPerfil(perfil);
                this.usersRepository.save(contacto);
            }
            return this.enterprisesRepository.save(enterprise);

        }
        throw new Exception("Esta empresa no existe");
    }

    public HashMap<String, Object> delete(Long enterpriseId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            this.deleteAllEnterpriseAddresses(enterpriseId);
            this.deleteAllEnterpriseContacts(enterpriseId);
            this.deleteAllEnterpriseTradeImage(enterpriseId);
            this.deleteAllEnterpriseProducts(enterpriseId);
            this.enterprisesRepository.deleteById(enterpriseId);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Borrado exitoso");
            return response;
        }
        throw new Exception("La empresa no existe");
    }

    public EnterpriseEntity updateStatusEnterprise(Long enterpriseId, Boolean status) throws Exception {
        EnterpriseEntity enterprise = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterprise != null) {
            enterprise.setEstatus(status);

            List<SistemaEntity> sistemaEntity = this.sistemaRepository.findAll();
            UserEntity responsable = sistemaEntity.get(0).getResponableSubin();

            try {
                NotificationsEntity ne = new NotificationsEntity();
                ne.setTexto("Se le informa que se ha actualizado el estatus de la empresa: " + enterprise.getEmpresa() + "a " + (!status ? "Inactiva" : "Activa"));
                ne.setVista(Boolean.FALSE);
                ne.setCreatedAt(new Date());
                ne.setTipo(Long.valueOf(1));
                ne.setDestinatario(responsable);
                this.notificationsService.create(ne);

            } catch (Exception e) {
                return null;
            }

            return this.enterprisesRepository.save(enterprise);
        }

        throw new Exception("Esta empresa no existe");
    }

    public EnterpriseEntity saveAccessRequest(Long enterpriseId, EnterpriseAccessRequestDTO enterpriseAccessRequestDTO) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            enterpriseEntity.setEstatus(enterpriseAccessRequestDTO.getStatus());
            List<UserEntity> contactos = enterpriseEntity.getContactos();
            if (contactos.size() > 0) {
                for (UserEntity contacto : contactos) {
                    contacto.setEstatus(enterpriseAccessRequestDTO.getStatus());
                    this.usersRepository.save(contacto);
                }

                String password = this.createRandomPassword();
                UserEntity mainContact = contactos.get(0);
                mainContact.setPassword(this.passwordEncoder.encode(password));
                mainContact = this.usersRepository.save(mainContact);

                String message = "<p>" + enterpriseAccessRequestDTO.getMessage() + "<p>";
                if (enterpriseAccessRequestDTO.getStatus()) {
                    message = message.replace("{{email}}", mainContact.getEmail());
                    message = message.replace("{{password}}", password);
                }
                System.out.println("Aqui");
                EnterpriseAccessRequestEntity enterpriseAccessRequestEntity = new EnterpriseAccessRequestEntity();
                enterpriseAccessRequestEntity.setStatus(enterpriseAccessRequestDTO.getStatus());
                enterpriseAccessRequestEntity.setMessage(message);
                enterpriseAccessRequestEntity.setCreatedAt(new Date());
                enterpriseAccessRequestEntity = this.enterpriseAccessRequestRepository.save(enterpriseAccessRequestEntity);
                enterpriseEntity.addAccessRequest(enterpriseAccessRequestEntity);


                //Mailgun mailgun = new Mailgun();
                //mailgun.sendBasicEmail("Respuesta a solicitud de acceso - Tu estado industrial", mainContact.getEmail(), this.accessRequestEmailMessage(message));
                this.emailService.sendEmail(mainContact.getEmail(), "Respuesta a solicitud de acceso - Tu estado industrial", this.accessRequestEmailMessage(message));

            }

            return this.enterprisesRepository.save(enterpriseEntity);
        }
        throw new Exception("Esta empresa no existe");
    }


    // Addresses
    public HashMap<String, Object> deleteAllEnterpriseAddresses(Long enterpriseId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            for (DomicilioEntity domicilioEntity : enterpriseEntity.getDomicilios()) {
                this.domiciliosRepository.deleteById(domicilioEntity.getId());
            }
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Domicilios eliminados");
            return response;
        }
        throw new Exception("La empresa no existe");
    }


    // Contacts section
    public EnterpriseEntity createContact(Long enterpriseId, UserRequestDTO userRequestDTO) throws Exception {
        EnterpriseEntity enterprise = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterprise != null) {
            String newPassword = this.createRandomPassword();
            UserEntity contact = this.formResourcesUserMapper.dtoToEntity(userRequestDTO.getUser());
            ProfileEntity perfil = this.profileRepository.findProfileEntityByTipo(TipoPerfilEnum.EMPRESA.getTipo());
            if (!userRequestDTO.getUpdatePassword()) {
                contact.setPassword(this.passwordEncoder.encode(newPassword));
            }
            if (userRequestDTO.getUpdatePassword()) {
                if (userRequestDTO.getNewPassword() == null) {
                    throw new Exception("La nueva contraseña es requerida");
                }
                if (userRequestDTO.getRepeatPassword() == null) {
                    throw new Exception("La repetición de la contraseña es requerida");
                }
                if (userRequestDTO.getNewPassword().length() < 8) {
                    throw new Exception("La nueva contraseña debe contener mínimo 8 caracteres");
                }
                if (userRequestDTO.getRepeatPassword().length() < 8) {
                    throw new Exception("La repetición de la contraseña debe contener mínimo 8 caracteres");
                }
                if (!userRequestDTO.getNewPassword().equals(userRequestDTO.getRepeatPassword())) {
                    throw new Exception("Las contraseñas no coinciden, verifica la información");
                }
                newPassword = userRequestDTO.getNewPassword();
                contact.setPassword(this.passwordEncoder.encode(newPassword));
            }
            contact = this.usersRepository.save(contact);
            contact.addPerfil(perfil);
            contact = this.usersRepository.save(contact);

            enterprise.addContact(contact);
            enterprise = this.enterprisesRepository.save(enterprise);

            Mailgun mailgun = new Mailgun();
            mailgun.sendBasicEmail(
                    "Registro de nuevo contacto - Tu estado industrial",
                    contact.getEmail(),
                    this.registrationEmailContent(contact.getNombre(), contact.getEmail(), newPassword)
            );

            return enterprise;
        }
        throw new Exception("La empres ano existe");
    }

    public EnterpriseEntity updateContact(Long enterpriseId, Long contactId, UserRequestDTO userRequestDTO) throws Exception {
        EnterpriseEntity enterprise = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterprise != null) {
            UserEntity contact = this.usersRepository.findById(contactId).get();
            if (contact != null) {
                String newPassword = this.createRandomPassword();
                contact.setNombre(userRequestDTO.getUser().getNombre());
                contact.setEmail(userRequestDTO.getUser().getEmail());
                contact.setTelefono(userRequestDTO.getUser().getTelefono());
                if (userRequestDTO.getUpdatePassword()) {
                    if (userRequestDTO.getNewPassword() == null) {
                        throw new Exception("La nueva contraseña es requerida");
                    }
                    if (userRequestDTO.getRepeatPassword() == null) {
                        throw new Exception("La repetición de la contraseña es requerida");
                    }
                    if (userRequestDTO.getNewPassword().length() < 8) {
                        throw new Exception("La nueva contraseña debe contener mínimo 8 caracteres");
                    }
                    if (userRequestDTO.getRepeatPassword().length() < 8) {
                        throw new Exception("La repetición de la contraseña debe contener mínimo 8 caracteres");
                    }
                    if (!userRequestDTO.getNewPassword().equals(userRequestDTO.getRepeatPassword())) {
                        throw new Exception("Las contraseñas no coinciden, verifica la información");
                    }
                    newPassword = userRequestDTO.getNewPassword();
                    contact.setPassword(this.passwordEncoder.encode(newPassword));

                    Mailgun mailgun = new Mailgun();
                    mailgun.sendBasicEmail(
                            "Actualización de datos de contacto - Tu estado industrial",
                            contact.getEmail(),
                            this.registrationEmailContent(contact.getNombre(), contact.getEmail(), newPassword)
                    );
                }
                contact = this.usersRepository.save(contact);
                return enterprise;
            }
            throw new Exception("El contacto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public UserEntity updateContactStatus(Long enterpriseId, Long contactId, Boolean status) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            UserEntity userEntity = this.usersRepository.findById(contactId).get();
            if (userEntity != null) {
                return this.userService.updateStatusUser(contactId, status);
            }
            throw new Exception("El contacto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public UserEntity contactPasswordRecovery(Long enterpriseId, Long contactId, PasswordRecoveryRequest passwordRecoveryRequest) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            UserEntity userEntity = this.usersRepository.findById(contactId).get();
            if (userEntity != null) {
                return this.userService.passwordRecovery(passwordRecoveryRequest);
            }
            throw new Exception("El contacto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public HashMap<String, Object> deleteContact(Long enterpriseId, Long contactId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            UserEntity userEntity = this.usersRepository.findById(contactId).get();
            if (userEntity != null) {
                return this.userService.deleteUser(contactId);
            }
            throw new Exception("El contacto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public EnterpriseEntity changePrincipalContact(Long enterpriseId, EnterpriseList_ContactDTO contact) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            enterpriseEntity.setEmail(contact.getEmail());
            return this.enterprisesRepository.save(enterpriseEntity);
        }
        throw new Exception("La empresa no existe");
    }

    public HashMap<String, Object> deleteAllEnterpriseContacts(Long enterpriseId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            for (UserEntity user : enterpriseEntity.getContactos()) {
                this.usersRepository.deleteById(user.getId());
            }
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Contactos eliminados");
            return response;
        }
        throw new Exception("La empresa no existe");
    }


    // Enterprise trade image functions
    public EnterpriseTradeImageEntity getEnterpriseTradeImage(Long enterpriseId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.findById(enterpriseId);
        if (enterpriseEntity != null) {
            if (enterpriseEntity.getImagenEmpresarial().size() == 0) {
                EnterpriseTradeImageEntity enterpriseTradeImageEntity = this.createEmptyEnterpriseTradeImage();
                enterpriseEntity.addImagenEmpresarial(enterpriseTradeImageEntity);
                enterpriseEntity = this.enterprisesRepository.save(enterpriseEntity);
            }
            return enterpriseEntity.getImagenEmpresarial().get(0);
        }
        throw new Exception("La empresa no existe");
    }

    public EnterpriseTradeImageEntity createEmptyEnterpriseTradeImage() throws Exception {
        EnterpriseTradeImageEntity enterpriseTradeImageEntity = new EnterpriseTradeImageEntity();
        enterpriseTradeImageEntity.setEstatus(true);
        enterpriseTradeImageEntity.setCreatedAt(new Date());
        return this.enterpriseTradeImageRepository.save(enterpriseTradeImageEntity);
    }

    public EnterpriseTradeImageEntity saveEnterpriseTradeImage(
            Long enterpriseId,
            EnterpriseTradeImageEntity enterpriseTradeImageEntity,
            MultipartFile logotipo,
            MultipartFile video
    ) throws Exception {
        MinIO minio = new MinIO();
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            enterpriseTradeImageEntity = this.enterpriseTradeImageRepository.save(enterpriseTradeImageEntity);
            if (logotipo != null) {
                minio.updateObject(enterpriseTradeImageEntity.getLogotipoId().getUrl(), logotipo);
            }
            if (video != null) {
                minio.updateObject(enterpriseTradeImageEntity.getVideoId().getUrl(), video);
            }
            return enterpriseTradeImageEntity;
        }
        throw new Exception("La empresa no existe");
    }

    public HashMap<String, Object> deleteAllEnterpriseTradeImage(Long enterpriseId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            if (enterpriseEntity.getImagenEmpresarial().size() > 0) {
                MinIO minIO = new MinIO();
                for (EnterpriseTradeImageEntity enterpriseTradeImageEntity : enterpriseEntity.getImagenEmpresarial()) {
                    minIO.deleteObject(enterpriseTradeImageEntity.getLogotipoId().getUrl());
                    minIO.deleteObject(enterpriseTradeImageEntity.getVideoId().getUrl());
                    this.enterpriseTradeImageRepository.deleteById(enterpriseTradeImageEntity.getId());
                }
                HashMap<String, Object> response = new HashMap<>();
                response.put("message", "Imagen comercial eliminada");
            }
        }
        throw new Exception("La empresa no existe");

    }


    // Products
    public ProductEntity getProductos(Long enterpriseId, Long productId) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            ProductEntity productEntity = this.productRepository.findById(productId).get();
            if (productEntity != null) {
                return productEntity;
            }
            throw new Exception("El producto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public ProductEntity saveEnterpriseProduct(
            Long enterpriseId,
            EnterpriseProductsRequestDTO enterpriseProductsRequestDTO,
            MultipartFile technicalDocument,
            MultipartFile[] images,
            MultipartFile[] videos
    ) throws Exception {
        MinIO minio = new MinIO();
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            String needle = "{productoId}";

            ProductEntity product = this.productMapper.dtoToEntity(enterpriseProductsRequestDTO.getProduct());

            product.setFichaTecnica(new ArrayList<>());
            product.setImagenes(new ArrayList<>());
            product.setVideos(new ArrayList<>());

            product = this.productRepository.save(product);

            List<FileEntity> technicalDocumentDataset = this.filesMapper.listDtoToEntity(enterpriseProductsRequestDTO.getProductTechnicalDocument());
            List<FileEntity> imagesDataset = this.filesMapper.listDtoToEntity(enterpriseProductsRequestDTO.getProductImages());
            List<FileEntity> videosDataset = this.filesMapper.listDtoToEntity(enterpriseProductsRequestDTO.getProductVideos());
            if (technicalDocument != null) {
                for (FileEntity file : technicalDocumentDataset) {
                    file.setUrl(file.getUrl().replace(needle, Long.toString(product.getId())));
                    file.setCreatedAt(new Date());
                    product.addFichaTecnica(file);
                    minio.updateObject(file.getUrl(), technicalDocument);
                }
            }
            product = this.productRepository.save(product);
            if (images != null) {
                for (int i = 0; i < imagesDataset.size(); i++) {
                    FileEntity file = imagesDataset.get(i);
                    file.setUrl(file.getUrl().replace(needle, product.getId().toString()));
                    file.setUrl(file.getUrl().replace("{index}", Integer.toString(i + 1)));
                    file.setCreatedAt(new Date());
                    product.addImagen(file);
                    minio.updateObject(file.getUrl(), images[i]);
                }
            }
            product = this.productRepository.save(product);
            if (videos != null) {
                for (int i = 0; i < videosDataset.size(); i++) {
                    FileEntity file = videosDataset.get(i);
                    file.setUrl(file.getUrl().replace(needle, Long.toString(product.getId())));
                    file.setUrl(file.getUrl().replace("{index}", Integer.toString(i + 1)));
                    file.setCreatedAt(new Date());
                    product.addVideo(file);
                    minio.updateObject(file.getUrl(), images[i]);
                }
            }
            product = this.productRepository.save(product);
            enterpriseEntity.addProducto(product);
            enterpriseEntity = this.enterprisesRepository.save(enterpriseEntity);
            return product;
        }
        throw new Exception("La empresa no existe");
    }

    public ProductEntity updateEnterpriseProduct(
            Long enterpriseId,
            Long productId,
            EnterpriseProductsRequestDTO enterpriseProductsRequestDTO,
            MultipartFile technicalDocument,
            MultipartFile[] images,
            MultipartFile[] videos
    ) throws Exception {
        MinIO minio = new MinIO();
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            ProductEntity productEntity = this.productRepository.findById(productId).get();
            if (productEntity != null) {
                String needle = "{productoId}";
                ProductEntity product = this.productMapper.dtoToEntity(enterpriseProductsRequestDTO.getProduct());
                product = this.productRepository.save(product);

                List<FileEntity> technicalDocumentDataset = this.filesMapper.listDtoToEntity(enterpriseProductsRequestDTO.getProductTechnicalDocument());
                List<FileEntity> imagesDataset = this.filesMapper.listDtoToEntity(enterpriseProductsRequestDTO.getProductImages());
                List<FileEntity> videosDataset = this.filesMapper.listDtoToEntity(enterpriseProductsRequestDTO.getProductVideos());
                if (technicalDocument != null) {
                    for (FileEntity file : technicalDocumentDataset) {
                        if (file.getId() != null) {
                            file.setUpdatedAt(new Date());
                            this.filesRepository.save(file);
                        } else {
                            file.setUrl(file.getUrl().replace(needle, Long.toString(product.getId())));
                            file.setCreatedAt(new Date());
                            product.addFichaTecnica(file);
                        }
                        minio.updateObject(file.getUrl(), technicalDocument);
                    }
                }
                product = this.productRepository.save(product);
                if (images != null) {
                    int imagesNumber = product.getImagenes().size();
                    for (int i = 0; i < imagesDataset.size(); i++) {
                        FileEntity file = imagesDataset.get(i);
                        if (file.getId() != null) {
                            file.setUpdatedAt(new Date());
                            this.filesRepository.save(file);
                        } else {
                            file.setCreatedAt(new Date());
                            file.setUrl(file.getUrl().replace(needle, product.getId().toString()));
                            file.setUrl(file.getUrl().replace("{index}", Integer.toString(imagesNumber + i + 1)));
                            product.addImagen(file);
                        }
                        minio.updateObject(file.getUrl(), images[i]);
                    }
                }
                product = this.productRepository.save(product);
                if (videos != null) {
                    int videosNumber = product.getVideos().size();
                    for (int i = 0; i < videosDataset.size(); i++) {
                        FileEntity file = videosDataset.get(i);
                        if (file.getId() != null) {
                            file.setUpdatedAt(new Date());
                            this.filesRepository.save(file);
                        } else {
                            file.setCreatedAt(new Date());
                            file.setUrl(file.getUrl().replace(needle, Long.toString(product.getId())));
                            file.setUrl(file.getUrl().replace("{index}", Integer.toString(videosNumber + i + 1)));
                            product.addVideo(file);
                        }
                        minio.updateObject(file.getUrl(), videos[i]);
                    }
                }
                product = this.productRepository.save(product);
                return product;
            }
            throw new Exception("El producto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public HashMap<String, Object> deleteEnterpriseProduct(Long productId) throws Exception {
        ProductEntity productEntity = this.productRepository.findById(productId).get();
        if (productEntity != null) {
            MinIO minio = new MinIO();
            for (FileEntity technicalDocument : productEntity.getFichaTecnica()) {
                minio.deleteObject(technicalDocument.getUrl());
                this.filesRepository.deleteById(technicalDocument.getId());
            }
            for (FileEntity image : productEntity.getImagenes()) {
                minio.deleteObject(image.getUrl());
                this.filesRepository.deleteById(image.getId());
            }
            for (FileEntity video : productEntity.getVideos()) {
                minio.deleteObject(video.getUrl());
                this.filesRepository.deleteById(video.getId());
            }
            this.productRepository.deleteById(productId);
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Producto eliminado");
            return response;
        }
        throw new Exception("El producto no existe");
    }

    public HashMap<String, Object> deleteAllEnterpriseProducts(Long enterpriseID) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseID).get();
        if (enterpriseEntity != null) {
            MinIO minio = new MinIO();
            for (ProductEntity product : enterpriseEntity.getProductos()) {
                for (FileEntity technicalDocument : product.getFichaTecnica()) {
                    minio.deleteObject(technicalDocument.getUrl());
                    this.filesRepository.deleteById(technicalDocument.getId());
                }
                for (FileEntity image : product.getImagenes()) {
                    minio.deleteObject(image.getUrl());
                    this.filesRepository.deleteById(image.getId());
                }
                for (FileEntity video : product.getVideos()) {
                    minio.deleteObject(video.getUrl());
                    this.filesRepository.deleteById(video.getId());
                }
                this.productRepository.deleteById(product.getId());
            }
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Productos eliminados");
            return response;
        }
        throw new Exception("La empresa no existe");
    }


    // Product files section
    public ProductEntity updateProductFile(
            Long enterpriseId,
            Long productId,
            Long fileId,
            FileEntity fileData,
            MultipartFile file
    ) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            ProductEntity productEntity = this.productRepository.findById(productId).get();
            if (productEntity != null) {
                FileEntity fileEntity = this.filesRepository.findById(fileId).get();
                if (fileEntity != null) {
                    if (file != null) {
                        this.filesRepository.save(fileData);
                        MinIO minIO = new MinIO();
                        minIO.updateObject(fileData.getUrl(), file);
                        return productEntity;
                    }
                    throw new Exception("El archivo no fue cargado");
                }
                throw new Exception("El archivo no existe");
            }
            throw new Exception("El producto no existe");
        }
        throw new Exception("La empresa no existe");
    }

    public ProductEntity deleteProductFile(
            Long enterpriseId,
            Long productId,
            Long fileId
    ) throws Exception {
        EnterpriseEntity enterpriseEntity = this.enterprisesRepository.findById(enterpriseId).get();
        if (enterpriseEntity != null) {
            ProductEntity productEntity = this.productRepository.findById(productId).get();
            if (productEntity != null) {
                FileEntity fileEntity = this.filesRepository.findById(fileId).get();
                if (fileEntity != null) {
                    MinIO minio = new MinIO();
                    minio.deleteObject(fileEntity.getUrl());
                    this.filesRepository.deleteById(fileId);
                    return productEntity;
                }
                throw new Exception("El archivo no existe");
            }
            throw new Exception("El producto no existe");
        }
        throw new Exception("La empresa no existe");
    }


    // SCOPES
    private List<EnterpriseEntity> getAllActiveEnterprises() {
        return this.enterprisesRepository.findAllByEstatusTrue();
    }

    // END SCOPES


    // Private functions

    private String accessRequestEmailMessage(String message) {
        String htmlContent = "<div>\n" + message + "                <br>\n" + "            </div>";
        return htmlContent;
    }

    private String registrationEmailContent(String name, String email, String password) {
        String htmlContent = "<div>\n" +
                "                <p>\n" +
                "                    Hola <b><i>{{name}}</i></b>:\n" +
                "                    <br><br>\n" +
                "                    Estas son las credenciales para que inicies sesión en la plataforma <b>Veracruz, Tu estado industrial</b>:\n" +
                "                </p>\n" +
                "                <h2>Correo electrónico: <b>{{email}}</b></h2>\n" +
                "                <br>\n" +
                "                <h2>Contraseña: <b>{{password}}</b></h2>\n" +
                "                <br><br>\n" +
                "                <p>Más adelante, si lo deseas puedes actualizar tu contraseña en tu sección <b><i>Mi perfil</i></b></p>" +
                "                <br>\n" +
                "            </div>";
        htmlContent = htmlContent.replace("{{name}}", name);
        htmlContent = htmlContent.replace("{{email}}", email);
        htmlContent = htmlContent.replace("{{password}}", password);

        return htmlContent;
    }

    // SCOPES
    public List<EnterpriseListUserFilterDTO> findAllByStatusTrueWithContacts() {
        return this.enterpriseListUserFilterMapper.listEntityToDTO(this.enterprisesRepository.findAllByEstatusTrue());
    }

    public EnterpriseListUserFilterDTO findByUserId(Long userId) throws Exception {
        EnterpriseEntity entity = this.enterprisesRepository.findByUserId(userId);
        if (entity != null) {
            return this.enterpriseListUserFilterMapper.entityToDTO(entity);
        }
        throw new Exception("Este empresario no pertenece a ninguna empresa");
    }

    public List<EnterpriseEntity> findByCorreo(String correo) {
        return this.enterprisesRepository.findEmpresaPorCorreo(correo);
    }

    public List<EnterpriseEntity> pageByEstatus(String estatus) {
        return this.enterprisesRepository.findAllByEstatus(estatus);
    }


    public List<Object[]> obtenerTotalPorSexo() throws Exception {
        return this.enterprisesRepository.obtenerTotalPorSexo();
    }

    public List<Object[]> obtenerTotalPorMunicipio() throws Exception {
        return this.enterprisesRepository.obtenerTotalPorMunicipio();
    }

    public List<Object[]> obtenerTotalPorsector() throws Exception {
        return this.enterprisesRepository.obtenerTotalPorSector();
    }

}
