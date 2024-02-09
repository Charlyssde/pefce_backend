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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
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
            
            System.out.println("TAMANO DE LISTA TAREAS" +  listTreas.size());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            Map<String, Object> params = new HashMap<>();

            // / LOS DATOS QUE VAN EN EL FIEL DE JASPERT, 
            params.put("logo", "report/logo.png");
            params.put("folio", object.getFolio());
            params.put("asunto", object.getAsunto());
            params.put("objetivo", object.getObjetivo());
            params.put("sede", object.getSede());
            params.put("puntos", object.getPuntosTratados());
            
            params.put("fecha", sdf.format(object.getFecha().getTime()));
            params.put("hora", "------");
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
            
            List<Map<String, Object>> allParams = new ArrayList<>();
            List<MinutasEntity> listaMinuta = minutasService.findAllMinutasWhereIn(ids);
            
            for (MinutasEntity  obj: listaMinuta) {
                MinutasEntity object = minutasService.findById(obj.getId());
                List<UserEntity> listparticipantes = usuarioService.findbyuserbyminuta(obj.getId());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Map<String, Object> params = new HashMap<>();
                params.put("logo", "report/logo.png");
                params.put("folio", object.getFolio());
                params.put("asunto", object.getAsunto());
                params.put("objetivo", object.getObjetivo());
                params.put("sede", object.getSede());
                params.put("fecha", sdf.format(object.getFecha().getTime()));
                params.put("hora", "12:00 PM");
                params.put("consecutivo", object.getFolio());

                List<Map<String, Object>> participantesData = new ArrayList<>();
                for (UserEntity participante : listparticipantes) {
                    Map<String, Object> participanteMap = new HashMap<>();
                    participanteMap.put("nombre", participante.getNombre());
                    participanteMap.put("email", participante.getEmail());
                    participantesData.add(participanteMap);
                }
                JRBeanCollectionDataSource source2 = new JRBeanCollectionDataSource(participantesData);
                params.put("source2", source2);

                allParams.add(params);
            }

            InputStream jasperStream = this.getClass().getResourceAsStream("/report/minuta.jasper");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allParams);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=minutas.pdf");
            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
        } catch (IOException | JRException e) {
            System.out.println(e.getMessage());
        }
    }

    
}
