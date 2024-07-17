package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Auth.PasswordRecoveryRequest;
import com.devx.software.ferias.DTOs.Enterprises.*;
import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.DTOs.Users.UserDTO;
import com.devx.software.ferias.DTOs.Users.UserRequestDTO;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseTradeImageEntity;
import com.devx.software.ferias.Entities.Enterprises.ProductEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Mappers.Enterprises.EnterpriseListMapper;
import com.devx.software.ferias.Mappers.Enterprises.EnterpriseTradeImageMapper;
import com.devx.software.ferias.Mappers.Enterprises.FormResourceEnterpriseMapper;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.Services.Enterprises.EnterprisesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/enterprises")
@PreAuthorize("isAuthenticated()")
public class EnterprisesController {

    private final HttpHeaders headers = new HttpHeaders();
    private final CatalogsService catalogsService;
    private final EnterprisesService enterprisesService;
    private final EnterpriseListMapper enterpriseListMapper;
    private final EnterpriseTradeImageMapper enterpriseTradeImageMapper;
    private final FormResourceEnterpriseMapper formResourceEnterpriseMapper;

    @Autowired
    public EnterprisesController(
            EnterprisesService enterprisesService,
            CatalogsService catalogsService,
            EnterpriseListMapper enterpriseListMapper,
            EnterpriseTradeImageMapper enterpriseTradeImageMapper,
            FormResourceEnterpriseMapper formResourceEnterpriseMapper
    ) {
        this.catalogsService = catalogsService;
        this.enterprisesService = enterprisesService;
        this.enterpriseListMapper = enterpriseListMapper;
        this.enterpriseTradeImageMapper = enterpriseTradeImageMapper;
        this.formResourceEnterpriseMapper = formResourceEnterpriseMapper;
    }

