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

import br.com.batista.dto.ConcessionariaDTO;
import br.com.batista.service.ConcessionariaService;

//TODO put method
public class ConcessionariaResource {

	@Autowired
	private ConcessionariaService concessionariaService;

	@GetMapping
	public List<ConcessionariaDTO> obterLista() {
		return concessionariaService.getAll();
	}

	@RequestMapping("{id}")
	public Optional<ConcessionariaDTO> obterPorId(@PathVariable Long id) {
		return concessionariaService.getById(id);
	}

	@DeleteMapping("{id}")
	public void excluir(@PathVariable Long id) {
		concessionariaService.delete(id);
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public void salvar(@RequestBody ConcessionariaDTO concessionaria) {
		concessionariaService.create(concessionaria);
	}
}
