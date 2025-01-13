package com.anemona.aneback.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReporteDTO {
    private Long id_reporte;
    private String anamnesis;
    private String diagnostico;
    private String observacion;
    private Long id_paciente;
    private LocalDateTime fecha_reporte;
}