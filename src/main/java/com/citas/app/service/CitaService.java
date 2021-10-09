/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Cita;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Edwin Quispe
 */
public interface CitaService {
    
    public List<Cita> buscarCitasPorFechaMedico(Long idMedico, Date fechaInicio, Date fechaFin);
    public Cita obtenerCita(Long id);
    public Cita guardarCita(Cita cita);
    public List<Cita> buscarCitasPaciente(String tipoDocumento, String numeroDocumento, Date fechaReservaDesde, Date fechaReservaHasta);
    
    
}
