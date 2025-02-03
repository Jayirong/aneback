package com.anemona.aneback.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anemona.aneback.dto.ParametrosVitalesDTO;
import com.anemona.aneback.model.ParametrosVitales;
import com.anemona.aneback.service.ParametrosVitalesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/parametros-vitales")
public class ParametrosVitalesController {
    
    @Autowired
    private ParametrosVitalesService parametrosService;

    @PostMapping
    public ResponseEntity<?> createParametros(@RequestBody ParametrosVitales parametros) {
        try {
            ParametrosVitalesDTO created = parametrosService.createParametros(parametros);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<?> getParametrosActivos() {
        try {
            ParametrosVitalesDTO parametros = parametrosService.getParametrosActivos();
            return new ResponseEntity<>(parametros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/{parametroId}")
    public ResponseEntity<?> getParametrosById(@PathVariable Long parametroId) {
        try {
            ParametrosVitalesDTO parametros = parametrosService.getParametrosById(parametroId);
            return new ResponseEntity<>(parametros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    

    @GetMapping()
    public ResponseEntity<List<ParametrosVitalesDTO>> getAllParametros() {
        List<ParametrosVitalesDTO> parametros = parametrosService.getAllParametros();
        return new ResponseEntity<>(parametros, HttpStatus.OK);
    }

    @PutMapping("/{parametroId}")
    public ResponseEntity<?> updateParametros(@PathVariable Long parametroId, @RequestBody ParametrosVitales parametros) {
        try {
            ParametrosVitalesDTO updated = parametrosService.updateParametros(parametroId, parametros);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{parametroId}")
    public ResponseEntity<?> deleteParametros(@PathVariable Long parametroId) {
        try {
            parametrosService.deleteParametros(parametroId);
            return new ResponseEntity<>("Parametros eliminados exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
    
}
