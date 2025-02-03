package com.anemona.aneback.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PARAMETROS_VITALES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParametrosVitales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_parametro;

    @Column(nullable = false)
    private int frecuencia_cardiaca_min;

    @Column(nullable = false)
    private int frecuencia_cardiaca_max;

    @Column(nullable = false)
    private int presion_arterial_sis_min;

    @Column(nullable = false)
    private int presion_arterial_sis_max;

    @Column(nullable = false)
    private int presion_arterial_dias_min;

    @Column(nullable = false)
    private int presion_arterial_dias_max;

    @Column(nullable = false)
    private float saturacion_oxigeno_min;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fecha_actualizacion;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        this.fecha_actualizacion = LocalDateTime.now();
    }
}
