package br.com.batista.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.batista.dto.CarroDTO;
import br.com.batista.service.CarroService;

public class CarroResource {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public List<CarroDTO> obterLista() {
		return carroService.getAll();
	}

	@RequestMapping("{id}")
	public Optional<CarroDTO> obterPorId(@PathVariable Long id) {
		return carroService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {
		carroService.delete(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody CarroDTO carro) {
		carroService.create(carro);
	}
}
