package com.devx.software.ferias.Misc;

import com.devx.software.ferias.Config.MinIOConfig;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @author Epsom Enrique Segura Jaramillo
 * Gestión básica de objetos entre servidor MinIO y backend JAVA Spring Boot
 */
public class MinIO {
    private static final String BUCKET = "pefce";
    private final MinIOConfig configuration;
    private final MinioClient client;

    public MinIO() {
        this.configuration = new MinIOConfig();
        this.client = configuration.minioClient();
    }

    /**
     * Retorna una cadena de texto informando si existe el bucket establecido para la plataforma.
     *
     * @return String existencia de bucket
     * @throws ErrorResponseException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws XmlParserException
     */
    // Buscar bucket
    public String findBucket() throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        boolean found = this.client.bucketExists(BucketExistsArgs.builder().bucket(BUCKET).build());
        return (found) ? "El bucket existe" : "El bucket no existe";
    }

    /**
     * Obtiene la URL de un objeto almacenado en el bucket a partir de la ruta y nombre del objeto
     *
     * @param path
     * @return URL de acceso al objeto
     * @throws ErrorResponseException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws XmlParserException
     */
    // Obtener la URL de un objeto
    public String getUrl(String path) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        return this.client.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(BUCKET)
                .object(path).
                method(Method.GET)
                .expiry(2, TimeUnit.DAYS).build());
    }

    /**
     * Almacena un objeto en el bucket especificado
     *
     * @param file
     * @return String de la ruta del objeto recientemente almacenado
     * @throws ErrorResponseException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws XmlParserException
     */
    // Guardar un objeto en el bucket
    public String uploadObject(MultipartFile file) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        int idx = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String suffix = file.getOriginalFilename().substring(idx + 1);
        String fileName = UUID.randomUUID() + "." + suffix;

        this.client.putObject(PutObjectArgs.builder()
                .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                .object(fileName)
                .contentType(file.getContentType())
                .bucket(BUCKET)
                .build());

        return fileName;
    }

    public String uploadObjectWithOriginalName(MultipartFile file) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, IOException, InvalidResponseException, NoSuchAlgorithmException, ServerException, XmlParserException {
        this.client.putObject(PutObjectArgs.builder()
                .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                .object(file.getOriginalFilename())
                .contentType(file.getContentType())
                .bucket(BUCKET)
                .build());

        return file.getOriginalFilename();
    }

    /**
     * Actualiza un objeto existente en el bucket identificado a partir de la ruta de almacenamiento
     *
     * @param path
     * @param file
     * @return String de la ruta del objeto recientemente actualizado
     * @throws ErrorResponseException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws InvalidKeyException
     * @throws InvalidResponseException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws ServerException
     * @throws XmlParserException
     */
    // Actualizar un objeto existente en el bucket
    public String updateObject(String path, MultipartFile file) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        InputStream inputStream = new BufferedInputStream(file.getInputStream());

        this.client.putObject(
                PutObjectArgs.builder()
                        .stream(inputStream, file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                        .object(path.replace(" ", "_"))
                        .contentType(file.getContentType())
                        .bucket(BUCKET)
                        .build());

        return path;
    }

    public void deleteObject(String path) throws ErrorResponseException, InsufficientDataException, InternalException, InvalidKeyException, InvalidResponseException, IOException, NoSuchAlgorithmException, ServerException, XmlParserException {
        this.client.removeObject(RemoveObjectArgs.builder().bucket(BUCKET).object(path).build());
    }

    public InputStream getObjectByFileName(String filename) throws Exception {
        InputStream stream;
        try {
            stream = this.client.getObject(GetObjectArgs.builder()
                    .bucket(BUCKET)
                    .object(filename)
                    .build());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return stream;
    }


}
