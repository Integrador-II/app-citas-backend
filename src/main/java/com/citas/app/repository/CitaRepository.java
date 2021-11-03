/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.repository;

import com.citas.app.entity.Cita;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Edwin Quispe
 */
public interface CitaRepository extends JpaRepository<Cita, Long>{
    
    @Query("SELECT c FROM Cita c WHERE c.medico.idMedico = :idMedico AND c.fechaReserva BETWEEN :fechaInicio AND :fechaFin order by c.fechaReserva")
    List<Cita> buscarCitasPorFechaMedico(@Param("idMedico") Long idMedico, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
    
    @Query("SELECT c FROM Cita c WHERE c.paciente.tipoDocumento = :tipoDocumento AND c.paciente.numeroDocumento = :numeroDocumento AND c.fechaReserva BETWEEN :fechaInicio AND :fechaFin order by c.fechaReserva desc")
    List<Cita> buscarCitasPaciente(@Param("tipoDocumento") String tipoDocumento, @Param("numeroDocumento") String numeroDocumento, @Param("fechaInicio") Date fechaReservaDesde, @Param("fechaFin") Date fechaReservaHasta);
    
    
}
