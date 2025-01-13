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
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "REPORTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reporte;

    @Column(nullable = false)
    private String anamnesis;

    @Column(nullable = false)
    private String diagnostico;

    @Column
    private String observacion;

    //relacion a paciente
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_paciente_asociado", nullable = false)
    private Paciente paciente;

    @Column(nullable = false)
    //formateo para consumir mediante la api en formato json
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime fecha_reporte;

     //init fecha
    @PrePersist
    protected void onCreate() {
        this.fecha_reporte = LocalDateTime.now();
    }
    
}
