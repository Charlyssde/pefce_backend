package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Idiomas.IdiomasEntity;
import com.devx.software.ferias.Services.Idiomas.IdiomasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/idiomas")
public class IdiomasController {
    private final IdiomasService idiomasService;

    @Autowired
    public IdiomasController(IdiomasService idiomasService) {
        this.idiomasService = idiomasService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<IdiomasEntity>> read() {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<IdiomasEntity> response = idiomasService.read();
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<PaginationResponse> page(
            @RequestParam(required = false) String nombre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<IdiomasEntity> pageDataset = idiomasService.page(paging, nombre);
            PaginationResponse response = new PaginationResponse();
            response.setDataset(pageDataset.getContent());
            response.setCurrentPage(pageDataset.getNumber());
            response.setTotalItems(pageDataset.getTotalElements());
            response.setTotalPages(pageDataset.getTotalPages());
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<IdiomasEntity> findById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            IdiomasEntity resp = idiomasService.findById(id);
            headers.set("200", "Consulta exitosa");
            return new ResponseEntity<>(resp, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<IdiomasEntity> create(@RequestBody IdiomasEntity model) {
        HttpHeaders headers = new HttpHeaders();
        try {
            IdiomasEntity resp = idiomasService.create(model);
            headers.set("200", "Registro exitoso");
            return new ResponseEntity<>(resp, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<IdiomasEntity> update(@RequestBody IdiomasEntity model) {
        HttpHeaders headers = new HttpHeaders();
        try {
            IdiomasEntity resp = idiomasService.update(model);
            headers.set("200", "Actualización exitosa");
            return new ResponseEntity<>(resp, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<IdiomasEntity> delete(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        try {
            IdiomasEntity resp = idiomasService.findById(id);
            if (resp != null) {
                idiomasService.delete(id);
                headers.set("200", "Borrado exitoso");
                return new ResponseEntity<>(resp, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity("No se encontró información", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
