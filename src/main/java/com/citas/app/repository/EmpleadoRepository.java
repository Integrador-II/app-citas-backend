/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.repository;

import com.citas.app.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Edwin Quispe
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    
    
}
