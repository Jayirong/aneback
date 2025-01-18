package com.anemona.aneback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anemona.aneback.model.EstadoVital;

@Repository
public interface EstadoVitalRepository extends JpaRepository<EstadoVital, Long>{
    @Query("SELECT e FROM EstadoVital e WHERE e.paciente.id_paciente = :idPaciente")
    List<EstadoVital> findByPacienteId(Long idPaciente);
}
