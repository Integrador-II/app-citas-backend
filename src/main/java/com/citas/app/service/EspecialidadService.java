/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Especialidad;
import java.util.List;

/**
 *
 * @author Edwin Quispe
 */
public interface EspecialidadService {
    
    public List<Especialidad> listarEspecialidad();
    public Especialidad obtenerEspecialidad(Long id);
    
}
