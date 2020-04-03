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

import br.com.batista.model.Concessionaria;
import br.com.batista.service.ConcessionariaService;

@RestController
@RequestMapping("/concessionaria")
public class ConcessionariaResource {

	
	@Autowired
	private ConcessionariaService concessionariaService;

	@GetMapping
	public List<Concessionaria> obterLista() {
		return concessionariaService.getAll();
	}

	@RequestMapping("{id}")
	public Concessionaria obterPorId(@PathVariable int id) {
		return concessionariaService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(int id) {
		concessionariaService.deleteConcessionaria(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody Concessionaria concessionaria) {
		concessionariaService.saveConcessionaria(concessionaria);
	}
	
}
