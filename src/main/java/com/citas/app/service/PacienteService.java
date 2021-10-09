/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Paciente;
import java.util.List;

/**
 *
 * @author Edwin Quispe
 */
public interface PacienteService {
    
    public List<Paciente> listarPaciente();
    public Paciente buscarPorTipoNumDocumento(String tipoDocumento, String numeroDocumento);
    public Paciente obtenerPaciente(Long id);
    
    public Paciente crearActualizar(Paciente paciente);
    
    public Paciente crear(Paciente paciente);
    public Paciente actualizar(Paciente paciente);
    
}
