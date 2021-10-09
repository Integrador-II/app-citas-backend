/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.MedicoHorario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public interface MedicoHorarioService {
    public List<MedicoHorario> buscarPorMedicoFechaDesde(Long idMedico, Date fechaDesde);
    public List<MedicoHorario> buscarPorMedicoFechaRango(Long idMedico, Date fechaDesde, Date fechaHasta);
    
}
