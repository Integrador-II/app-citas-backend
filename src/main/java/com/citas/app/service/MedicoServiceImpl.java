/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Especialidad;
import com.citas.app.entity.Medico;
import com.citas.app.repositiry.MedicoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class MedicoServiceImpl implements MedicoService{

    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Override
    public List<Medico> listarMedico() {
        return medicoRepository.findAll();
    }

    @Override
    public Medico obtenerMedico(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Medico> buscarPorEspecialidad(Especialidad especialidad) {
        return medicoRepository.findByEspecialidad(especialidad);
    }
    
}
