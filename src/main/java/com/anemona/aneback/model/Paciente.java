package com.anemona.aneback.model;

import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PACIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente;

    @Column(nullable = false)
    private String nombre_paciente;

    @Column(nullable = false)
    private String apellido_paciente;

    @Column(nullable = false)
    private String rut_paciente;

    @Column(nullable = false)
    private int edad_paciente;

    @Column(nullable = false)
    private String telefono_paciente;

    @JsonManagedReference
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alerta> alertas = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstadoVital> estadosVitales = new ArrayList<>();
}
