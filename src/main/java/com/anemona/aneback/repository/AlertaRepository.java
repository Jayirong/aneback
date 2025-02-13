package com.anemona.aneback.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anemona.aneback.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long>{
    @Query("SELECT a FROM Alerta a WHERE a.paciente.id_paciente = :idPaciente")
    List<Alerta> findByPacienteId(@Param("idPaciente") Long idPaciente);
    //extra
    @Query("SELECT a FROM Alerta a WHERE a.visto = false")
    List<Alerta> findByVistoFalse();

    @Query("SELECT a FROM Alerta a WHERE a.paciente.id_paciente = :idPaciente AND a.visto = false")
    List<Alerta> findByPacienteIdAndVistoFalse(@Param("idPaciente") Long pacienteId);

    //query para obtener las alertas dentro de un rango de fechas
    @Query("SELECT alerta FROM Alerta alerta WHERE alerta.fecha_alerta BETWEEN :fechaInicio AND :fechaFin")
    List<Alerta> findAlertasByRangoFecha(
        @Param("fechaInicio") LocalDateTime fechaInicio,
        @Param("fechaFin") LocalDateTime fechaFin
    );

}
