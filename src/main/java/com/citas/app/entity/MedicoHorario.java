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
@Table(name = "medico_horario")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class MedicoHorario implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idmedico_horario")
    private Long idMedicoHorario;
    
    @Column(name = "hora_inicio")
    private String horaInicio;
    
    @Column(name = "hora_fin")
    private String horaFin;
    
    @Column(name = "cantidad_atenciones")
    private Integer cantidadAtenciones;
    

    @Column(name = "tienda_atencion")
    private Integer tiempoAtencion;
    
    
    private Date fecha;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idmedico")
    private Medico medico;
}
