/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.entity.Cita;
import com.citas.app.entity.Medico;
import com.citas.app.entity.Paciente;
import com.citas.app.entity.TipoAtencion;
import com.citas.app.repository.CitaRepository;
import com.citas.app.util.Constantes;
import com.citas.app.util.Utilitarios;
import java.util.Calendar;
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
public class CitaRepositoryMockTest {
    
    @Autowired
    private CitaRepository citaRepository;
    
    @Test
    public void registroCitaTest(){
        
        Long idTipoAtencion = 1L;
        Long idPaciente = 14L;
        Long idMedico = 8L;
        
        Calendar calendarReserva = Calendar.getInstance();
        calendarReserva.set(2021, 11-1, 8, 8, 00, 0);
        
        List<Cita> encontradosPrev = citaRepository.findAll();
        
        Cita cita01 = Cita.builder()
                .estado(Constantes.CITA_ESTADO_PENDIENTE_CONFIRMAR)
                .fechaReserva(calendarReserva.getTime())
                .tipoAtencion(TipoAtencion.builder().idTipoAtencion(idTipoAtencion).build())
                .paciente(Paciente.builder().idPaciente(idPaciente).build())
                .medico(Medico.builder().idMedico(idMedico).build())
                .build();
        
        citaRepository.save(cita01);
        
        List<Cita> encontradosNext = citaRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
    }
    
    
    @Test
    public void buscarPorMedicoFechaDesdeTest(){
        Long idMedico = 8L;
        
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.set(2021, 11-1, 4, 0, 0, 0);
        
        Calendar calendarFin = Calendar.getInstance();
        calendarFin.set(2021, 11-1, 4, 23, 59, 59);
        
        List<Cita> listsaCitasFecha = citaRepository.buscarCitasPorFechaMedico(idMedico, calendarInicio.getTime(), calendarFin.getTime());
        
        System.out.println("=================");
        System.out.println("listsaCitasFecha.size()==>"+listsaCitasFecha.size());
        
        Assertions.assertTrue(listsaCitasFecha.size()>0);
    }
}
