/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tipo_atencion")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class TipoAtencion implements Serializable {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idtipo_atencion")
    private Long idTipoAtencion;
    
    @Column(name = "tipo_atencion")
    private String tipoAtencion;
}
