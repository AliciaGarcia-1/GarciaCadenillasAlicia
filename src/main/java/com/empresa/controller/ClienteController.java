package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Cliente;
import com.empresa.service.ClienteService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@RestController
@RequestMapping("/rest/cliente")

public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Cliente>> listaCliente() {
		log.info(">>>> lista ");
		List<Cliente> lstCliente = service.listaCliente();
		return ResponseEntity.ok(lstCliente);
	}

	@GetMapping("/buscarPorDNI/{dni}")
	public ResponseEntity<List<Cliente>> buscar(@PathVariable("dni") String dni) {
		log.info(">>>> busca por dni : " + dni);
		List<Cliente> lstCliente = service.listaPorDni(dni);
		if (!CollectionUtils.isEmpty(lstCliente)) {
			return ResponseEntity.ok(lstCliente);
		} else {
			log.info(">>>> buscar por dni - no existen clientes con ese dni : " + dni);
			return ResponseEntity.badRequest().build();
		}
	}

//	@GetMapping("/{id}")
	@ResponseBody
//	public ResponseEntity<Cliente> listaPorId(@PathVariable("id") String id) {
//		Optional <Cliente> optCliente =service.obtienePorId(int idCliente);
//		if (optCliente.isPresent()) {
//			return ResponseEntity.ok(optCliente.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

	@PostMapping
	public ResponseEntity<Cliente> registra(@RequestBody Cliente obj) {
		log.info(">>>> registra  " + obj.getIdCliente());
		Cliente objSalida = service.insertaActualizaCliente(obj);
		if (objSalida != null) {
			return ResponseEntity.ok(objSalida);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<Cliente> actualiza(@RequestBody Cliente obj) {
		log.info(">>>> actualiza  " + obj.getIdCliente());
		Optional<Cliente> optCliente = service.obtienePorId(obj.getIdCliente());
		if (optCliente.isPresent()) {
			Cliente objSalida = service.insertaActualizaCliente(obj);
			if (objSalida != null) {
				return ResponseEntity.ok(objSalida);
			} else {
				return ResponseEntity.badRequest().build();
			}
		} else {
			log.info(">>>> actualiza no existe el id : " + obj.getIdCliente());
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> elimina(@PathVariable("id") int idCliente) {
		log.info(">>>> elimina  " + idCliente);
		Optional<Cliente> optCliente = service.obtienePorId(idCliente);
		if (optCliente.isPresent()) {
			service.eliminaCliente(idCliente);
			return ResponseEntity.ok(optCliente.get());
		} else {
			log.info(">>>> elimina no existe el id : " + idCliente);
			return ResponseEntity.badRequest().build();
		}
	}

}
