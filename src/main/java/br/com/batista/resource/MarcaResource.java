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

import br.com.batista.dto.MarcaDTO;
import br.com.batista.service.MarcaService;

public class MarcaResource {

	@Autowired
	private MarcaService marcaService;

	@GetMapping
	public List<MarcaDTO> obterLista() {
		return marcaService.getAll();
	}

	@RequestMapping("{id}")
	public Optional<MarcaDTO> obterPorId(@PathVariable Long id) {
		return marcaService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {
		marcaService.delete(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody MarcaDTO marca) {
		marcaService.create(marca);
	}
}
