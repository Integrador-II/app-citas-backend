/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.controller;

import com.citas.app.entity.Especialidad;
import com.citas.app.service.EspecialidadService;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Edwin Quispe
 */
@Slf4j
@RestController
@RequestMapping(value = "/especialidades")
@CrossOrigin(origins = "*")
public class EspecialidadController {
    
    @Autowired
    private EspecialidadService especialidadService;
    
    @GetMapping
    public ResponseEntity<List<Especialidad>> listarEspecialidad(){
        
        List<Especialidad> especialidades = especialidadService.listarEspecialidad();
        if(especialidades.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        //Collections.sort(especialidades, Comparator.);
        
        especialidades = especialidades.stream().sorted((a,b) -> a.getNombre().compareTo(b.getNombre()) ).collect(Collectors.toList());
        return ResponseEntity.ok(especialidades);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Especialidad> obtenerEspecialidad(@PathVariable("id") Long id){
        Especialidad especialidad = especialidadService.obtenerEspecialidad(id);
        if(null == especialidad){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(especialidad);
    }
    
}
