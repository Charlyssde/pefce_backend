/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devx.software.ferias.Report;

import com.devx.software.ferias.Entities.Minutas.MinutasEntity;
import com.devx.software.ferias.Services.Minutas.MinutasService;
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

  @Autowired
    public ReporteController(MinutasService minutasService) {
        this.minutasService = minutasService;
    }
    
   
    
@CrossOrigin(origins = "*")
@RequestMapping(value = "/minuta/{id}", method = RequestMethod.GET)
    public void reportMinuta(HttpServletResponse response, @PathVariable long id) {
     
         
        try {
         
            MinutasEntity object = minutasService.findById(id);

            Map<String, Object> params = new HashMap<>();
            params.put("logo", "report/logo.png");
  
            params.put("folio", object.getFolio());
            params.put("asunto", object.getAsunto());
            params.put("objetivo", object.getObjetivo());
            params.put("sede", object.getSede());
            
            
            List<Map<String, Object>> hashMapList = new ArrayList<>();
            hashMapList.add(params);
            JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(hashMapList);

            Map<String, Object> params2 = new HashMap<>();

            InputStream jasperStream = this.getClass().getResourceAsStream("/report/minuta.jasper");

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params2, source);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=minuta.pdf");
            final OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        } catch (IOException | JRException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
