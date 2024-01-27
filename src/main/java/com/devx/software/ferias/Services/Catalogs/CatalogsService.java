package com.devx.software.ferias.Services.Catalogs;

import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Repositories.Catalogs.CatalogsRepository;
import com.devx.software.ferias.DTOs.Catalogs.CatalogsDTO;
import com.devx.software.ferias.DTOs.Catalogs.EstadoMunicipioDTO;
import com.devx.software.ferias.DTOs.Catalogs.MunicipioDTO;
import com.devx.software.ferias.Entities.Catalogs.CatalogsEntity;
import com.devx.software.ferias.Entities.Catalogs.EstadosEntity;
import com.devx.software.ferias.Entities.Catalogs.EstadosMunicipiosEntity;
import com.devx.software.ferias.Entities.Catalogs.MunicipiosEntity;
import com.devx.software.ferias.Mappers.Catalogs.CatalogosMapper;
import com.devx.software.ferias.Mappers.Catalogs.EstadoMunicipioMapper;
import com.devx.software.ferias.Mappers.Catalogs.MunicipioMapper;
import com.devx.software.ferias.Repositories.Catalogs.EstadosMunicipiosRepository;
import com.devx.software.ferias.Repositories.Catalogs.EstadosRepositoy;
import com.devx.software.ferias.Repositories.Catalogs.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogsService {

    private final EstadosRepositoy estadosRepositoy;

    private final MunicipioRepository municipioRepository;

    private final EstadosMunicipiosRepository estadosMunicipiosRepository;

    private final EstadoMunicipioMapper estadoMunicipioMapper;

    private final MunicipioMapper municipioMapper;

    private final CatalogsRepository catalogsRepository;

    private final CatalogosMapper catalogosMapper;

    @Autowired
    public CatalogsService(EstadosRepositoy estadosRepositoy, MunicipioRepository municipioRepository, EstadosMunicipiosRepository estadosMunicipiosRepository, EstadoMunicipioMapper estadoMunicipioMapper, MunicipioMapper municipioMapper, CatalogsRepository catalogsRepository, CatalogosMapper catalogosMapper) {
        this.estadosRepositoy = estadosRepositoy;
        this.municipioRepository = municipioRepository;
        this.estadosMunicipiosRepository = estadosMunicipiosRepository;
        this.estadoMunicipioMapper = estadoMunicipioMapper;
        this.municipioMapper = municipioMapper;
        this.catalogsRepository = catalogsRepository;
        this.catalogosMapper = catalogosMapper;
    }

    public Page<CatalogsEntity> pageCatalogoByTipo(String tipo, Pageable pageable, String nombre) throws Exception {
        if (nombre == null) {
            return this.catalogsRepository.findAllByTipoCatalogoAndActivoTrueOrderByNombreAsc(tipo, pageable);
        } else {
            return this.catalogsRepository.findAllByTipoCatalogoAndActivoTrueAndNombreContainsIgnoreCaseOrderByNombreAsc(tipo, nombre, pageable);
        }
    }

    public Page<CatalogsEntity> pageCatalogoByTipoAndPadre(String tipo, Pageable pageable, Long padreId, String nombre) throws Exception {
        if (nombre.equals("null")) {
            return this.catalogsRepository.findAllByTipoCatalogoAndActivoTrueAndIdCatalogoPadreOrderByNombreAsc(tipo, padreId, pageable);
        } else {
            return this.catalogsRepository.findAllByTipoCatalogoAndActivoTrueAndIdCatalogoPadreAndNombreContainsIgnoreCaseOrderByNombreAsc(tipo, padreId, nombre, pageable);
        }
    }

    public List<CatalogsEntity> getCatalogosByTipo(String tipoCatalogo) throws Exception {
        return this.catalogsRepository.findAllByTipoCatalogoAndActivoTrue(tipoCatalogo);
    }

    public List<CatalogsEntity> getCatalogosByTipoAndPadre(String tipoCatalogo, Long padre) throws Exception {
        return this.catalogsRepository.findAllByTipoCatalogoAndActivoTrueAndIdCatalogoPadre(tipoCatalogo, padre);
    }

    public CatalogsEntity updateOrCreate(CatalogsEntity CatalogsEntity) throws Exception {
        return this.catalogsRepository.save(CatalogsEntity);
        // Create if CatalogsEntity does not have ID

    }


    ////////////////////////////////////////////////////////

    public List<EstadosEntity> getAllEstados() {
        return estadosRepositoy.findAll();
    }

    public List<MunicipiosEntity> getAllMunicipios() {
        return municipioRepository.findAll();
    }

    public List<MunicipiosEntity> getAllMunicipiosByEstado(Long id) {
        List<EstadosMunicipiosEntity> estadosMunicipiosEntityList = estadosMunicipiosRepository.findAllByEstadoId_Id(id);
        List<EstadoMunicipioDTO> estadoMunicipioDTOList = estadoMunicipioMapper.listEntityToDTO(estadosMunicipiosEntityList);
        List<MunicipioDTO> municipioDTOList = new ArrayList<>();
        if (!estadoMunicipioDTOList.isEmpty()) {
            estadoMunicipioDTOList.forEach(estadoMunicipioDTO -> {
                municipioDTOList.add(estadoMunicipioDTO.getMunicipioId());
            });
        }
        return municipioMapper.listDTOToEntity(municipioDTOList);
    }

    public List<CatalogsEntity> findAllCatalogosByTipoCatalogo(String tipoCatalogo) {
        List<CatalogsEntity> catalogos = this.catalogsRepository.findAllByTipoCatalogoAndActivoTrue(tipoCatalogo);
        return catalogos;
    }

    public List<CatalogsDTO> getAllCatalogosByTipoCatalogo(String tipoCatalogo) {
        List<CatalogsEntity> CatalogsEntityList = this.catalogsRepository.findAllByTipoCatalogoAndActivoTrue(tipoCatalogo);
        List<CatalogsDTO> catalogosDTOList = new ArrayList<>();
        if (!CatalogsEntityList.isEmpty()) {
            catalogosDTOList = this.catalogosMapper.entitiesToDTOS(CatalogsEntityList);
        }
        return catalogosDTOList;
    }

    public CatalogsEntity getCatalogoByTipoAndNombre(String tipo, String nombre) {
        CatalogsEntity resp = this.catalogsRepository.findCatalogoByTipoCatalogoAndNombre(tipo, nombre);
        return resp;
    }

    public CatalogsEntity getCatalogByTipoAndNombre(String tipo, String nombre) {
        return this.catalogsRepository.findCatalogByTipoCatalogoAndNombre(tipo, nombre);
    }

    public List<CatalogsDTO> page() {
        List<CatalogsEntity> CatalogsEntityList = this.catalogsRepository.findAll();
        List<CatalogsDTO> catalogosDTOList = new ArrayList<>();
        if (!CatalogsEntityList.isEmpty()) {
            catalogosDTOList = this.catalogosMapper.entitiesToDTOS(CatalogsEntityList);
        }
        return catalogosDTOList;
    }


    public CatalogsEntity create(CatalogsEntity entity) throws Exception {
        try {
            return catalogsRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CatalogsEntity findById(Long id) {
        return catalogsRepository.findCatalogoById(id);
    }

    public CatalogsEntity update(CatalogsEntity entity) {
        return catalogsRepository.save(entity);
    }

    public void deleteById(Long id) {
        catalogsRepository.deleteById(id);
    }
}
