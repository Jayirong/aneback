package com.anemona.aneback.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anemona.aneback.dto.ParametrosVitalesDTO;
import com.anemona.aneback.model.ParametrosVitales;
import com.anemona.aneback.repository.ParametrosVitalesRepository;

@Service
public class ParametrosVitalesServiceImpl implements ParametrosVitalesService{
   
    @Autowired
    private ParametrosVitalesRepository parametrosRepository;

    private ParametrosVitalesDTO convertToDTO(ParametrosVitales parametros) {
        ParametrosVitalesDTO dto = new ParametrosVitalesDTO();
        dto.setId_parametro(parametros.getId_parametro());
        dto.setFrecuencia_cardiaca_min(parametros.getFrecuencia_cardiaca_min());
        dto.setFrecuencia_cardiaca_max(parametros.getFrecuencia_cardiaca_max());
        dto.setPresion_arterial_sis_min(parametros.getPresion_arterial_sis_min());
        dto.setPresion_arterial_sis_max(parametros.getPresion_arterial_sis_max());
        dto.setPresion_arterial_dias_min(parametros.getPresion_arterial_dias_min());
        dto.setPresion_arterial_dias_max(parametros.getPresion_arterial_dias_max());
        dto.setSaturacion_oxigeno_min(parametros.getSaturacion_oxigeno_min());
        dto.setActivo(parametros.isActivo());
        dto.setFecha_actualizacion(parametros.getFecha_actualizacion());
        return dto;
    }

    @Override
    public ParametrosVitalesDTO createParametros(ParametrosVitales parametros) {
        //desactivamos parametros anteriores
        parametrosRepository.findTopByActivoTrueOrderByFechaActualizacionDesc()
            .ifPresent(param -> {
                param.setActivo(false);
                parametrosRepository.save(param);
            });

        ParametrosVitales savedParametros = parametrosRepository.save(parametros);
        return convertToDTO(savedParametros);
    }

    @Override
    public ParametrosVitalesDTO getParametrosActivos() {
        ParametrosVitales parametros = parametrosRepository.findTopByActivoTrueOrderByFechaActualizacionDesc()
            .orElseThrow(() -> new RuntimeException("No hay parametros vitales activos configurados"));
        return convertToDTO(parametros);
    }

    @Override
    public ParametrosVitalesDTO getParametrosById(Long parametroId) {
        ParametrosVitales parametros = parametrosRepository.findById(parametroId)
            .orElseThrow(() -> new RuntimeException("Parametros no encontrados con ID: " + parametroId));
        return convertToDTO(parametros);
    }

    @Override
    public List<ParametrosVitalesDTO> getAllParametros() {
        return parametrosRepository.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Override
    public ParametrosVitalesDTO updateParametros(Long parametroId, ParametrosVitales parametros) {
        ParametrosVitales existingParametros = parametrosRepository.findById(parametroId)
            .orElseThrow(() -> new RuntimeException("Parámetros no encontrados con ID: " + parametroId));
        
        existingParametros.setFrecuencia_cardiaca_min(parametros.getFrecuencia_cardiaca_min());
        existingParametros.setFrecuencia_cardiaca_max(parametros.getFrecuencia_cardiaca_max());
        existingParametros.setPresion_arterial_sis_min(parametros.getPresion_arterial_sis_min());
        existingParametros.setPresion_arterial_sis_max(parametros.getPresion_arterial_sis_max());
        existingParametros.setPresion_arterial_dias_min(parametros.getPresion_arterial_dias_min());
        existingParametros.setPresion_arterial_dias_max(parametros.getPresion_arterial_dias_max());
        existingParametros.setSaturacion_oxigeno_min(parametros.getSaturacion_oxigeno_min());

        return convertToDTO(parametrosRepository.save(existingParametros));
    }

    @Override
    public void deleteParametros(Long parametroId) {
        if (!parametrosRepository.existsById(parametroId)) {
            throw new RuntimeException("Parametros no encontrados con ID: " + parametroId);
        }
        parametrosRepository.deleteById(parametroId);
    }

    @Override
    public boolean validarEstadovital(float frecuenciaCardiaca, float presionSis, float presionDias, float saturacion) {
        ParametrosVitales parametros = parametrosRepository.findTopByActivoTrueOrderByFechaActualizacionDesc()
            .orElseThrow(() -> new RuntimeException("No hay parametros vitales activos configurados"));
        
        return frecuenciaCardiaca >= parametros.getFrecuencia_cardiaca_min() &&
                frecuenciaCardiaca <= parametros.getFrecuencia_cardiaca_max() &&
                presionSis >= parametros.getPresion_arterial_sis_min() &&
                presionSis <= parametros.getPresion_arterial_sis_max() &&
                presionDias >= parametros.getPresion_arterial_dias_min() &&
                presionDias <= parametros.getPresion_arterial_dias_max() &&
                saturacion >= parametros.getSaturacion_oxigeno_min();
    }



}
