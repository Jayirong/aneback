package com.anemona.aneback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anemona.aneback.dto.AlertaDTO;
import com.anemona.aneback.model.Alerta;
import com.anemona.aneback.service.AlertaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/alertas")
public class AlertaController {
    
    @Autowired
    private AlertaService alertaService;

    @PostMapping("/ingreso/{pacienteId}")
    public ResponseEntity<?> addAlerta(@PathVariable Long pacienteId ,@RequestBody Alerta alerta) {
        try {
            AlertaDTO createdAlerta = alertaService.createAlerta(pacienteId, alerta);
            return new ResponseEntity<>(createdAlerta, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlertaDTO>> getAllAlertas() {
        List<AlertaDTO> alertasDTO = alertaService.getAllAlertas();
        return new ResponseEntity<>(alertasDTO, HttpStatus.OK);
    }

    @GetMapping("/{alertaId}")
    public ResponseEntity<?> getAlertaById(@PathVariable Long alertaId) {
        try {
            AlertaDTO alertaDTO = alertaService.getAlertaById(alertaId);
            return new ResponseEntity<>(alertaDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> getAlertasByPaciente(@PathVariable Long pacienteId) {
        try {
            List<AlertaDTO> alertas = alertaService.getAlertasByPacienteId(pacienteId);
            return new ResponseEntity<>(alertas, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("update/{alertaId}")
    public ResponseEntity<?> updateAlerta(@PathVariable Long alertaId, @RequestBody Alerta updatedAlerta) {
        try {
            AlertaDTO alertaDTO = alertaService.updateAlertaById(alertaId, updatedAlerta);
            return new ResponseEntity<>(alertaDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{alertaId}")
    public ResponseEntity<?> deleteAlertaById(@PathVariable Long alertaId) {
        try {
            alertaService.deleteAlertaById(alertaId);
            return new ResponseEntity<>("Alerta eliminada con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllAlertas() {
        try {
            alertaService.deleteAllAlertas();
            return new ResponseEntity<>("Todas las alertas eliminadas exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
