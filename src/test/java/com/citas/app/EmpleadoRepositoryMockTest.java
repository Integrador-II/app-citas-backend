/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Empleado;
import com.citas.app.repository.EmpleadoRepository;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Edwin Quispe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpleadoRepositoryMockTest {
    
    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Test
    public void whenFindAll_thenReturnList(){
        
        List<Empleado> encontradosPrev = empleadoRepository.findAll();
        
        Empleado empleado01 = Empleado.builder()
                .nombre("Edwin")
                .apellidoPaterno("Quispe")
                .apellidoMaterno("Ramos")
                .tipoDocumento("01")
                .numeroDocumento("43459267")
                .build();
        
        empleadoRepository.save(empleado01);
        
        List<Empleado> encontradosNext = empleadoRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
    }
}
