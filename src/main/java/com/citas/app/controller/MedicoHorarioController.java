package com.citas.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citas.app.dto.RespuestaApi;
import com.citas.app.entity.MedicoHorario;
import com.citas.app.service.MedicoHorarioService;
import com.citas.app.util.Constantes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/medicoHorarios")
@CrossOrigin(origins = "*")
public class MedicoHorarioController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MedicoHorarioController.class);

	@Autowired
	private MedicoHorarioService medicoHorarioService;

	@GetMapping
	public ResponseEntity<List<MedicoHorario>> listarMedicoHorario() {

		List<MedicoHorario> medicoHorarios = medicoHorarioService.listarMedicoHorario();
		if (medicoHorarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		medicoHorarios = medicoHorarios.stream()
				.sorted((a, b) -> a.getIdMedicoHorario().compareTo(b.getIdMedicoHorario()))
				.collect(Collectors.toList());
		
		Collections.reverse(medicoHorarios);
		
		return ResponseEntity.ok(medicoHorarios);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<MedicoHorario> obtenerMedicoHorario(@PathVariable("id") Long id) {
		MedicoHorario medicoHorario = medicoHorarioService.obtenerMedicoHorario(id);
		if (null == medicoHorario) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicoHorario);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> guardarMedicoHorario(@RequestBody MedicoHorario medicoHorarioRequest) {
		try {

			MedicoHorario medicoHorarioBD = null;

			if (null == medicoHorarioRequest) {
				return ResponseEntity.noContent().build();
			}

			if (null == medicoHorarioRequest.getIdMedicoHorario()) {
				medicoHorarioBD = medicoHorarioService.guardar(medicoHorarioRequest);
			} else {
				MedicoHorario medicoHorarioSearch = medicoHorarioService
						.buscar(medicoHorarioRequest.getIdMedicoHorario());
				if (null == medicoHorarioSearch) {
					throw new Exception("Medico Horario no encontrado");
				}

				medicoHorarioService.guardar(medicoHorarioRequest);
			}

			return new ResponseEntity<RespuestaApi>(
					new RespuestaApi(Constantes.CODIGO_RESPUESTA_GENERAL_EXITO, medicoHorarioBD), HttpStatus.OK);

		} catch (Exception e) {
			LOGGER.error("Error: ", e);
			return new ResponseEntity<>(new RespuestaApi(Constantes.CODIGO_RESPUESTA_GENERAL_ERROR, null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
