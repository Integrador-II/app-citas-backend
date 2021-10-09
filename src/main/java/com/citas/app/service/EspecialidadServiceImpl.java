/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Especialidad;
import com.citas.app.repositiry.EspecialidadRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class EspecialidadServiceImpl implements EspecialidadService{

    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    @Override
    public List<Especialidad> listarEspecialidad() {
        return especialidadRepository.findAll();
    }

    @Override
    public Especialidad obtenerEspecialidad(Long id) {
        return especialidadRepository.findById(id).orElse(null);
    }
    
}
