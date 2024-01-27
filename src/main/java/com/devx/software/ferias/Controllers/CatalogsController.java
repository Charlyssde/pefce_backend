package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Services.Catalogs.CatalogsService;
import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Entities.Catalogs.EstadosEntity;
import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogos")
@PreAuthorize("permitAll()")
public class CatalogsController {

    private final CatalogsService catalogsService;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    public CatalogsController(CatalogsService catalogsService) {
        this.catalogsService = catalogsService;
    }

    @GetMapping("/{tipoCatalogo}/page")
    public ResponseEntity<PaginationResponse> pageCatalogosByTipoCatalogo(
            @PathVariable String tipoCatalogo,
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<CatalogsEntity> pageDataset = this.catalogsService.pageCatalogoByTipo(tipoCatalogo, paging, nombre);
            PaginationResponse response = new PaginationResponse();
            response.setDataset(pageDataset.getContent());
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{tipoCatalogo}/page/{padre}")
    public ResponseEntity<PaginationResponse> pageCatalogosByTipoCatalogoAndPadre(
            @PathVariable String tipoCatalogo,
            @PathVariable Long padre,
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<CatalogsEntity> pageDataset = this.catalogsService.pageCatalogoByTipoAndPadre(tipoCatalogo, paging, padre, nombre);
            PaginationResponse response = new PaginationResponse();
            response.setDataset(pageDataset.getContent());
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            this.headers.set("200", "Consulta exitosa");

            return new ResponseEntity<>(response, this.headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{tipoCatalogo}/all")
    public ResponseEntity<List<CatalogsEntity>> getCatalogsEntityByTipoCatalogo(@PathVariable String tipoCatalogo) {
        try {
            List<CatalogsEntity> response = this.catalogsService.getCatalogosByTipo(tipoCatalogo);
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{tipoCatalogo}/all/{padre}")
    public ResponseEntity<List<CatalogsEntity>> getCatalogsEntityByTipoCatalogoAndPadre(
            @PathVariable String tipoCatalogo,
            @PathVariable Long padre
    ) {
        try {
            List<CatalogsEntity> response = this.catalogsService.getCatalogosByTipoAndPadre(tipoCatalogo, padre);
            this.headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<CatalogsEntity> saveCatalogo(@RequestBody CatalogsEntity CatalogsEntity) {
        try {
            CatalogsEntity response = this.catalogsService.updateOrCreate(CatalogsEntity);
            this.headers.set("200", (CatalogsEntity.getId() != null ? "Edición exitosa" : "Registro exitoso"));
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////7
    @GetMapping("/estados")
    public ResponseEntity<List<EstadosEntity>> getAllEstados() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<EstadosEntity> allEstados = catalogsService.getAllEstados();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(allEstados, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/municipios")
    public ResponseEntity<List<MunicipiosEntity>> getAllMunicipios() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<MunicipiosEntity> allMunicipios = catalogsService.getAllMunicipios();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(allMunicipios, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/municipiosEstado/{id}")
    public ResponseEntity<List<MunicipiosEntity>> getAllMunicipiosById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<MunicipiosEntity> allMunicipiosEstado = catalogsService.getAllMunicipiosByEstado(id);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(allMunicipiosEstado, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<CatalogsDTO>> page() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CatalogsDTO> catalogosDTOList = this.catalogsService.page();
            return new ResponseEntity<>(catalogosDTOList, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/findByTipoCatalogo/{tipoCatalogo}")
    public ResponseEntity<List<CatalogsDTO>> getAllCatalogosByTipoCatalogo(@PathVariable String tipoCatalogo) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<CatalogsDTO> catalogosDTOList = this.catalogsService.getAllCatalogosByTipoCatalogo(tipoCatalogo);
            return new ResponseEntity<>(catalogosDTOList, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CatalogsEntity> create(@RequestBody CatalogsEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CatalogsEntity responseData = catalogsService.create(entity);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<CatalogsEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CatalogsEntity responseData = catalogsService.findById(id);
            if (responseData != null) {
                headers.set("200", "Consulta exitosa");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CatalogsEntity> update(@RequestBody CatalogsEntity entity) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CatalogsEntity responseData = catalogsService.update(entity);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<CatalogsEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            CatalogsEntity responseData = catalogsService.findById(id);
            if (responseData != null) {
                catalogsService.deleteById(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(responseData, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
