/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Services.MinutaArchivo;

import com.devx.software.ferias.Config.MinIOConfig;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Entities.Minutas.MinutaArchivoEntity;
import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Repositories.Files.FilesRepository;
import com.devx.software.ferias.Repositories.Minutas.MinutaArchivoRepository;
import com.devx.software.ferias.Repositories.Minutas.MinutasRepository;
import com.devx.software.ferias.Repositories.Users.UsersRepository;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author blopez
 */

@Service
@Transactional
public class MinutaArchivoService2Impl implements MinutaArchivoService2{

   private final MinIOConfig minIOConfig;
   
   private final UsersRepository usersRepository;
   private final MinutaArchivoRepository minutaArchivoRepository;
   private final FilesRepository filesRepository;
   private final MinutasRepository minutasRepository;
   
   
    private static final String bucketName = "pefce";
    
  @Autowired
    public MinutaArchivoService2Impl(
    MinIOConfig minIOConfig,
    UsersRepository usersRepository,
    MinutaArchivoRepository minutaArchivoRepository,
    FilesRepository filesRepository,
    MinutasRepository minutasRepository
    ) {
        this.minIOConfig = minIOConfig;
        this.usersRepository = usersRepository; 
        this.minutaArchivoRepository = minutaArchivoRepository;
        this.filesRepository = filesRepository;
        this.minutasRepository = minutasRepository;
    }
    
    

    @Override
    public MinutaArchivoEntity save(MultipartFile file, long idminuta, long idusuario) {

        //ENCONTRAMO LA MINUTA PARA ACTUALIZARLA Y SETEARLE LOS ARCHIVOS A LA LISTA QUE NOS PIDE
        MinutasEntity minuta = this.minutasRepository.findMinutaById(idminuta);

        //ENCONTRAMOS EL USUARIO RESPONSABLE
        UserEntity user = this.usersRepository.findUserById(idusuario);

        Date date = new Date();
        String nombrepdf = date.getTime() + "-" + file.getOriginalFilename();

        //DESPUES SE GUARDA LOS DATOS EN LA TABLA a_minuta__a_archivo
        MinutaArchivoEntity savem = new MinutaArchivoEntity();
        
       
        savem.setMinuta(minuta);
        savem.setArchivo(file.getOriginalFilename());
        savem.setNombre(nombrepdf);
        savem.setCreatedAt(new Date());
        savem.setUpdatedAt(new Date());
        savem.setResponsable(user);
        savem.setUrl("http://localhost:9000/pefce/minutas/" + nombrepdf);
        savem.setTipo(file.getContentType());
        savem.setTamaño(file.getSize());
        this.minutaArchivoRepository.save(savem);

        //POSTERIORMENTE SE GUARDA EL PDF EN MINIO
        try {
            String objectName = "minutas/" + nombrepdf;
            minIOConfig.minioClient()
                    .putObject(PutObjectArgs.builder()
                            .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                            .object(objectName)
                            .contentType(file.getContentType())
                            .bucket(bucketName)
                            .build());

        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException ex) {
            ex.getMessage();
            ex.getStackTrace();
        }

        return null;
    }

    @Override
    public MinutaArchivoEntity findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MinutaArchivoEntity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MinutaArchivoEntity delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InputStream getUrl(String nombre) {
         InputStream stream = null;
         String objectName = "minutas/" + nombre;
        try {
            stream =  minIOConfig.minioClient().getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
          e.getMessage();
        }
        return stream;     
    }

    @Override
    public List<MinutaArchivoEntity> findAllByMinutaId(long id) {
        return this.minutaArchivoRepository.findMinutaArchivoByIdlist(id);
    }
    
}
