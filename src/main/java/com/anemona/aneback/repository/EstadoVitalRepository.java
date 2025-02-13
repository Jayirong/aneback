package com.anemona.aneback.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anemona.aneback.model.EstadoVital;

@Repository
public interface EstadoVitalRepository extends JpaRepository<EstadoVital, Long>{
    @Query("SELECT e FROM EstadoVital e WHERE e.paciente.id_paciente = :idPaciente")
    List<EstadoVital> findByPacienteId(Long idPaciente);

    //Query para obtener EA en un rango de fecha y hora
    @Query("SELECT estadoVital FROM EstadoVital estadoVital WHERE estadoVital.fecha_registro_ev BETWEEN :fechaInicio AND :fechaFin")
    List<EstadoVital> findEstadoVitalesByRangoFecha(
        @Param("fechaInicio") LocalDateTime fechaInicio,
        @Param("fechaFin") LocalDateTime fechaFin
    );
}
