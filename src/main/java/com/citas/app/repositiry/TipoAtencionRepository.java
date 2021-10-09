/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.repositiry;

import com.citas.app.entity.TipoAtencion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Edwin Quispe
 */
public interface TipoAtencionRepository extends JpaRepository<TipoAtencion, Long>{
    
    
}
