/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import java.util.Date;
import java.util.List;

import com.citas.app.entity.MedicoHorario;

/**
 *
 * @author User
 */
public interface MedicoHorarioService {
	public MedicoHorario obtenerMedicoHorario(Long id);
	public List<MedicoHorario> listarMedicoHorario();
    public MedicoHorario guardar(MedicoHorario horario);
    public MedicoHorario buscar(Long idMedicoHorario);    
    
    public List<MedicoHorario> buscarPorMedicoFechaDesde(Long idMedico, Date fechaDesde);
    public List<MedicoHorario> buscarPorMedicoFechaRango(Long idMedico, Date fechaDesde, Date fechaHasta);
    
}
