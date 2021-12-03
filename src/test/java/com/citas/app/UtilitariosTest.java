/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app;

import com.citas.app.util.Constantes;
import com.citas.app.util.Utilitarios;
import java.text.ParseException;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Edwin Quispe
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilitariosTest {
    
    @Test
    public void cuposPorHora_Test() throws ParseException{
        
        Date fecha = Utilitarios.getDateWithoutTimeUsingFormat();
        String horaInicio =  "08:00";
        String horaFin =  "10:00";
        
        //Utilitarios.cuposPorHora(fecha, horaInicio, horaFin, 20, 5, null);
    }
    
    @Test
    public void formatoFecha_Test() throws ParseException{
        System.out.println("======formatoFecha_Test=========");
        Date fecha = new Date();
        String fechaFormat = Utilitarios.formatoFecha(fecha, Constantes.FORMATO_FECHA_DDMMYYYY);
        System.out.println("====>fechaFormat: "+fechaFormat);
    }
    
    
}
