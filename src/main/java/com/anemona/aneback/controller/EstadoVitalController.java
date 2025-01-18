package com.anemona.aneback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anemona.aneback.dto.EstadoVitalDTO;
import com.anemona.aneback.model.EstadoVital;
import com.anemona.aneback.service.EstadoVitalService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/estadoVitales")
public class EstadoVitalController {
    
    @Autowired
    private EstadoVitalService estadoVitalService;

    @PostMapping("ingreso/{pacienteId}")
    public ResponseEntity<?> addEstadoVital(@PathVariable Long pacienteId, @RequestBody EstadoVital estadoVital) {
        try {
            EstadoVitalDTO createdEstadoVital = estadoVitalService.createEstadoVital(pacienteId, estadoVital);
            return new ResponseEntity<>(createdEstadoVital, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error",e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EstadoVitalDTO>> getAllEstadoVitales() {
        List<EstadoVitalDTO> estadoVitalDTO = estadoVitalService.getAllEstadoVitales();
        return new ResponseEntity<>(estadoVitalDTO, HttpStatus.OK);
    }

    @GetMapping("/{estadoVitalId}")
    public ResponseEntity<?> getEstadoVitalById(@PathVariable Long estadoVitalId) {
        try {
            EstadoVitalDTO estadoVitalDTO = estadoVitalService.getEstadoVitalById(estadoVitalId);
            return new ResponseEntity<>(estadoVitalDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> getEstadoVitalesByPaciente(@PathVariable Long pacienteId) {
        try {
            List<EstadoVitalDTO> estadoVitales = estadoVitalService.getEstadoVitalesByPacienteId(pacienteId);
            return new ResponseEntity<>(estadoVitales, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{estadoVitalId}")
    public ResponseEntity<?> updateEstadoVital(@PathVariable Long estadoVitalId, @RequestBody EstadoVital updatedEstadoVital) {
        try {
            EstadoVitalDTO estadoVitalDTO = estadoVitalService.updateEstadoVitalById(estadoVitalId, updatedEstadoVital);
            return new ResponseEntity<>(estadoVitalDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{estadoVitalId}")
    public ResponseEntity<?> deleteEstadoVitalById(@PathVariable Long estadoVitalId) {
        try {
            estadoVitalService.deleteEstadoVitalById(estadoVitalId);
            return new ResponseEntity<>("Estado vital eliminado exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllEstadoVitales() {
        try {
            estadoVitalService.deleteAllEstadoVitales();
            return new ResponseEntity<>("Todos los estados vitales han sido eliminados exitosamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
        

}
