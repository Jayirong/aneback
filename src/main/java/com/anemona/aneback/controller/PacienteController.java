package com.anemona.aneback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anemona.aneback.model.Paciente;
import com.anemona.aneback.service.PacienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/ingreso")
    public ResponseEntity<?> addPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente createdPaciente = pacienteService.createPaciente(paciente);
            return new ResponseEntity<>(createdPaciente, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.getAllPacientes();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<?> getPacienteById(@PathVariable Long pacienteId) {
        try{
            Paciente paciente = pacienteService.getPacienteById(pacienteId);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<?> deletePacienteById(@PathVariable Long pacienteId) {
        try { 
            pacienteService.deletePacienteById(pacienteId);
            return new ResponseEntity<>("Paciente matado con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update/{pacienteId}")
    public ResponseEntity<?> updatePaciente (@PathVariable Long pacienteId, @RequestBody Paciente updatedPaciente) {
        try {
            Paciente paciente = pacienteService.updatePacienteById(pacienteId, updatedPaciente);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllPacientes(){
        try {
            pacienteService.deleteAllPacientes();
            return new ResponseEntity<>("Todos los pacientes matados con éxito", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
