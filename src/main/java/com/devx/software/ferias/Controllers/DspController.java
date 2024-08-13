package com.devx.software.ferias.Controllers;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devx.software.ferias.Entities.DSP.DSP;
import com.devx.software.ferias.Entities.Events.EventEntity;
import com.devx.software.ferias.Entities.Files.FileEntity;
import com.devx.software.ferias.Services.Dsp.DspService;
import com.devx.software.ferias.Services.Dsp.DspServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/dsp")
public class DspController {

    private final DspServiceImpl dspService;

    @Autowired
    public DspController(DspServiceImpl dspService) {
        this.dspService = dspService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DSP>> getAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("200", "Consulta exitosa");
        return new ResponseEntity<>(this.dspService.getAll(), headers, HttpStatus.SC_OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DSP> get(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        System.out.println("Data->" + id);
        headers.set("200", "Consulta exitosa");
        return new ResponseEntity<>(dspService.getById(id), headers, HttpStatus.SC_OK);
    }

    @PostMapping("/")
    public ResponseEntity<DSP> get(
        @RequestParam(name = "dsp") String dspString,
        @RequestParam(name = "file") String fileString,
        @RequestParam(name = "archivo") MultipartFile archivo) {

            HttpHeaders headers = new HttpHeaders();
    headers.set("200", "Consulta exitosa");

    try{
        ObjectMapper objectMapper = new ObjectMapper();
        DSP dsp = objectMapper.readValue(dspString, DSP.class) ;
        FileEntity file = objectMapper.readValue(fileString, FileEntity.class) ;

    return new ResponseEntity<>(dspService.create(dsp, file, archivo), headers, HttpStatus.SC_OK);
    }catch(Exception e){
        System.out.println(e.getMessage());
        return new ResponseEntity(e.getMessage(), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

    @PutMapping("/")
    public ResponseEntity<DSP> update(
            @RequestParam(name = "dsp") String dspString,
            @RequestParam(name = "file") String fileString,
            @RequestParam(name = "archivo") MultipartFile archivo) {

                HttpHeaders headers = new HttpHeaders();
        headers.set("200", "Consulta exitosa");

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            DSP dsp = objectMapper.readValue(dspString, DSP.class) ;
            FileEntity file = objectMapper.readValue(fileString, FileEntity.class) ;

        return new ResponseEntity<>(dspService.update(dsp, file, archivo), headers, HttpStatus.SC_OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(e.getMessage(), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.set("200", "Consulta exitosa");
        dspService.delete(id);
        return new ResponseEntity<>("{\"message\" : \"ok\"}", headers, HttpStatus.SC_OK);
    }

}
