/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import java.util.Calendar;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.citas.app.entity.Medico;
import com.citas.app.entity.MedicoHorario;
import com.citas.app.repository.MedicoHorarioRepository;

/**
 *
 * @author Edwin Quispe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MedicoHorarioRepositoryMockTest {
    
    @Autowired
    private MedicoHorarioRepository medicoHorarioRepository;
    
    
    @Test
    public void registroNuevoHorario(){
        
        Long idMedico = 8L;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 12-1, 3, 0, 0, 0);
        
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
    public void buscarHorarioDisponible(){
        Long idMedico = 8L;
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11-1, 8, 0, 0, 0);
        
        List<MedicoHorario> horarios = medicoHorarioRepository.buscarPorMedicoFechaDesde(idMedico, calendar.getTime());
        
        Assertions.assertTrue(horarios.size() > 0);
        
    }
    
}
