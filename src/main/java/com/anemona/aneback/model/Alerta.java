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
@Table(name = "ALERTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_alerta;

    @Column(nullable = false)
    private String descripcion_alerta;

    @Column(nullable = false)
    private int nivel_alerta; //tentativo el uso de 1 a 3

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime fecha_alerta;
    
    //referencia a paciente
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    //inicializacion de fecha
    @PrePersist
    protected void onCreate() {
        this.fecha_alerta = LocalDateTime.now();
    }

    //campos extra
    @Column(nullable = false)
    private boolean visto = false; //false por defecto

    //referencia al estado vital que gatille la alerta
    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadoVital estadoVital;

    @Column(nullable = false)
    private String parametro_alterado;

}
