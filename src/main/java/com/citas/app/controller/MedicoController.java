/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.controller;

import com.citas.app.dto.CuposDTO;
import com.citas.app.dto.MedicoEspecialidadCuposResponseDTO;
import com.citas.app.entity.Cita;
import com.citas.app.entity.Especialidad;
import com.citas.app.entity.Medico;
import com.citas.app.entity.MedicoHorario;
import com.citas.app.service.CitaService;
import com.citas.app.service.MedicoHorarioService;
import com.citas.app.service.MedicoService;
import com.citas.app.util.Constantes;
import com.citas.app.util.Utilitarios;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Edwin Quispe
 */
@Slf4j
@RestController
@RequestMapping(value = "/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {
    
    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private MedicoHorarioService medicoHorarioService;
    
    @Autowired
    private CitaService citaService;
    
    @GetMapping
    public ResponseEntity<List<Medico>> listarMedico(@RequestParam(name = "especialidadId", required = false) Long especialidadId){
        List<Medico> medicos = new ArrayList<>();
        if(null == especialidadId){
            medicos = medicoService.listarMedico();
            if(medicos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            medicos = medicoService.buscarPorEspecialidad(Especialidad.builder().idEspecialidad(especialidadId).build());
            if(medicos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }
        
        return ResponseEntity.ok(medicos);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Medico> obtenerEspecialidad(@PathVariable("id") Long id){
        Medico medico = medicoService.obtenerMedico(id);
        if(null == medico){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medico);
    }
    
    
    @GetMapping(value = "/listarMedicoCuposPorEspecialidad")
    public ResponseEntity<List<MedicoEspecialidadCuposResponseDTO>> listarMedicoCuposPorEspecialidad(@RequestParam(name = "especialidadId", required = false) Long especialidadId) throws ParseException{
        
        List<MedicoEspecialidadCuposResponseDTO> listaMedicosEspecialidad = new ArrayList<>();
        if(null == especialidadId){
            return ResponseEntity.noContent().build();
        }
        
        List<Medico> listaMedicos = medicoService.buscarPorEspecialidad(Especialidad.builder().idEspecialidad(especialidadId).build());
        if(listaMedicos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        Date fechaDesde = Utilitarios.getDateWithoutTimeUsingFormat();
        for(Medico medico : listaMedicos){
                        
            List<MedicoHorario> horarios = medicoHorarioService.buscarPorMedicoFechaDesde(medico.getIdMedico(), fechaDesde);
            if(!horarios.isEmpty()){
                
                MedicoEspecialidadCuposResponseDTO medicoDTO = new MedicoEspecialidadCuposResponseDTO();
                medicoDTO.setMedico(medico);
                
                List<CuposDTO> cuposDisponibles = this.obtenerCuposDisponibles(horarios);
        
                medicoDTO.setCuposDisponibles(cuposDisponibles);
                listaMedicosEspecialidad.add(medicoDTO);
            }
        }
        
        if(listaMedicosEspecialidad.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(listaMedicosEspecialidad);
    }
    
    @GetMapping(value = "/listarCuposPorMedico")
    public ResponseEntity<List<CuposDTO>> listarCuposPorMedico(@RequestParam(name = "medicoId", required = false) Long medicoId) throws ParseException{
        Date fechaDesde = Utilitarios.getDateWithoutTimeUsingFormat();
        List<MedicoHorario> horarios = medicoHorarioService.buscarPorMedicoFechaDesde(medicoId, fechaDesde);
        if(horarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<CuposDTO> cuposDisponibles = this.obtenerCuposDisponibles(horarios);
        return ResponseEntity.ok(cuposDisponibles);
    }
    
    
    @GetMapping(value = "/listarCuposPorMedicoYFecha")
    public ResponseEntity<CuposDTO> listarCuposPorMedicoYFecha(@RequestParam(name = "medicoId", required = false) Long medicoId, @RequestParam(name = "fecha", required = false) String fecha) throws ParseException{
        CuposDTO cupo = null;
        
        String fechaDesdeString = fecha + " 00:00:00";
        String fechaHastaString = fecha + " 23:59:59";
        
        Date fechaDesde = Utilitarios.StringToDate(fechaDesdeString, Constantes.FORMATO_FECHA_DDMMYYYYHHMMSS);
        Date fechaHasta = Utilitarios.StringToDate(fechaHastaString, Constantes.FORMATO_FECHA_DDMMYYYYHHMMSS);
        
        List<MedicoHorario> horarios = medicoHorarioService.buscarPorMedicoFechaRango(medicoId, fechaDesde, fechaHasta);
        
        if(horarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        
        List<CuposDTO> cuposDisponibles = this.obtenerCuposDisponibles(horarios);
        if(!cuposDisponibles.isEmpty()){
            cupo = cuposDisponibles.get(0);
        }
        
        if(null == cupo){
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(cupo);
    }
    
    private List<CuposDTO> obtenerCuposDisponibles(List<MedicoHorario> horarios){
        List<CuposDTO> cuposDisponibles = new ArrayList<>();
        
        for(MedicoHorario medicoHorario : horarios){
            
            Calendar calendarInicio = Calendar.getInstance();
            calendarInicio.setTime(medicoHorario.getFecha());
            calendarInicio.add(Calendar.HOUR, 0);
            calendarInicio.add(Calendar.MINUTE, 0);
            calendarInicio.add(Calendar.SECOND, 0);
            
            Calendar calendarFin = Calendar.getInstance();
            calendarFin.setTime(medicoHorario.getFecha());
            calendarFin.add(Calendar.HOUR, 23);
            calendarFin.add(Calendar.MINUTE, 59);
            calendarFin.add(Calendar.SECOND, 59);
            
            List<Cita> citasDia = citaService.buscarCitasPorFechaMedico(medicoHorario.getMedico().getIdMedico(), calendarInicio.getTime(), calendarFin.getTime());
            
            CuposDTO cupo = new CuposDTO();
            cupo.setFecha(Utilitarios.formatoFecha(medicoHorario.getFecha(), Constantes.FORMATO_FECHA_DDMMYYYY));
            cupo.setHoras(Utilitarios.cuposPorHora(medicoHorario.getFecha(), medicoHorario.getHoraInicio(), medicoHorario.getHoraFin(), Constantes.TIEMPO_ATENCION, 12, citasDia));
            cuposDisponibles.add(cupo);
        }
        
        
        return cuposDisponibles;
    }
    
}
