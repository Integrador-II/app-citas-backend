/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.dto;

import com.citas.app.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edwin Quispe
 */
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class CitaRequestDTO {
    
    private Long idTipoAtencion;
    private Long idMedico;
    private String fechaReserva;
    private String horaReserva;
    private Paciente paciente;
    
}
