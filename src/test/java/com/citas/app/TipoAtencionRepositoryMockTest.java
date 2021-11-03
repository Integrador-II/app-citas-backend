/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.TipoAtencion;
import com.citas.app.repository.TipoAtencionRepository;

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
public class TipoAtencionRepositoryMockTest {
    
    @Autowired
    private TipoAtencionRepository tipoAtencionRepository;
    
    @Test
    public void whenFindAll_thenReturnList(){
        
        List<TipoAtencion> encontradosPrev = tipoAtencionRepository.findAll();
        
        TipoAtencion tipoAtencion01 = TipoAtencion.builder()
                .tipoAtencion("Virtual")
                .build();
        
        tipoAtencionRepository.save(tipoAtencion01);
        
        List<TipoAtencion> encontradosNext = tipoAtencionRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
    }
}
