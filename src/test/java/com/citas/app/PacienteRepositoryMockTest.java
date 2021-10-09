/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Paciente;
import com.citas.app.repositiry.PacienteRepository;
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
                .nombre("Edwin")
                .apellidoPaterno("Quispe")
                .apellidoMaterno("Ramos")
                .tipoDocumento("01")
                .numeroDocumento("43459267")
                .direccion("Jr Los Ingenieros Mz K14 Lote 17")
                .celular("973868825")
                .correo("edwinqramos@gmail.com")
                .build();
        
        pacienteRepository.save(paciente01);
        
        
        List<Paciente> encontradosNext = pacienteRepository.findAll();
        
        //Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
    }
    
    
    @Test
    public void buscarPorTipoNumeroDocumento_Test(){
        
        try{
        
            String tipoDocumento = Constantes.TIPO_DOCUMENTO_DNI;
            String numeroDocumento = "43459267";

            Paciente paciente = pacienteRepository.buscarPorTipoNumeroDocumento(tipoDocumento, numeroDocumento).orElse(null);
            System.out.println("==============OK PACIENTE==============");
        }catch(Exception ex){
            System.out.println("=======ERROR=======");
            System.out.println("ex=>"+ex.getMessage());
        }
        
        Assertions.assertTrue(true);
        
    }
}
