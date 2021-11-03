/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Medico;
import com.citas.app.entity.MedicoHorario;
import com.citas.app.repository.MedicoHorarioRepository;
import com.citas.app.util.Constantes;
import com.citas.app.util.Utilitarios;
import java.util.Calendar;
import java.util.Date;
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
public class MedicoHorarioRepositoryMockTest {
    
    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;
    
    @Test
    public void whenFindAll_thenReturnList(){
        
        Long idMedico = 1L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 10-1, 22, 0, 0, 0);
        
        List<MedicoHorario> encontradosPrev = medicoHorarioRepository.findAll();
        
        MedicoHorario medicoHorario01 = MedicoHorario.builder()
                .horaInicio("08:00")
                .horaFin("12:00")
                .fecha(calendar.getTime())
                .tiempoAtencion(20)
                .cantidadAtenciones(10)
                .medico(Medico.builder().idMedico(idMedico).build())
                .build();
        
        medicoHorarioRepository.save(medicoHorario01);
        
        List<MedicoHorario> encontradosNext = medicoHorarioRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
       
    
    }
    
    @Test
    public void whenFindByMedicoFechaDesde_thenReturnList(){
        
        Long idMedico = 1L;
        Date fechaDesde = new Date();
        List<MedicoHorario> horarios = medicoHorarioRepository.buscarPorMedicoFechaDesde(idMedico, fechaDesde);
        
        System.out.println("=============");
        System.out.println("total=>"+horarios.size());
        
    }
    
}
