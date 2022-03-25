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

import br.com.batista.model.Marca;
import br.com.batista.service.MarcaService;

@RestController
@RequestMapping("/marca")
public class MarcaResource {

	@Autowired
	private MarcaService marcaService;

	@GetMapping
	public List<Marca> obterLista() {
		return marcaService.getAll();
	}

	@RequestMapping("{id}")
	public Marca obterPorId(@PathVariable int id) {
		return marcaService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable int id) {
		marcaService.deleteMarca(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody Marca marca) {
		marcaService.saveMarca(marca);
	}
}
