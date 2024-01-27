package com.devx.software.ferias.Repositories.PaginaContenido;

import com.devx.software.ferias.Entities.PaginaContenido.PaginaContenidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaginaContenidoRepository extends JpaRepository<PaginaContenidoEntity, Long> {
    PaginaContenidoEntity findPaginaContenidoByPagina(String pagina);
}
