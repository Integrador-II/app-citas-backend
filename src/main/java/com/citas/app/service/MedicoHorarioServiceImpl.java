/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citas.app.entity.MedicoHorario;
import com.citas.app.repository.MedicoHorarioRepository;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class MedicoHorarioServiceImpl implements MedicoHorarioService {

	@Override
	public MedicoHorario obtenerMedicoHorario(Long id) {
		return medicoHorarioRepository.findById(id).orElse(null);
	}

	@Override
	public List<MedicoHorario> listarMedicoHorario() {
		return medicoHorarioRepository.findAll();
	}

	@Override
	public MedicoHorario guardar(MedicoHorario medicoHorario) {
		return medicoHorarioRepository.save(medicoHorario);
	}

	@Override
	public MedicoHorario buscar(Long idMedicoHorario) {
		Optional<MedicoHorario> search = medicoHorarioRepository.findById(idMedicoHorario);
		if (!search.isPresent())
			return null;

		return search.get();
	}

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
