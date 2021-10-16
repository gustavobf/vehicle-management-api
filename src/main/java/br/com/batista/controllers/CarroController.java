package br.com.batista.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batista.model.Carro;
import br.com.batista.service.CarroService;

@RestController
@RequestMapping(value = "/api/carro")
public class CarroController {

	@Autowired
	CarroService carroService;
	
	@GetMapping
	public ResponseEntity<List<Carro>> getAll() {
		List<Carro> list = carroService.getAll();
		return ResponseEntity.status(200).body(list);
	}

}
