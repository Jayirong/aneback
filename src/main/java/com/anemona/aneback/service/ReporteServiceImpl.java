package com.anemona.aneback.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anemona.aneback.dto.ReporteDTO;
import com.anemona.aneback.model.Paciente;
import com.anemona.aneback.model.Reporte;
import com.anemona.aneback.repository.PacienteRepository;
import com.anemona.aneback.repository.ReporteRepository;

@Service
public class ReporteServiceImpl implements ReporteService{

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private ReporteDTO convertToDTO(Reporte reporte) {
        ReporteDTO dto = new ReporteDTO();
        dto.setId_reporte(reporte.getId_reporte());
        dto.setAnamnesis(reporte.getAnamnesis());
        dto.setDiagnostico(reporte.getDiagnostico());
        dto.setObservacion(reporte.getObservacion());
        dto.setId_paciente(reporte.getPaciente().getId_paciente());
        dto.setFecha_reporte(reporte.getFecha_reporte());
        return dto;
    }

    @Override
    public ReporteDTO createReporte(Long pacienteid, Reporte reporte) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteid);
        if (pacienteOptional.isEmpty()) {
            throw new RuntimeException("Paciente no encontrado");
        }
        Paciente paciente = pacienteOptional.get();
        reporte.setPaciente(paciente);
        Reporte savedReporte = reporteRepository.save(reporte);
        return convertToDTO(savedReporte);
    }

    @Override
    public ReporteDTO getReporteById(Long reporteId) {
        Reporte reporte = reporteRepository.findById(reporteId).orElseThrow(() -> new RuntimeException("No se encuentra el reporte indicado"));
        return convertToDTO(reporte);
    }

    @Override
    public List<ReporteDTO> getAllReportes() {
        return reporteRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public ReporteDTO updateReporteById(Long reporteId, Reporte updatedReporte) {
        Reporte reporte = reporteRepository.findById(reporteId).orElseThrow(() -> new RuntimeException("No se encuentra el reporte a actualizar"));

        reporte.setAnamnesis(updatedReporte.getAnamnesis());
        reporte.setDiagnostico(updatedReporte.getDiagnostico());
        reporte.setObservacion(updatedReporte.getObservacion());

        Reporte savedReporte = reporteRepository.save(reporte);
        return convertToDTO(savedReporte);
    }

    @Override
    public void deleteReporteById(Long reporteId) {
        if (!reporteRepository.existsById(reporteId)) { 
            throw new RuntimeException("Reporte no econtrado");
        }
        reporteRepository.deleteById(reporteId);
    }    

    @Override
    public void deleteAllReportes() {
        reporteRepository.deleteAll();
    }
    
}
