package com.devx.software.ferias.DTOs.Encuestas;

import com.devx.software.ferias.DTOs.Files.FileDTO;

import java.util.List;

public class EncuestaRequestDTO {

    EncuestaDTO encuesta;

    List<FileDTO> archivo;

    public EncuestaDTO getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(EncuestaDTO encuesta) {
        this.encuesta = encuesta;
    }

    public List<FileDTO> getArchivo() {
        return archivo;
    }

    public void setArchivo(List<FileDTO> archivo) {
        this.archivo = archivo;
    }
}
