/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.controller;

import com.citas.app.entity.TipoAtencion;
import com.citas.app.service.TipoAtencionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author EdwinQuispe
 */
@RestController
@RequestMapping(value = "/tipoAtenciones")
@CrossOrigin(origins = "*")
public class TipoAtencionController {
    
    @Autowired
    private TipoAtencionService tipoAtencionService;
    
    @GetMapping
    public ResponseEntity<List<TipoAtencion>> listarTipoAtencion(){
        List<TipoAtencion> tipoAtenciones = tipoAtencionService.listarTipoAtencion();
        if(tipoAtenciones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoAtenciones);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<TipoAtencion> obtenerEspecialidad(@PathVariable("id") Long id){
        TipoAtencion tipoAtencion = tipoAtencionService.obtenerTipoAtencion(id);
        if(null == tipoAtencion){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoAtencion);
    }
    
}
