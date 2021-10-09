/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        
package com.citas.app.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edwin Quispe
 */

@Entity
@Table(name = "cita")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Cita implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idcita")
    private Long idCita;
    private String estado;
    
    @Column(name = "fecha_reserva")
    private Date fechaReserva;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipo_atencion")
    private TipoAtencion tipoAtencion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idpaciente")
    private Paciente paciente;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmedico")
    private Medico medico;
    
}
