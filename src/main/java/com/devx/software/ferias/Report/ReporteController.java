/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Report;

import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Entities.Tasks.TaskEntity;
import com.devx.software.ferias.Entities.Users.UserEntity;
import com.devx.software.ferias.Services.Minutas.MinutasService;
import com.devx.software.ferias.Services.Tasks.TasksService;
import com.devx.software.ferias.Services.Users.UserService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author blopez
 */

@RestController
@RequestMapping("/reporte")
public class ReporteController {
    
    private final MinutasService minutasService;
    private final UserService usuarioService;
    private final TasksService  tasksService;
    
  @Autowired
    public ReporteController(MinutasService minutasService,UserService usuarioService, TasksService tasksService ) {
        this.minutasService = minutasService;
        this.usuarioService = usuarioService;
        this.tasksService = tasksService;
    }
    
   
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/minuta/{id}", method = RequestMethod.GET)
    public void reportMinuta(HttpServletResponse response, @PathVariable long id) {

        try {
            MinutasEntity object = minutasService.findById(id);
            List<UserEntity> listparticipantes = usuarioService.findbyuserbyminuta(id);
            List<TaskEntity> listTreas = tasksService.findallTaskbyMinuta(id);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");


            Map<String, Object> params = new HashMap<>();

            // / LOS DATOS QUE VAN EN EL FIEL DE JASPERT, 
            params.put("logo", "report/logo.png");
            params.put("folio", object.getFolio());
            params.put("asunto", object.getAsunto());
            params.put("objetivo", object.getObjetivo());
            params.put("sede", object.getSede());
            params.put("puntos", object.getPuntosTratados());
            
            if (object.getProyecto() != null) {
                  params.put("solicitante", object.getProyecto().getEmpresaId().getEmpresa());
            }else{
                  params.put("solicitante", "Sin Solicitante/Proyecto");
            }
          
            params.put("fecha", sdf.format(object.getCreatedAt().getTime()));
            params.put("hora",  sdfHora.format(object.getCreatedAt().getTime()));
            params.put("consecutivo", object.getFolio());

            // DATOS QUE MANDO EN LA TABLA RESPONSALE
            List<Map<String, Object>> participantesData = new ArrayList<>();
            for (UserEntity participante : listparticipantes) {
                Map<String, Object> participanteMap = new HashMap<>();
                participanteMap.put("nombre", participante.getNombre());
                participanteMap.put("email", participante.getEmail());
                participantesData.add(participanteMap);
            }
            JRBeanCollectionDataSource source2 = new JRBeanCollectionDataSource(participantesData);
            params.put("source2", source2);

            // DATOS QUE MANDO EN LA TABLA TAREAS
            List<Map<String, Object>> datostarea = new ArrayList<>();
            for (TaskEntity tarea : listTreas) {
                Map<String, Object> tareamap = new HashMap<>();
                tareamap.put("descripcion", tarea.getTarea());
                tareamap.put("responsable", tarea.getUsuarioId().getNombre());
                tareamap.put("fCompromiso", sdf.format(tarea.getFechaTermino().getTime()));
                datostarea.add(tareamap);
            }
            JRBeanCollectionDataSource source3 = new JRBeanCollectionDataSource(datostarea);
            params.put("source3", source3);

            List<Map<String, Object>> hashMapList = new ArrayList<>();
            hashMapList.add(params);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(hashMapList);

            InputStream jasperStream = this.getClass().getResourceAsStream("/report/minuta.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=minuta.pdf");
            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        } catch (IOException | JRException e) {
            System.out.println(e.getMessage());
        }
    }
    

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/minutas", method = RequestMethod.POST)
    public void reportMinutas(HttpServletResponse response, @RequestBody List<Long> ids) {
        try {
            List<JasperPrint> jasperPrintList = new ArrayList<>();

            for (Long id : ids) {
                MinutasEntity minuta = minutasService.findById(id);

                Map<String, Object> params = new HashMap<>(); // Crea un nuevo mapa para cada iteración

                List<UserEntity> listparticipantes = usuarioService.findbyuserbyminuta(minuta.getId());
                List<TaskEntity> listTreas = tasksService.findallTaskbyMinuta(minuta.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

                // LOS DATOS QUE VAN EN EL FIEL DE JASPERT
                params.put("logo", "report/logo.png");
                params.put("folio", minuta.getFolio());
                params.put("asunto", minuta.getAsunto());
                params.put("objetivo", minuta.getObjetivo());
                params.put("sede", minuta.getSede());
                params.put("puntos", minuta.getPuntosTratados());
                
                if (minuta.getProyecto() != null) {
                    params.put("solicitante", minuta.getProyecto().getEmpresaId().getEmpresa());
                } else {
                    params.put("solicitante", "Sin Solicitante/Proyecto");
                }

                params.put("fecha", sdf.format(minuta.getCreatedAt().getTime()));
                params.put("hora", sdfHora.format(minuta.getCreatedAt().getTime()));
                params.put("consecutivo", minuta.getFolio());

                // DATOS QUE MANDO EN LA TABLA RESPONSALE DEL JASPERT
                List<Map<String, Object>> participantesData = new ArrayList<>();
                for (UserEntity participante : listparticipantes) {
                    Map<String, Object> participanteMap = new HashMap<>();
                    participanteMap.put("nombre", participante.getNombre());
                    participanteMap.put("email", participante.getEmail());
                    participantesData.add(participanteMap);
                }
                JRBeanCollectionDataSource source2 = new JRBeanCollectionDataSource(participantesData);
                params.put("source2", source2);

                // DATOS QUE MANDO EN LA TABLA TAREAS DEL JASPERT
                List<Map<String, Object>> datostarea = new ArrayList<>();
                for (TaskEntity tarea : listTreas) {
                    Map<String, Object> tareamap = new HashMap<>();
                    tareamap.put("descripcion", tarea.getTarea());
                    tareamap.put("responsable", tarea.getUsuarioId().getNombre());
                    tareamap.put("fCompromiso", sdf.format(tarea.getFechaTermino().getTime()));
                    datostarea.add(tareamap);
                }
                JRBeanCollectionDataSource source3 = new JRBeanCollectionDataSource(datostarea);
                params.put("source3", source3);

                JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(Collections.singletonList(params));

                InputStream jasperStream = this.getClass().getResourceAsStream("/report/minuta.jasper");
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);

                jasperPrintList.add(jasperPrint);
            }

            // Combina los JasperPrints utilizando JRPdfExporter
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
            exporter.exportReport();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=minutas.pdf");
        } catch (IOException | JRException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/minutas/zip", method = RequestMethod.POST)
    public void reportMinutasZip(HttpServletResponse response, @RequestBody List<Long> ids) {
        try {
            List<JasperPrint> jasperPrintList = new ArrayList<>();
            List<String> pdfFileNames = new ArrayList<>();

            for (Long id : ids) {
                // ... (tu código actual para generar un PDF)
                MinutasEntity minuta = minutasService.findById(id);

                Map<String, Object> params = new HashMap<>(); // Crea un nuevo mapa para cada iteración

                List<UserEntity> listparticipantes = usuarioService.findbyuserbyminuta(minuta.getId());
                List<TaskEntity> listTreas = tasksService.findallTaskbyMinuta(minuta.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");

                // LOS DATOS QUE VAN EN EL FIEL DE JASPERT
                params.put("logo", "report/logo.png");
                params.put("folio", minuta.getFolio());
                params.put("asunto", minuta.getAsunto());
                params.put("objetivo", minuta.getObjetivo());
                params.put("sede", minuta.getSede());
                params.put("puntos", minuta.getPuntosTratados());
                
                if (minuta.getProyecto() != null) {
                    params.put("solicitante", minuta.getProyecto().getEmpresaId().getEmpresa());
                } else {
                    params.put("solicitante", "Sin Solicitante/Proyecto");
                }

                params.put("fecha", sdf.format(minuta.getCreatedAt().getTime()));
                params.put("hora", sdfHora.format(minuta.getCreatedAt().getTime()));
                params.put("consecutivo", minuta.getFolio());

                // DATOS QUE MANDO EN LA TABLA RESPONSALE DEL JASPERT
                List<Map<String, Object>> participantesData = new ArrayList<>();
                for (UserEntity participante : listparticipantes) {
                    Map<String, Object> participanteMap = new HashMap<>();
                    participanteMap.put("nombre", participante.getNombre());
                    participanteMap.put("email", participante.getEmail());
                    participantesData.add(participanteMap);
                }
                JRBeanCollectionDataSource source2 = new JRBeanCollectionDataSource(participantesData);
                params.put("source2", source2);

                // DATOS QUE MANDO EN LA TABLA TAREAS DEL JASPERT
                List<Map<String, Object>> datostarea = new ArrayList<>();
                for (TaskEntity tarea : listTreas) {
                    Map<String, Object> tareamap = new HashMap<>();
                    tareamap.put("descripcion", tarea.getTarea());
                    tareamap.put("responsable", tarea.getUsuarioId().getNombre());
                    tareamap.put("fCompromiso", sdf.format(tarea.getFechaTermino().getTime()));
                    datostarea.add(tareamap);
                }
                JRBeanCollectionDataSource source3 = new JRBeanCollectionDataSource(datostarea);
                params.put("source3", source3);

                JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(Collections.singletonList(params));

                InputStream jasperStream = this.getClass().getResourceAsStream("/report/minuta.jasper");
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);

                // Añadir el JasperPrint y el nombre del archivo PDF a la lista
                jasperPrintList.add(jasperPrint);
                pdfFileNames.add("minuta_" + id + ".pdf");
            }

            // Crear archivo ZIP
            ByteArrayOutputStream zipByteArrayStream = new ByteArrayOutputStream();
            // Agregar cada archivo PDF al ZIP
            try ( ZipOutputStream zipOutputStream = new ZipOutputStream(zipByteArrayStream)) {
                // Agregar cada archivo PDF al ZIP
                for (int i = 0; i < jasperPrintList.size(); i++) {
                    JasperPrint jasperPrint = jasperPrintList.get(i);
                    String pdfFileName = pdfFileNames.get(i);

                    JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);

                    // Agregar el archivo al ZIP
                    ZipEntry zipEntry = new ZipEntry(pdfFileName);
                    zipOutputStream.putNextEntry(zipEntry);

                    try ( FileInputStream pdfInputStream = new FileInputStream(pdfFileName)) {
                        IOUtils.copy(pdfInputStream, zipOutputStream);
                    }

                    zipOutputStream.closeEntry();
                    new File(pdfFileName).delete(); // Eliminar el archivo PDF después de agregarlo al ZIP
                }

                zipOutputStream.finish();
            }

            // Configurar la respuesta para el archivo ZIP
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=minutas.zip");

            // Enviar el archivo ZIP al cliente
            OutputStream outputStream = response.getOutputStream();
            zipByteArrayStream.writeTo(outputStream);
            outputStream.flush();

        } catch (IOException | JRException e) {
            System.out.println(e.getMessage());
        }
    }

}
