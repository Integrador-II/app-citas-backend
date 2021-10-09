/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.controller;

import com.citas.app.dto.MedicoEspecialidadCuposResponseDTO;
import com.citas.app.entity.Especialidad;
import com.citas.app.entity.Medico;
import com.citas.app.entity.Paciente;
import com.citas.app.service.PacienteService;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Edwin Quispe
 */

@RestController
@RequestMapping(value = "/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {
    
    @Autowired
    private PacienteService pacienteService;
    
    @GetMapping
    public ResponseEntity<List<Paciente>> listarPaciente(){
        List<Paciente> pacientes = pacienteService.listarPaciente();
        if(pacientes.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pacientes);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Paciente> obtenerPaciente(@PathVariable("id") Long id){
        Paciente paciente = pacienteService.obtenerPaciente(id);
        if(null == paciente){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paciente);
    }
    
    @GetMapping(value = "/buscarTipoNumDocumento")
    public ResponseEntity<Paciente> buscarTipoNumDocumento(@RequestParam(name = "tipoDocumento", required = true) String tipoDocumento, @RequestParam(name = "numeroDocumento", required = true) String numeroDocumento){
        Paciente paciente = pacienteService.buscarPorTipoNumDocumento(tipoDocumento, numeroDocumento);
        if(null == paciente){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(paciente);
    }
}
