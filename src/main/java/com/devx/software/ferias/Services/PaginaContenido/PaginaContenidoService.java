package com.devx.software.ferias.Services.PaginaContenido;

import com.devx.software.ferias.Entities.PaginaContenido.PaginaContenidoEntity;
import com.devx.software.ferias.Repositories.PaginaContenido.PaginaContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaginaContenidoService {

    private final PaginaContenidoRepository repository;

    @Autowired
    public PaginaContenidoService(PaginaContenidoRepository repository) {
        this.repository = repository;
    }

    public PaginaContenidoEntity findPaginaContenidoByPagina(String pagina) {
        return repository.findPaginaContenidoByPagina(pagina);
    }


    public PaginaContenidoEntity update(PaginaContenidoEntity model) {
        return repository.save(model);
    }
}
