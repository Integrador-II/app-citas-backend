/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.util;

import com.citas.app.entity.Cita;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Edwin Quispe
 */
public final class Utilitarios {
    
    public static List<String> cuposPorHora(Date fecha, String horaInicio, String horaFin, Integer tiempoAtencion, Integer cantidadAtencion, List<Cita> citasDia){
        
        String[] inicioSpli = horaInicio.split(":");
        SimpleDateFormat formatoHoraMinuto = new SimpleDateFormat(Constantes.FORMATO_FECHA_HHMM);
        
        List<String> listaHoras = new ArrayList<>();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        for(int i =1; i<=cantidadAtencion; i++){
            if(i == 1){
                calendar.add(Calendar.HOUR, Integer.parseInt(inicioSpli[0]));
                calendar.add(Calendar.MINUTE, Integer.parseInt(inicioSpli[1]));
                calendar.add(Calendar.SECOND, 0);
            }else{
                calendar.add(Calendar.MINUTE, tiempoAtencion);
            }
            
            if(!existeReserva(citasDia, calendar)){
                listaHoras.add(formatoHoraMinuto.format(calendar.getTime()));
            }
        }
        
        return listaHoras;
    }
    
    private static Boolean existeReserva(List<Cita> citas, Calendar calendarReserva){
        Boolean existe = false;
        for(Cita cita : citas){
            Calendar calendarCita = Calendar.getInstance();
            calendarCita.setTime(cita.getFechaReserva());
            
            if(calendarReserva.equals(calendarCita)){
                existe = true;
                break;
            }
        }
        
        return existe;
    }
    
    public static Date getDateWithoutTimeUsingFormat() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.parse(formatter.format(new Date()));
    }
    
    public static String formatoFecha(Date fecha, String formato){
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        String fechaFormateada = sdf.format(calendar.getTime());
        
        return fechaFormateada;
    }
    
    public static Date StringToDate(String fechaString, String formato) throws ParseException{
        SimpleDateFormat sourceFormat = new SimpleDateFormat(formato);
        Date date = sourceFormat.parse(fechaString);

        return date;
    }
    
    //DD/MM/YYY - HH:MM
    public static Date crearFechaReserva(String fecha, String hora){
        String[] fechaSplit = fecha.split("/");
        String[] horaSplit = hora.split(":");
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(fechaSplit[2]), Integer.parseInt(fechaSplit[1])-1, Integer.parseInt(fechaSplit[0]), Integer.parseInt(horaSplit[0]), Integer.parseInt(horaSplit[1]), 0);
        
        return calendar.getTime();
    }
    
    public static String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
