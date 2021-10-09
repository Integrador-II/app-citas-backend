/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Especialidad;
import com.citas.app.entity.Medico;
import java.util.List;

/**
 *
 * @author Edwin Quispe
 */
public interface MedicoService {
    
    public List<Medico> listarMedico();
    public Medico obtenerMedico(Long id);
    public List<Medico> buscarPorEspecialidad(Especialidad especialidad);
}
