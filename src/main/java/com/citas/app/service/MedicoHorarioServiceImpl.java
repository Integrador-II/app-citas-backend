/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.MedicoHorario;
import com.citas.app.repository.MedicoHorarioRepository;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class MedicoHorarioServiceImpl implements MedicoHorarioService{

    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;
    
    @Override
    public List<MedicoHorario> buscarPorMedicoFechaDesde(Long idMedico, Date fechaDesde) {
        return medicoHorarioRepository.buscarPorMedicoFechaDesde(idMedico, fechaDesde);
    }
    
    @Override
    public List<MedicoHorario> buscarPorMedicoFechaRango(Long idMedico, Date fechaDesde, Date fechaHasta) {
        return medicoHorarioRepository.buscarPorMedicoFechaRango(idMedico, fechaDesde, fechaHasta);
    }
    
}
