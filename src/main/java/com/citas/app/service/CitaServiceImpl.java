/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Cita;
import com.citas.app.repository.CitaRepository;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class CitaServiceImpl implements CitaService{

    @Autowired
    private CitaRepository citaRepository;
    
    @Override
    public Cita obtenerCita(Long id) {
        return citaRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<Cita> buscarCitasPorFechaMedico(Long idMedico, Date fechaInicio, Date fechaFin) {
        return citaRepository.buscarCitasPorFechaMedico(idMedico, fechaInicio, fechaFin);
    }

    @Override
    public Cita guardarCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> buscarCitasPaciente(String tipoDocumento, String numeroDocumento, Date fechaReservaDesde, Date fechaReservaHasta) {
        return citaRepository.buscarCitasPaciente(tipoDocumento, numeroDocumento, fechaReservaDesde, fechaReservaHasta);
    }
    
}
