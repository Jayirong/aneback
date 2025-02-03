package com.anemona.aneback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anemona.aneback.model.ParametrosVitales;

@Repository
public interface ParametrosVitalesRepository extends JpaRepository<ParametrosVitales, Long>{
    @Query("SELECT p FROM ParametrosVitales p WHERE p.activo = true ORDER BY p.fecha_actualizacion DESC")
    Optional<ParametrosVitales> findTopByActivoTrueOrderByFechaActualizacionDesc();
}
