package com.anemona.aneback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anemona.aneback.dto.AlertaDTO;
import com.anemona.aneback.model.Alerta;
import com.anemona.aneback.model.Paciente;
import com.anemona.aneback.repository.AlertaRepository;
import com.anemona.aneback.repository.PacienteRepository;

@Service
public class AlertaServiceImpl implements AlertaService{
    
    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //alerta -> alertaDTO
    private AlertaDTO convertToDTO(Alerta alerta) {
        AlertaDTO dto = new AlertaDTO();
        dto.setId_alerta(alerta.getId_alerta());
        dto.setDescripcion_alerta(alerta.getDescripcion_alerta());
        dto.setNivel_alerta(alerta.getNivel_alerta());
        dto.setFecha_alerta(alerta.getFecha_alerta().toLocalDate());
        dto.setHora_alerta(alerta.getFecha_alerta().toLocalTime());
        dto.setId_paciente(alerta.getPaciente().getId_paciente());
        return dto;
    }

    @Override
    public AlertaDTO createAlerta(Long pacienteId, Alerta alerta) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        if (pacienteOptional.isEmpty()) {
            throw new RuntimeException("Paciente no encontrado");
        }
        Paciente paciente = pacienteOptional.get();
        alerta.setPaciente(paciente);
        Alerta savedAlerta = alertaRepository.save(alerta);
        return convertToDTO(savedAlerta);
    }
    
    @Override
    public AlertaDTO updateAlertaById(Long alertaId, Alerta updatedAlerta) {
        Alerta alerta = alertaRepository.findById(alertaId)
                .orElseThrow(() -> new RuntimeException("Alerta no encontrada con Id:" + alertaId));
        alerta.setDescripcion_alerta(updatedAlerta.getDescripcion_alerta());
        alerta.setNivel_alerta(updatedAlerta.getNivel_alerta());

        Alerta savedAlerta = alertaRepository.save(alerta);
        return convertToDTO(savedAlerta);
    }

    @Override
    public List<AlertaDTO> getAllAlertas() {
        return alertaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlertaDTO getAlertaById(Long alertaId) {
        Alerta alerta = alertaRepository.findById(alertaId)
            .orElseThrow(() -> new RuntimeException("Alerta no encontrada con Id: " + alertaId));
        return convertToDTO(alerta);
    }

    @Override
    public List<AlertaDTO> getAlertasByPacienteId(Long pacienteId){
        List<Alerta> alertas = alertaRepository.findByPacienteId(pacienteId);

        if (alertas.isEmpty()) {
            throw new RuntimeException("No se encontraron alertas para el paciente con ID: " + pacienteId);
        }

        return alertas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteAllAlertas() {
        alertaRepository.deleteAll();
    }

    @Override
    public void deleteAlertaById(Long alertaId) {
        if (alertaRepository.existsById(alertaId)) {
            alertaRepository.deleteById(alertaId);
        } else { 
            throw new RuntimeException("Alerta no encontrada con id: " + alertaId);
        }
    }

}
