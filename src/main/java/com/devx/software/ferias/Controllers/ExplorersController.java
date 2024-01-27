package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.DTOs.Shared.PaginationResponse;
import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Mappers.Enterprises.EnterpriseListMapper;
import com.devx.software.ferias.Services.Explorers.ExplorersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explorers")
public class ExplorersController {

    private final HttpHeaders headers = new HttpHeaders();
    private final ExplorersService explorersService;
    private final EnterpriseListMapper enterpriseListMapper;

    public ExplorersController(
            ExplorersService explorersService,
            EnterpriseListMapper enterpriseListMapper
    ) {
        this.explorersService = explorersService;
        this.enterpriseListMapper = enterpriseListMapper;
    }

    @GetMapping("/enterprises/page")
    public ResponseEntity<PaginationResponse> pageEnterprises(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String regimenFiscal,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) String subsector,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size
    ) {
        try {
            Pageable pegeable = PageRequest.of(page, size);
            Page<EnterpriseEntity> pageDataset = this.explorersService.pageEnterprisesExplorer(
                    pegeable,
                    nombre,
                    regimenFiscal,
                    categoria,
                    sector,
                    subsector
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

}
