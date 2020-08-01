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

import br.com.batista.model.Modelo;
import br.com.batista.service.ModeloService;

@RestController
@RequestMapping("/modelo")
public class ModeloResource {
	
	@Autowired
	private ModeloService modeloService;

	@GetMapping
	public List<Modelo> obterLista() {
		return modeloService.getAll();
	}

	@RequestMapping("{id}")
	public Modelo obterPorId(@PathVariable int id) {
		return modeloService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable int id) {
		modeloService.deleteModelo(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody Modelo modelo) {
		modeloService.saveModelo(modelo);
	}
}
