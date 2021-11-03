/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Especialidad;
import com.citas.app.entity.Medico;
import com.citas.app.repository.MedicoRepository;

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
public class MedicoRepositoryMockTest {
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Test
    public void whenFindByEspecialidad_thenReturnListMedico(){
        
        Long idEspecialidad = 1L;
        
        List<Medico> encontradosPrev = medicoRepository.findByEspecialidad(Especialidad.builder().idEspecialidad(idEspecialidad).build());
        
        Medico medico01 = Medico.builder()
                .nombre("Karina")
                .apellidoPaterno("Huiman")
                .apellidoMaterno("Siaden")
                .tipoDocumento("01")
                .numeroDocumento("495511140")
                .especialidad(Especialidad.builder().idEspecialidad(idEspecialidad).build())
                .build();
        
        medicoRepository.save(medico01);
        
        List<Medico> encontradosNext = medicoRepository.findByEspecialidad(medico01.getEspecialidad());
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
       
        Assertions.assertTrue(true);
    
    }
}
