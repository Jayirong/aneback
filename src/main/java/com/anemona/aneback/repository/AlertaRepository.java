package com.anemona.aneback.repository;

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
}
