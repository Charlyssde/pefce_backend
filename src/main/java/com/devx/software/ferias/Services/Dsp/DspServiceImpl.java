package com.devx.software.ferias.Services.Dsp;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devx.software.ferias.Entities.DSP.DSP;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Mail.EmailService;
import com.devx.software.ferias.Misc.MinIO;
import com.devx.software.ferias.Repositories.Dsp.DspRepository;

@Service
public class DspServiceImpl implements DspService {
    
    private final DspRepository dspRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    public DspServiceImpl(DspRepository dspRepository) {
        this.dspRepository = dspRepository;
    }

    public DSP create(DSP entity, FileEntity file,
            MultipartFile archivo) {
        if (file == null) {
            return null;
        }
        DSP newEntity = dspRepository.save(entity);
        try {
            MinIO minio = new MinIO();
            String needle = "MIID";

            FileEntity fileo = file;
            fileo.setCreatedAt(new Date());
            fileo.setUrl(file.getUrl().replace(needle, Long.toString(newEntity.getId())));
            minio.updateObject(file.getUrl(), archivo);
            
            newEntity.setFile(fileo);
           newEntity = dspRepository.save(newEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newEntity;
    }

    public List<DSP> getAll(){
        return dspRepository.findAll();
    }

    public DSP getById(Long id){
        return dspRepository.getOne(id);
    }

    public DSP update(DSP entity, FileEntity file,
    MultipartFile archivo){
       try{
        if(file != null){
            MinIO minio = new MinIO();
            FileEntity fileo = file;
            fileo.setUpdatedAt(new Date());
            minio.updateObject(file.getUrl(), archivo);
            entity.setFile(fileo);
        }
       }catch(Exception e){
        e.printStackTrace();
       }
        return dspRepository.save(entity);
    }

    public void delete(Long id){
        dspRepository.deleteById(id);
    }

}
