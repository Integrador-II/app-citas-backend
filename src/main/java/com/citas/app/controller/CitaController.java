/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.controller;

import com.citas.app.dto.CitaRequestDTO;
import com.citas.app.entity.Cita;
import com.citas.app.entity.Medico;
import com.citas.app.entity.Paciente;
import com.citas.app.entity.TipoAtencion;
import com.citas.app.service.CitaService;
import com.citas.app.service.PacienteService;
import com.citas.app.util.Constantes;
import com.citas.app.util.Utilitarios;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Edwin Quispe
 */
@Slf4j
@RestController
@RequestMapping(value = "/citas")
@CrossOrigin(origins = "*")
public class CitaController {
    
    @Autowired
    private CitaService citaService;
    
    @Autowired
    private PacienteService pacienteService;

    
    @PostMapping(value = "/generarCita")
    public ResponseEntity<Cita> generarCita(@RequestBody CitaRequestDTO citaRequestDTO){
        
        log.debug("Ingreso generarCita");
        
        if(null == citaRequestDTO || null == citaRequestDTO.getPaciente()){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
        Paciente paciente = pacienteService.crearActualizar(citaRequestDTO.getPaciente());
        
        if(null == paciente){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
        
        Cita citaCreate = Cita.builder()
                .fechaReserva(Utilitarios.crearFechaReserva(citaRequestDTO.getFechaReserva(), citaRequestDTO.getHoraReserva()))
                .estado(Constantes.CITA_ESTADO_PENDIENTE_CONFIRMAR)
                .paciente(paciente)
                .medico(Medico.builder().idMedico(citaRequestDTO.getIdMedico()).build())
                .tipoAtencion(TipoAtencion.builder().idTipoAtencion(citaRequestDTO.getIdTipoAtencion()).build())
                .build();
        
        try{
            citaCreate = citaService.guardarCita(citaCreate);
        }catch(Exception ex){
            log.debug("ex.getMessage==>"+ex.getMessage());
            log.debug("ex==>"+ex.getMessage());
        }
        
        
        if(null == citaCreate || null == citaCreate.getIdCita()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
        //Cita cita = citaService.obtenerCita(citaCreate.getIdCita());
                
        return ResponseEntity.status(HttpStatus.CREATED).body(citaCreate);
    }
    
    @GetMapping(value = "/buscarCita")
    public ResponseEntity<Cita> buscarCita(@RequestParam(name = "tipoDocumento", required = true) String tipoDocumento, @RequestParam(name = "numeroDocumento", required = true) String numeroDocumento, @RequestParam(name = "fechaReserva", required = true) String fechaReserva) throws ParseException{
        log.debug("INGRESO => buscarCita");
        
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        
        
        String fechaDesdeString = fechaReserva + " 00:00:00";
        String fechaHastaString = fechaReserva + " 23:59:59";
        
        Date fechaDesde = Utilitarios.StringToDate(fechaDesdeString, Constantes.FORMATO_FECHA_DDMMYYYYHHMMSS);
        Date fechaHasta = Utilitarios.StringToDate(fechaHastaString, Constantes.FORMATO_FECHA_DDMMYYYYHHMMSS);
        
        List<Cita> listaCitas = citaService.buscarCitasPaciente(tipoDocumento, numeroDocumento, fechaDesde, fechaHasta);
        
        if(listaCitas == null || listaCitas.size() == 0){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
        
        
        Cita cita = listaCitas.get(0);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(cita);
    }
    
}
