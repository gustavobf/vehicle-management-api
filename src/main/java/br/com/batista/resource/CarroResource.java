package br.com.batista.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.batista.model.Carro;
import br.com.batista.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroResource {

	@Autowired
	private CarroService carroService;

	@GetMapping
	public List<Carro> obterLista() {
		return carroService.getAll();
	}

	@RequestMapping("{id}")
	public Carro obterPorId(@PathVariable int id) {
		return carroService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(int id) {
		carroService.deleteCar(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody Carro carro) {
		carroService.saveCar(carro);
	}
}
