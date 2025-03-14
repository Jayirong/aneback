package com.anemona.aneback.service;

import java.time.LocalDateTime;
import java.util.List;

import com.anemona.aneback.dto.AlertaDTO;
import com.anemona.aneback.model.Alerta;

public interface AlertaService {
    
    AlertaDTO createAlerta(Long pacienteId, Alerta alerta);
    AlertaDTO getAlertaById(Long alertaId);
    List<AlertaDTO> getAllAlertas();
    List<AlertaDTO> getAlertasByPacienteId(Long pacienteId);
    AlertaDTO updateAlertaById(Long alertaId, Alerta alerta);
    void deleteAllAlertas();
    void deleteAlertaById(Long id);
    //extra
    AlertaDTO marcarComoVista(Long alertaId);
    AlertaDTO marcarComoNoVista(Long alertaId);
    List<AlertaDTO> getAlertasNoVistas();
    List<AlertaDTO> getAlertasNoVistasByPacienteId(Long pacienteId);
    List<AlertaDTO> getAlertasByRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
