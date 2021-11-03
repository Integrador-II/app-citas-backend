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
    public void whenFindAll_thenReturnList(){
        
        Long idTipoAtencion = 1L;
        Long idPaciente = 1L;
        Long idMedico = 1L;
        
        Calendar calendarReserva = Calendar.getInstance();
        calendarReserva.set(2021, 06-1, 29, 9, 00, 0);
        
        String fechaFormat = Utilitarios.formatoFecha(calendarReserva.getTime(), Constantes.FORMATO_FECHA_DDMMYYYYHHMMSS);
        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println("fechaFormat=>"+fechaFormat);
        
        List<Cita> encontradosPrev = citaRepository.findAll();
        
        Cita cita01 = Cita.builder()
                .estado("1")
                .fechaReserva(calendarReserva.getTime())
                .tipoAtencion(TipoAtencion.builder().idTipoAtencion(idTipoAtencion).build())
                .paciente(Paciente.builder().idPaciente(idPaciente).build())
                .medico(Medico.builder().idMedico(idMedico).build())
                .build();
        
        citaRepository.save(cita01);
        
        List<Cita> encontradosNext = citaRepository.findAll();
        
        Assertions.assertEquals(encontradosPrev.size()+1, encontradosNext.size());
        
        //Assertions.assertTrue(true);
    
    }
    
    
    @Test
    public void buscarPorMedicoFechaDesdeTest(){
        Long idMedico = 1L;
        
        Calendar calendarInicio = Calendar.getInstance();
        calendarInicio.set(2021, 06-1, 29, 0, 0, 0);
        
        Calendar calendarFin = Calendar.getInstance();
        calendarFin.set(2021, 06-1, 29, 23, 59, 59);
        
        List<Cita> listsaCitasFecha = citaRepository.buscarCitasPorFechaMedico(idMedico, calendarInicio.getTime(), calendarFin.getTime());
        
        System.out.println("=================");
        System.out.println("listsaCitasFecha.size()==>"+listsaCitasFecha.size());
        
        Assertions.assertTrue(true);
    }
}
