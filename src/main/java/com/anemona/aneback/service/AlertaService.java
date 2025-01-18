package com.anemona.aneback.service;

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


}
