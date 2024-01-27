package com.devx.software.ferias.Services.Notifications;
//
//import com.devx.software.ferias.zx_entities.CanalUsuarioEntity;
//import com.devx.software.ferias.Entities.Users.UserEntity;
//import com.devx.software.ferias.zx_repositories.CanalUsuarioRepository;
//import com.devx.software.ferias.zx_repositories.UsuariosRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
public class NotificationsUserChannelService {
//
//    private final CanalUsuarioRepository canalUsuarioRepository;
//
//    private final UsuariosRepository usuariosRepository;
//
//    @Autowired
//    public NotificationsUserChannelService(CanalUsuarioRepository canalUsuarioRepository, UsuariosRepository usuariosRepository) {
//        this.canalUsuarioRepository = canalUsuarioRepository;
//        this.usuariosRepository = usuariosRepository;
//    }
//
//    public CanalUsuarioEntity create(CanalUsuarioEntity entity) throws Exception {
//        try {
//            return this.canalUsuarioRepository.save(entity);
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public List<CanalUsuarioEntity> page() {
//        return this.canalUsuarioRepository.findAll();
//    }
//
//    public CanalUsuarioEntity findById(Long id) {
//        return this.canalUsuarioRepository.findCanalUsuarioById(id);
//    }
//
//    public CanalUsuarioEntity update(CanalUsuarioEntity entity) {
//        return this.canalUsuarioRepository.save(entity);
//    }
//
//    public void deleteById(Long id) {
//        this.canalUsuarioRepository.deleteById(id);
//    }
//
//    public List<UserEntity> findUsuariosByCanalId(Long id) {
//        return this.usuariosRepository.findUsuariosByCanalId(id);
//    }
//
//    public List<UserEntity> findUsuariosByCanalIdNull(Long id) {
//        return this.usuariosRepository.findUsuariosByCanalIdNull(id);
//    }
//
//    public CanalUsuarioEntity findByCanalIdAndUsuarioId(Long idCanal, Long idUsuario) {
//        return this.canalUsuarioRepository.findCanalUsuarioByCanalIdAndPerfilId(idCanal, idUsuario);
//    }
//
//    public void deleteByCanalIdAndUsuarioId(Long idCanal, Long idUsuario) {
//        this.canalUsuarioRepository.deleteCanalUsuarioByCanalIdAndPerfilId(idCanal, idUsuario);
//    }
}
