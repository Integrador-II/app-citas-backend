/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.citas.app.util;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Edwin Quispe
 */
@Getter @Setter @Builder
public class ErrorMessage{
    private String code ;
    private List<Map<String, String >> messages ;
}
