package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.model.Marca;
import br.com.batista.repositories.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;

	public List<Marca> getAll() {
		List<Marca> lista = marcaRepository.findAll();
		return lista;
	}

	public Optional<Marca> getById(Long id) {
		Optional<Marca> marca = marcaRepository.findById(id);
		return marca;
	}

	public Marca create(Marca marca) {
		Marca marcaSalva = marcaRepository.save(marca);
		return marcaSalva;
	}

	public void delete(Long id) {
		marcaRepository.deleteById(id);
	}

	public Marca update(Long id, Marca marcaNova) {
		marcaNova.setId(id);
		Marca marca = marcaRepository.save(marcaNova);
		return marca;
	}

}
