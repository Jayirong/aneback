package com.anemona.aneback.service;

import java.util.List;

import com.anemona.aneback.dto.ParametrosVitalesDTO;
import com.anemona.aneback.model.ParametrosVitales;

public interface ParametrosVitalesService {
    ParametrosVitalesDTO createParametros(ParametrosVitales parametros);
    ParametrosVitalesDTO getParametrosActivos();
    ParametrosVitalesDTO getParametrosById(Long parametroId);
    List<ParametrosVitalesDTO> getAllParametros();
    ParametrosVitalesDTO updateParametros(Long parametroId, ParametrosVitales parametros);
    void deleteParametros(Long parametroId);
    boolean validarEstadovital(float frecuenciaCardiaca, float presionSis, float presionDias, float saturacion);
}
