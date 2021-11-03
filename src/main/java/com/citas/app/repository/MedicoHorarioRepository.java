/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.repository;

import com.citas.app.entity.MedicoHorario;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edwin Quispe
 */
public interface MedicoHorarioRepository extends JpaRepository<MedicoHorario, Long>{
    
    @Query("SELECT mh FROM MedicoHorario mh WHERE mh.medico.idMedico = :idMedico AND mh.fecha >= :fechaDesde order by mh.fecha")
    List<MedicoHorario> buscarPorMedicoFechaDesde(@Param("idMedico") Long idMedico, @Param("fechaDesde") Date fechaDesde);
    
    
    @Query("SELECT mh FROM MedicoHorario mh WHERE mh.medico.idMedico = :idMedico AND mh.fecha BETWEEN :fechaDesde AND :fechaHasta order by mh.fecha")
    List<MedicoHorario> buscarPorMedicoFechaRango(@Param("idMedico") Long idMedico, @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);
    
    
    
    
}
