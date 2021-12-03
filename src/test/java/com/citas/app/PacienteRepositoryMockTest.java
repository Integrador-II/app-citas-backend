/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Paciente;
import com.citas.app.repository.PacienteRepository;
import com.citas.app.util.Constantes;
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
public class PacienteRepositoryMockTest {
    
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Test
    public void whenFindAll_thenReturnList(){
        
        List<Paciente> encontradosPrev = pacienteRepository.findAll();
        
        Paciente paciente01 = Paciente.builder()
                .nombre("Alberto")
                .apellidoPaterno("Rojas")
                .apellidoMaterno("Quiroz")
                .tipoDocumento("01")
                .numeroDocumento("479959267")
                .direccion("Proceres")
                .celular("98959599")
                .correo("rojas@yopmail.com")
                .build();
        
        pacienteRepository.save(paciente01);
        
        List<Paciente> encontradosNext = pacienteRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
    }
    
    
    @Test
    public void buscarPorTipoNumeroDocumento_Test(){
        
    	 String tipoDocumento = Constantes.TIPO_DOCUMENTO_DNI;
         String numeroDocumento = "43459267";

         Paciente paciente = pacienteRepository.buscarPorTipoNumeroDocumento(tipoDocumento, numeroDocumento).orElse(null);
         
         Assertions.assertEquals(paciente.getNumeroDocumento(), numeroDocumento);
         
    }
}
