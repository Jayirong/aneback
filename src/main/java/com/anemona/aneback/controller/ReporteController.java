package com.anemona.aneback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anemona.aneback.dto.ReporteDTO;
import com.anemona.aneback.model.Reporte;
import com.anemona.aneback.service.ReporteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/{reporteId}")
    public ResponseEntity<?> getReporteById(@PathVariable Long reporteId) {
        try {
            ReporteDTO reporte = reporteService.getReporteById(reporteId);
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReporteDTO>> getAllReportes() {
        List<ReporteDTO> reportes = reporteService.getAllReportes();
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    @PostMapping("/ingreso/{pacienteId}")
    public ResponseEntity<?> addReporte(@PathVariable Long pacienteId, @RequestBody Reporte reporte) {
        try {
            ReporteDTO createdReporte = reporteService.createReporte(pacienteId, reporte);
            return new ResponseEntity<>(createdReporte, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{reporteId}")
    public ResponseEntity<?> deleteReporteById(@PathVariable Long reporteId) {
        try {
            reporteService.deleteReporteById(reporteId);
            return new ResponseEntity<>("Reporte quemado con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllReportes(){
        try {
            reporteService.deleteAllReportes();
            return new ResponseEntity<>("Todos los reportes quemados con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("update/{reporteId}")
    public ResponseEntity<?> updateReporte(@PathVariable Long reporteId, @RequestBody Reporte updatedReporte) {
        try {
            ReporteDTO reporte = reporteService.updateReporteById(reporteId, updatedReporte);
            return new ResponseEntity<>(reporte, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    
    

}
