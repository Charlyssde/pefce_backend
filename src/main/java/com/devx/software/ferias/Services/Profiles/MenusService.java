package com.devx.software.ferias.Services.Profiles;

import com.devx.software.ferias.Entities.Profiles.MenuEntity;
import com.devx.software.ferias.Repositories.Profiles.MenusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenusService {

    private final MenusRepository menusRepository;

    @Autowired
    public MenusService(
            MenusRepository menusRepository
    ) {
        this.menusRepository = menusRepository;
    }

    public MenuEntity create(MenuEntity entity) throws Exception {
        try {
            return menusRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<MenuEntity> getMainMenus() {
        return this.menusRepository.findAllByPadreNullOrderByNombreAsc();
    }

    public List<MenuEntity> page() {
        return menusRepository.findAll();
    }

    public MenuEntity findById(Long id) {
        return menusRepository.findMenuById(id);
    }

    public List<MenuEntity> findNoRegistrados(Long idPerfil) {
        return menusRepository.findMenusNoRegistrados(idPerfil);
    }

    public List<MenuEntity> findNoRegistrados(Long idPerfilPadre, Long idPerfil) {
        return menusRepository.findMenusNoRegistrados(idPerfilPadre, idPerfil);
    }

    public MenuEntity update(MenuEntity entity) {
        return menusRepository.save(entity);
    }

    public void deleteById(Long id) {
        menusRepository.deleteById(id);
    }
}
