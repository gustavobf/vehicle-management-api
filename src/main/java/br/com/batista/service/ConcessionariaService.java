package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.model.Concessionaria;
import br.com.batista.repositories.ConcessionariaRepository;

@Service
public class ConcessionariaService {

	@Autowired
	private ConcessionariaRepository concessionariaRepository;

	public List<Concessionaria> getAll() {
		List<Concessionaria> lista = concessionariaRepository.findAll();
		return lista;
	}

	public Optional<Concessionaria> getById(Long id) {
		Optional<Concessionaria> concessionaria = concessionariaRepository.findById(id);
		return concessionaria;
	}

	public Concessionaria create(Concessionaria concessionaria) {
		Concessionaria concessionariaSalva = concessionariaRepository.save(concessionaria);
		return concessionariaSalva;
	}

	public void delete(Long id) {
		concessionariaRepository.deleteById(id);
	}

	public Concessionaria update(Long id, Concessionaria concessionariaNova) {
		concessionariaNova.setId(id);
		Concessionaria concessionaria = concessionariaRepository.save(concessionariaNova);
		return concessionaria;
	}

}
