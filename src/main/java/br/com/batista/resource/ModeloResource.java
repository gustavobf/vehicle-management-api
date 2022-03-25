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

import br.com.batista.dto.ModeloDTO;
import br.com.batista.service.ModeloService;

public class ModeloResource {

	@Autowired
	private ModeloService modeloService;

	@GetMapping
	public List<ModeloDTO> obterLista() {
		return modeloService.getAll();
	}

	@RequestMapping("{id}")
	public Optional<ModeloDTO> obterPorId(@PathVariable Long id) {
		return modeloService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {
		modeloService.delete(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody ModeloDTO modelo) {
		modeloService.create(modelo);
	}
}
