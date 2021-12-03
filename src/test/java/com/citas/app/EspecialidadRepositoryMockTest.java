/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Especialidad;
import com.citas.app.repository.EspecialidadRepository;

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
public class EspecialidadRepositoryMockTest {
    
    @Autowired
    private EspecialidadRepository especialidadRepository;
    
    @Test
    public void whenFind_thenReturnListEspecialidad(){
        
        List<Especialidad> encontradosPrev = especialidadRepository.findAll();
        
        Especialidad especialidad01 = Especialidad.builder()
                .nombre("Neumología")
                .build();
        
        especialidadRepository.save(especialidad01);
        
        List<Especialidad> encontradosNext = especialidadRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
    
    }
}
