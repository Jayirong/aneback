package com.anemona.aneback.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESTADO_VITAL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_estado;

    @Column(nullable = false)
    private int frecuencia_cardiaca;

    @Column(nullable = false)
    private int presion_arterial_sis;

    @Column(nullable = false)
    private int presion_arterial_dias;

    @Column(nullable = false)
    private float saturacion_oxigeno; //usar valores entre 0 y 1 para hacer funcion de porcentaje

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime fecha_registro_ev;

    //refe al paciente
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    //inicializacion de la fecha
    @PrePersist
    protected void onCreate() {
        this.fecha_registro_ev = LocalDateTime.now();
    }
    
}
