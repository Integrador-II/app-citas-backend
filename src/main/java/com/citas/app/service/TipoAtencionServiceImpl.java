/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.TipoAtencion;
import com.citas.app.repositiry.TipoAtencionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edwin Quispe
 */
@Service
public class TipoAtencionServiceImpl implements TipoAtencionService {
    
    @Autowired
    private TipoAtencionRepository tipoAtencionRepository;

    @Override
    public List<TipoAtencion> listarTipoAtencion() {
        return tipoAtencionRepository.findAll();
    }

    @Override
    public TipoAtencion obtenerTipoAtencion(Long id) {
        return tipoAtencionRepository.findById(id).orElse(null);
    }
    
}
