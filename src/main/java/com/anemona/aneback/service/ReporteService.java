package com.anemona.aneback.service;

import java.util.List;
import com.anemona.aneback.dto.ReporteDTO;
import com.anemona.aneback.model.Reporte;

public interface ReporteService {
    ReporteDTO getReporteById(Long reporteId);
    List<ReporteDTO> getAllReportes();
    ReporteDTO createReporte(Long pacienteId, Reporte reporte);
    void deleteReporteById(Long reporteId);
    void deleteAllReportes();
    ReporteDTO updateReporteById(Long reporteId, Reporte updatedReporte);
}
