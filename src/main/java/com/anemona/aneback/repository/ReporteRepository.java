package com.anemona.aneback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anemona.aneback.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long>{
    
}
