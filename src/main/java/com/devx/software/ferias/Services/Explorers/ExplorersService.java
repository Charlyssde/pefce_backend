package com.devx.software.ferias.Services.Explorers;

import com.devx.software.ferias.Entities.Enterprises.EnterpriseEntity;
import com.devx.software.ferias.Repositories.Enterprises.EnterprisesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExplorersService {

    private final EnterprisesRepository enterprisesRepository;

    public ExplorersService(
            EnterprisesRepository enterprisesRepository
    ) {
        this.enterprisesRepository = enterprisesRepository;
    }

    public Page<EnterpriseEntity> pageEnterprisesExplorer(
            Pageable pageable,
            String nombre,
            String regimenFiscal,
            String categoria,
            String sector,
            String subsector
    ) {
        if (nombre != null || regimenFiscal != null || categoria != null || sector != null || subsector != null) {
            return this.enterprisesRepository.findAllApprovedByOrderByEmpresaDescAndMiscFilters(
                    pageable,
                    nombre,
                    regimenFiscal,
                    categoria,
                    sector,
                    subsector
            );
        }
        return this.enterprisesRepository.findAllByAutorizadoIsTrueAndPabellonAprobadoIsTrueOrderByEmpresaDesc(pageable);
    }
}
