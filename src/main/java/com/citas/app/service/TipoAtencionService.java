/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.service;

import com.citas.app.entity.TipoAtencion;
import java.util.List;

/**
 *
 * @author Edwin Quispe
 */
public interface TipoAtencionService {
    
    public List<TipoAtencion> listarTipoAtencion();
    public TipoAtencion obtenerTipoAtencion(Long id);
}
