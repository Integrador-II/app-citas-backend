/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citas.app.entity.Especialidad;
import com.citas.app.repository.EspecialidadRepository;

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
    
    @Override
	public Especialidad guardar(Especialidad especialidad) {
		return especialidadRepository.save(especialidad);
	}

	@Override
	public Especialidad buscar(Long idEspecialidad) {
		Optional<Especialidad> search = especialidadRepository.findById(idEspecialidad);
		if(!search.isPresent()) return null;
		
		return search.get();
	}
    
}
