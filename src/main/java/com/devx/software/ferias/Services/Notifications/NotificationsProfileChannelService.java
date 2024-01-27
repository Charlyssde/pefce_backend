package com.devx.software.ferias.Services.Notifications;

import com.devx.software.ferias.Repositories.Profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationsProfileChannelService {

    //private final CanalPerfilRepository canalPerfilRepository;

    private final ProfileRepository perfilRepository;

    @Autowired
    public NotificationsProfileChannelService(
            //CanalPerfilRepository canalPerfilRepository,
            ProfileRepository perfilRepository) {
        //this.canalPerfilRepository = canalPerfilRepository;
        this.perfilRepository = perfilRepository;
    }

//    public NotificationsProfileChannelEntity create(NotificationsProfileChannelEntity entity) throws Exception {
//        try {
//            return this.canalPerfilRepository.save(entity);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public List<NotificationsProfileChannelEntity> page() {
//        return this.canalPerfilRepository.findAll();
//    }
//
//    public NotificationsProfileChannelEntity findById(Long id) {
//        return this.canalPerfilRepository.findCanalPerfilById(id);
//    }
//
//    public NotificationsProfileChannelEntity update(NotificationsProfileChannelEntity entity) {
//        return this.canalPerfilRepository.save(entity);
//    }
//
//    public void deleteById(Long id) {
//        this.canalPerfilRepository.deleteById(id);
//    }
//
//    public NotificationsProfileChannelEntity findByCanalIdAndPerfilId(Long idCanal, Long idPerfil) {
//        return this.canalPerfilRepository.findCanalPerfilByCanalIdAndPerfilId(idCanal, idPerfil);
//    }
//
//    public void deleteByCanalIdAndPerfilId(Long idCanal, Long idPerfil) {
//        this.canalPerfilRepository.deleteCanalPerfilByCanalIdAndPerfilId(idCanal, idPerfil);
//    }
}
 