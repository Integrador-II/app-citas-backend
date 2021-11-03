/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citas.app.dto.RespuestaApi;
import com.citas.app.entity.Especialidad;
import com.citas.app.service.EspecialidadService;
import com.citas.app.util.Constantes;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Edwin Quispe
 */
@Slf4j
@RestController
@RequestMapping(value = "/especialidades")
@CrossOrigin(origins = "*")
public class EspecialidadController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EspecialidadController.class);
    
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
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespuestaApi> guardarEspecialidad(@RequestBody Especialidad especialidadRequest){
    	try {
    		
    		Especialidad especialidadBD = null;

            if(null == especialidadRequest){
                return ResponseEntity.noContent().build();
            }
            
            if(null == especialidadRequest.getIdEspecialidad()) {
            	especialidadBD = especialidadService.guardar(especialidadRequest);
            }else {
            	Especialidad especialidadSearch = especialidadService.buscar(especialidadRequest.getIdEspecialidad());
            	if(null == especialidadSearch) {
            		throw new Exception("Especialidad no encontrada");
            	}
            	
            	especialidadService.guardar(especialidadRequest);
            }
            
            return new ResponseEntity<RespuestaApi>(new RespuestaApi(Constantes.CODIGO_RESPUESTA_GENERAL_EXITO, especialidadBD), HttpStatus.OK);
            
            
    	}catch (Exception e) {
			LOGGER.error("Error: ", e);
			return new ResponseEntity<>(new RespuestaApi(Constantes.CODIGO_RESPUESTA_GENERAL_ERROR, null), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
}