    @GetMapping("/withFilter")
    public ResponseEntity<List<EnterpriseListUserFilterDTO>> getAllEnterprises(
            @RequestParam(required = false) String estatus,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String regimenFiscal,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String subsector
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity(
                    this.enterprisesService.getAllWithFilter(
                            estatus,
                            categoria,
                            regimenFiscal,
                            sector,
                            subsector
                    ),
                    this.headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"", "/", "/all"}, method = RequestMethod.GET)
    public ResponseEntity<List<EnterpriseListDTO>> getAll() {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity(
                    this.enterpriseListMapper.listEntityToDto(this.enterprisesService.getAll()),
                    this.headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/filterByTopic/{topic}")
    public ResponseEntity<List<EnterpriseListUserFilterDTO>> getAllEnterprisesByTopic(@PathVariable String topic) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity(
                    this.enterprisesService.getAllByTopic(topic),
                    this.headers,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<PaginationResponse> pageEnterprises(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String regimenFiscal,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String subsector,
            @RequestParam(required = false) String estatus,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable pegeable = PageRequest.of(page, size);
            Page<EnterpriseEntity> pageDataset = this.enterprisesService.pageEnterprises(
                    pegeable,
                    nombre,
                    regimenFiscal,
                    categoria,
                    sector,
                    subsector,
                    estatus
            );
            PaginationResponse response = new PaginationResponse();
            response.setDataset(this.enterpriseListMapper.listEntityToDto(pageDataset.getContent()));
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> findById(@PathVariable Long enterpriseId) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity(this.enterprisesService.findById(enterpriseId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{enterpriseId}/formResources")
    public ResponseEntity<HashMap<String, Object>> formResources(
            @PathVariable(required = false) Long enterpriseId
    ) {
        try {
            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            FormResourceEnterpriseDTO enterpriseDTO = null;
            if (enterpriseId > 0) {
                enterpriseDTO = this.formResourceEnterpriseMapper.entityToDTO(this.enterprisesService.findById(enterpriseId));
            }
            formResources.put("empresa", enterpriseDTO);
            formResources.put("categorias", this.catalogsService.getAllCatalogosByTipoCatalogo("TIPO_EMPRESA"));
            formResources.put("regimenesFiscales", this.catalogsService.getAllCatalogosByTipoCatalogo("REGIMEN_FISCAL"));
            formResources.put("sectores", this.catalogsService.getAllCatalogosByTipoCatalogo("SECTORES"));
            formResources.put("subsectores", this.catalogsService.getAllCatalogosByTipoCatalogo("SUBSECTORES"));
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<EnterpriseEntity> createEnterprise(@RequestBody FormResourceEnterpriseDTO formResourceEnterpriseDTO) {
        try {
            this.headers.set("200", "Registro exitoso");
            return new ResponseEntity(this.enterprisesService.create(formResourceEnterpriseDTO), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Este correo ya se encuentra registrado!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> updateEnterprise(@PathVariable Long enterpriseId, @RequestBody FormResourceEnterpriseDTO formResourceEnterpriseDTO) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity(this.enterprisesService.update(enterpriseId, formResourceEnterpriseDTO), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @PutMapping("/autorizado/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> updateEnterpriseautorizado(@PathVariable Long enterpriseId, @RequestBody FormResourceEnterpriseDTO formResourceEnterpriseDTO) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity(this.enterprisesService.updateautorizado(enterpriseId, formResourceEnterpriseDTO), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
      @PutMapping("/desautorizado/{enterpriseId}")
    public ResponseEntity<EnterpriseEntity> updateEnterprisedesautorizado(@PathVariable Long enterpriseId, @RequestBody FormResourceEnterpriseDTO formResourceEnterpriseDTO) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity(this.enterprisesService.updateDesautorizado(enterpriseId, formResourceEnterpriseDTO), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
  
   
      @CrossOrigin(origins = "*")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<List<EnterpriseEntity>> findAllAreas() {
        return ResponseEntity.status(HttpStatus.OK).body(enterprisesService.getAll());
    }

    @DeleteMapping("/{enterpriseId}")
    public ResponseEntity<HashMap<String, Object>> deleteEnterprise(@PathVariable Long enterpriseId) {
        try {
            this.headers.set("200", "Borrado exitoso");
            return new ResponseEntity(this.enterprisesService.delete(enterpriseId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enterpriseId}/status/{status}")
    public ResponseEntity<EnterpriseListDTO> updateStatusUser(@PathVariable Long enterpriseId, @PathVariable Boolean status) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterpriseListMapper.entityToDTO(this.enterprisesService.updateStatusEnterprise(enterpriseId, status)), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enterpriseId}/access-request")
    public ResponseEntity<EnterpriseListDTO> saveAccessRequest(@PathVariable Long enterpriseId, @RequestBody EnterpriseAccessRequestDTO enterpriseAccessRequestDTO) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterpriseListMapper.entityToDTO(this.enterprisesService.saveAccessRequest(enterpriseId, enterpriseAccessRequestDTO)), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Contacts section
    @PostMapping("/{enterpriseId}/contacts")
    public ResponseEntity<EnterpriseListDTO> createContact(@PathVariable Long enterpriseId, @RequestBody UserRequestDTO userRequestDTO) {
        try {
            this.headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(this.enterpriseListMapper.entityToDTO(this.enterprisesService.createContact(enterpriseId, userRequestDTO)), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enterpriseId}/contacts/{contactId}")
    public ResponseEntity<EnterpriseListDTO> updateContact(@PathVariable Long enterpriseId, @PathVariable Long contactId, @RequestBody UserRequestDTO userRequestDTO) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterpriseListMapper.entityToDTO(this.enterprisesService.updateContact(enterpriseId, contactId, userRequestDTO)), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enterpriseId}/contacts/{contactId}/status/{status}")
    public ResponseEntity<UserEntity> updateContactStatus(@PathVariable Long enterpriseId, @PathVariable Long contactId, @PathVariable Boolean status) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterprisesService.updateContactStatus(enterpriseId, contactId, status), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{enterpriseId}/contacts/{contactId}/password-recovery")
    public ResponseEntity<UserEntity> passwordRecovery(@PathVariable Long enterpriseId, @PathVariable Long contactId, @RequestBody PasswordRecoveryRequest passwordRecoveryRequest) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterprisesService.contactPasswordRecovery(enterpriseId, contactId, passwordRecoveryRequest), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{enterpriseId}/contacts/{contactId}")
    public ResponseEntity<HashMap<String, Object>> deleteContact(@PathVariable Long enterpriseId, @PathVariable Long contactId) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterprisesService.deleteContact(enterpriseId, contactId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{enterpriseId}/contacts/change-principal")
    public ResponseEntity<EnterpriseEntity> changePrincipalContact(@PathVariable Long enterpriseId, @RequestBody EnterpriseList_ContactDTO contact) {
        try {
            this.headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(this.enterprisesService.changePrincipalContact(enterpriseId, contact), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Enterprise trade image section
    @GetMapping("/{enterpriseId}/trade-image")
    public ResponseEntity<EnterpriseTradeImageEntity> getEnterpriseTradeImage(@PathVariable Long enterpriseId) {
        try {
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.enterprisesService.getEnterpriseTradeImage(enterpriseId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/{enterpriseId}/trade-image", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<EnterpriseTradeImageEntity> saveEnterpriseTradeImage(
            @PathVariable Long enterpriseId,
            @RequestPart(name = "tradeImageEnterprise") String tradeImageEnterprise,
            @RequestPart(name = "logotipo", required = false) MultipartFile logotipo,
            @RequestPart(name = "video", required = false) MultipartFile video
    ) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EnterpriseTradeImageEntity enterpriseTradeImageEntity = objectMapper.readValue(tradeImageEnterprise, EnterpriseTradeImageEntity.class);
            this.headers.set("200", "Guardado exitoso");
            return new ResponseEntity<>(this.enterprisesService.saveEnterpriseTradeImage(
                    enterpriseId,
                    enterpriseTradeImageEntity,
                    logotipo,
                    video
            ), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Products
    @RequestMapping(path = "/{enterpriseId}/products", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductEntity> saveEnterpriseProduct(
            @PathVariable Long enterpriseId,
            @RequestParam(name = "enterpriseProduct") String enterpriseProduct,
            @RequestParam(name = "technicalDocument", required = false) MultipartFile technicalDocument,
            @RequestParam(name = "images", required = false) MultipartFile[] images,
            @RequestParam(name = "videos", required = false) MultipartFile[] videos
    ) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EnterpriseProductsRequestDTO enterpriseProductsRequestDTO = objectMapper.readValue(enterpriseProduct, EnterpriseProductsRequestDTO.class);
            this.headers.set("200", "Guardado exitoso");
            return new ResponseEntity<>(this.enterprisesService.saveEnterpriseProduct(
                    enterpriseId,
                    enterpriseProductsRequestDTO,
                    technicalDocument,
                    images,
                    videos
            ), this.headers, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(path = "/{enterpriseId}/products/{productId}", method = RequestMethod.PATCH, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductEntity> updateEnterpriseProduct(
            @PathVariable Long enterpriseId,
            @PathVariable Long productId,
            @RequestParam(name = "enterpriseProduct") String enterpriseProduct,
            @RequestParam(name = "technicalDocument", required = false) MultipartFile technicalDocument,
            @RequestParam(name = "images", required = false) MultipartFile[] images,
            @RequestParam(name = "videos", required = false) MultipartFile[] videos
    ) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EnterpriseProductsRequestDTO enterpriseProductsRequestDTO = objectMapper.readValue(enterpriseProduct, EnterpriseProductsRequestDTO.class);
            this.headers.set("200", "Guardado exitoso");
            return new ResponseEntity<>(this.enterprisesService.updateEnterpriseProduct(
                    enterpriseId,
                    productId,
                    enterpriseProductsRequestDTO,
                    technicalDocument,
                    images,
                    videos
            ), this.headers, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{enterpriseId}/products/{productId}")
    public ResponseEntity<HashMap<String, Object>> deleteProduct(@PathVariable Long enterpriseId, @PathVariable Long productId) {
        try {
            this.headers.set("200", "Borrado exitoso");
            return new ResponseEntity(this.enterprisesService.deleteEnterpriseProduct(productId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Product files
    @RequestMapping(path = "/{enterpriseId}/products/{productId}/files/{fileId}", method = RequestMethod.PATCH, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductEntity> updateEnterpriseFile(
            @PathVariable Long enterpriseId,
            @PathVariable Long productId,
            @PathVariable Long fileId,
            @RequestParam String fileProduct,
            @RequestParam(name = "file") MultipartFile file
    ) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileEntity fileEntity = objectMapper.readValue(fileProduct, FileEntity.class);
            this.headers.set("200", "Guardado exitoso");
            return new ResponseEntity<>(this.enterprisesService.updateProductFile(
                    enterpriseId,
                    productId,
                    fileId,
                    fileEntity,
                    file
            ), this.headers, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{enterpriseId}/products/{productId}/files/{fileId}")
    public ResponseEntity<ProductEntity> deleteProductFile(
            @PathVariable Long enterpriseId,
            @PathVariable Long productId,
            @PathVariable Long fileId
    ) {
        try {
            this.headers.set("200", "Borrado exitoso");
            return new ResponseEntity<>(this.enterprisesService.deleteProductFile(enterpriseId, productId, fileId), this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // SCOPES
    @GetMapping("/getActiveEnterprisesWithContacts")
    public ResponseEntity<List<EnterpriseListUserFilterDTO>> findAllByStatusTrueWithContacts(){
        try{
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(this.enterprisesService.findAllByStatusTrueWithContacts(), this.headers, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getEnterpriseByUserId/{userId}")
    public ResponseEntity<EnterpriseListUserFilterDTO> findEnterpriseByUserId(@PathVariable Long userId){
        try{
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(
                    this.enterprisesService.findByUserId(userId),
                    this.headers,
                    HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reportesolicitudes")
    public ResponseEntity<HashMap<String, Object>> reporteSolicitudes() {
        HttpHeaders headers = new HttpHeaders();
        try {

            this.headers.set("200", "Consulta exitosa");
            HashMap<String, Object> formResources = new HashMap<>();
            formResources.put("sexo", this.enterprisesService.obtenerTotalPorSexo());
            formResources.put("municipio", this.enterprisesService.obtenerTotalPorMunicipio());
            formResources.put("sector", this.enterprisesService.obtenerTotalPorsector());
            //formResources.put("subsectores", this.catalogsService.getAllCatalogosByTipoCatalogo("SUBSECTORES"));
            return new ResponseEntity<>(formResources, this.headers, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/all-empresas", method = RequestMethod.GET)
    public ResponseEntity<List<EnterpriseEntity>> findAllArchivo() {
        return ResponseEntity.status(HttpStatus.OK).body(enterprisesService.getallempresas());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/evento", method = RequestMethod.GET)
    public ResponseEntity<?> getList(@RequestParam("eventoIds") List<Long> eventoIds) {
        return ResponseEntity.status(HttpStatus.OK).body(this.enterprisesService.getallempresasByEvento(eventoIds));
    }
}
