package com.anemona.aneback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anemona.aneback.dto.EstadoVitalDTO;
import com.anemona.aneback.model.EstadoVital;
import com.anemona.aneback.model.Paciente;
import com.anemona.aneback.repository.EstadoVitalRepository;
import com.anemona.aneback.repository.PacienteRepository;

@Service
public class EstadoVitalServiceImpl implements EstadoVitalService {
    
    @Autowired
    private EstadoVitalRepository estadoVitalRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private EstadoVitalDTO convertToDTO(EstadoVital estadoVital) {
        EstadoVitalDTO dto = new EstadoVitalDTO();
        dto.setId_estado(estadoVital.getId_estado());
        dto.setFrecuencia_cardiaca(estadoVital.getFrecuencia_cardiaca());
        dto.setPresion_arterial_sis(estadoVital.getPresion_arterial_sis());
        dto.setPresion_arterial_dias(estadoVital.getPresion_arterial_dias());
        dto.setSaturacion_oxigeno(estadoVital.getSaturacion_oxigeno());
        dto.setFecha_registro_ev(estadoVital.getFecha_registro_ev().toLocalDate());
        dto.setHora_registro_ev(estadoVital.getFecha_registro_ev().toLocalTime());
        dto.setId_paciente(estadoVital.getPaciente().getId_paciente());
        return dto;
    }

    @Override
    public EstadoVitalDTO createEstadoVital(Long pacienteId, EstadoVital estadoVital) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        if (pacienteOptional.isEmpty()) {
            throw new RuntimeException("Paciente no encontrado");
        }
        Paciente paciente = pacienteOptional.get();
        estadoVital.setPaciente(paciente);
        EstadoVital savedEstadoVital = estadoVitalRepository.save(estadoVital);
        return convertToDTO(savedEstadoVital);
    }

    @Override
    public EstadoVitalDTO updateEstadoVitalById(Long estadoVitalId, EstadoVital updatedEstadoVital) {
        EstadoVital estadoVital = estadoVitalRepository.findById(estadoVitalId)
                .orElseThrow(() -> new RuntimeException("Estado vital no encontrado con Id: " + estadoVitalId));
        estadoVital.setFrecuencia_cardiaca(updatedEstadoVital.getFrecuencia_cardiaca());
        estadoVital.setPresion_arterial_sis(updatedEstadoVital.getPresion_arterial_sis());
        estadoVital.setPresion_arterial_dias(updatedEstadoVital.getPresion_arterial_dias());
        estadoVital.setSaturacion_oxigeno(updatedEstadoVital.getSaturacion_oxigeno());

        EstadoVital saverEstadoVital = estadoVitalRepository.save(estadoVital);
        return convertToDTO(saverEstadoVital);
    }

    @Override
    public List<EstadoVitalDTO> getAllEstadoVitales() {
        return estadoVitalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoVitalDTO getEstadoVitalById(Long estadoVitalId) {
        EstadoVital estadoVital = estadoVitalRepository.findById(estadoVitalId)
                .orElseThrow(() -> new RuntimeException("Estado vital no encontrado con Id: " + estadoVitalId));
        return convertToDTO(estadoVital);
    }

    @Override
    public List<EstadoVitalDTO> getEstadoVitalesByPacienteId(Long pacienteId) {
        List<EstadoVital> estadoVitales = estadoVitalRepository.findByPacienteId(pacienteId);
        if (estadoVitales.isEmpty()) {
            throw new RuntimeException("No se encontraron estados vitales para el paciente con ID: " + pacienteId);
        }
        return estadoVitales.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAllEstadoVitales() {
        estadoVitalRepository.deleteAll();
    }

    @Override
    public void deleteEstadoVitalById(Long estadoVitalId) {
        if (estadoVitalRepository.existsById(estadoVitalId)) {
            estadoVitalRepository.deleteById(estadoVitalId);
        } else {
            throw new RuntimeException("Estado vital no encontrado con id: " + estadoVitalId);
        }
    }

    @Override
    public List<EstadoVitalDTO> getEstadoVitalesByRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return estadoVitalRepository.findEstadoVitalesByRangoFecha(fechaInicio, fechaFin)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

}
