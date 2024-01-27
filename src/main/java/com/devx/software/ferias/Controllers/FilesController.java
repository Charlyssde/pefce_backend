package com.devx.software.ferias.Controllers;

import com.devx.software.ferias.Misc.MinIO;
import com.devx.software.ferias.DTOs.Files.MultimediaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.minio.errors.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/files")
public class FilesController {
    // Funciones MinIO
    private final MinIO minio = new MinIO();

    @GetMapping("/findBucket")
    public ResponseEntity<String> findBucket() {
        HttpHeaders headers = new HttpHeaders();

        try {
            String response = minio.findBucket();
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getManyUrl")
    public ResponseEntity<List<MultimediaDTO>> getManyUrl(@RequestBody String jsonData) throws ErrorResponseException {
        HttpHeaders headers = new HttpHeaders();
        try {
            MinIO minio = new MinIO();
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> response = mapper.readValue(jsonData, HashMap.class);
            Object jsonF = response.get("jsonData");
            MultimediaDTO[] files = mapper.convertValue(jsonF, MultimediaDTO[].class);

            for (MultimediaDTO i : files) {
                String url = minio.getUrl(i.getPath());
                i.setPath(url);
            }

            return new ResponseEntity(files, headers, HttpStatus.OK);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getUrl")
    public void getUrl(@RequestParam String pathfile, HttpServletResponse response) throws Exception {
        if (pathfile != null) {
            InputStream stream = minio.getObjectByFileName(pathfile);
            if (stream != null) {
                InputStreamResource inputStreamResource = new InputStreamResource(stream);
                response.addHeader("Content-disposition", "attachment;filename=" + pathfile);
                response.setContentType(URLConnection.guessContentTypeFromName(pathfile));
                IOUtils.copy(stream, response.getOutputStream());
                response.flushBuffer();
            }
        }

//        HttpHeaders headers = new HttpHeaders();
//        try{
//            String url = minio.getUrl(pathfile);
//            InputStreamResource file = new InputStreamResource(new FileInputStream(url));
//
//            headers.set("200", "Consulta exitosa");
//            return ResponseEntity.ok()
//                    .headers(headers)
//                    .body(file);
//
//        }
//        catch(ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @PostMapping("/uploadObject")
    public ResponseEntity<String> uploadObject(@RequestParam(name = "file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String response = minio.uploadObject(file);
            return new ResponseEntity(response, headers, HttpStatus.OK);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/uploadObjectWithOriginalName")
    public ResponseEntity<String> uploadObjectWithOriginalName(@RequestParam(name = "file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String response = minio.uploadObjectWithOriginalName(file);
            return new ResponseEntity(response, headers, HttpStatus.OK);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateObject")
    public ResponseEntity<String> updateObjectPost(@RequestParam String pathfile, @RequestParam(name = "file") MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        try {
            String response = minio.updateObject(pathfile, file);
            headers.add("responseType", "text");
            return new ResponseEntity(response, headers, HttpStatus.OK);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteObject")
    public ResponseEntity<String> deleteObject(@RequestParam String pathfile) {
        HttpHeaders headers = new HttpHeaders();
        try {
            minio.deleteObject(pathfile);
            headers.add("responseType", "text");
            return new ResponseEntity("Objeto eliminado", headers, HttpStatus.OK);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidResponseException | ServerException | XmlParserException | IOException | IllegalArgumentException | InvalidKeyException | NoSuchAlgorithmException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-object-by-filename")
    public void getFile(@RequestParam String fileName, HttpServletResponse response) throws Exception {
        InputStream stream = minio.getObjectByFileName(fileName);
        InputStreamResource inputStreamResource = new InputStreamResource(stream);
        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setContentType(URLConnection.guessContentTypeFromName(fileName));
        IOUtils.copy(stream, response.getOutputStream());
        response.flushBuffer();
    }
}


