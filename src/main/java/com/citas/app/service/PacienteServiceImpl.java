/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.Especialidad;
import com.citas.app.entity.Paciente;
import com.citas.app.repositiry.PacienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Override
    public List<Paciente> listarPaciente() {
        return pacienteRepository.findAll();
    }
    
    @Override
    public Paciente obtenerPaciente(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }
    
    @Override
    public Paciente buscarPorTipoNumDocumento(String tipoDocumento, String numeroDocumento) {
        Paciente paciente = pacienteRepository.buscarPorTipoNumeroDocumento(tipoDocumento, numeroDocumento).orElse(null);
        
        return paciente;
    }
    
    @Override
    public Paciente crearActualizar(Paciente paciente) {
        Paciente pacienteBD = pacienteRepository.buscarPorTipoNumeroDocumento(paciente.getTipoDocumento(), paciente.getNumeroDocumento()).orElse(null);
        
        if(null == pacienteBD){
            pacienteBD = paciente;
        }else{
            pacienteBD.setNombre(paciente.getNombre());
            pacienteBD.setApellidoPaterno(paciente.getApellidoPaterno());
            pacienteBD.setApellidoMaterno(paciente.getApellidoMaterno());
            pacienteBD.setDireccion(paciente.getDireccion());
        }
        return pacienteRepository.save(pacienteBD);
    }

    @Override
    public Paciente crear(Paciente paciente) {
        Paciente pacienteDB = pacienteRepository.buscarPorTipoNumeroDocumento(paciente.getTipoDocumento(), paciente.getNumeroDocumento()).orElse(null);
        if(pacienteDB != null){
            return pacienteDB;
        }
        pacienteDB.setIdPaciente(null);
        pacienteDB = pacienteRepository.save(paciente);
        
        return pacienteDB;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        if(null == paciente.getIdPaciente()) return null;
        return pacienteRepository.save(paciente);
    }
    
}
