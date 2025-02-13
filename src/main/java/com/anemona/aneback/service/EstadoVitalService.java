package com.anemona.aneback.service;

import java.time.LocalDateTime;
import java.util.List;

import com.anemona.aneback.dto.EstadoVitalDTO;
import com.anemona.aneback.model.EstadoVital;

public interface EstadoVitalService {
    
    EstadoVitalDTO createEstadoVital(Long pacienteId, EstadoVital estadoVital);
    EstadoVitalDTO getEstadoVitalById(Long estadoVitalId);
    List<EstadoVitalDTO> getAllEstadoVitales();
    List<EstadoVitalDTO> getEstadoVitalesByPacienteId(Long pacienteId);
    EstadoVitalDTO updateEstadoVitalById(Long estadiVitalId, EstadoVital estadoVital);
    void deleteAllEstadoVitales();
    void deleteEstadoVitalById(Long id);
    List<EstadoVitalDTO> getEstadoVitalesByRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
