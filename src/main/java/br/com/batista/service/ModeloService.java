package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.model.Modelo;
import br.com.batista.repositories.ModeloRepository;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;

	public List<Modelo> getAll() {
		List<Modelo> lista = modeloRepository.findAll();
		return lista;
	}

	public Optional<Modelo> getById(Long id) {
		Optional<Modelo> modelo = modeloRepository.findById(id);
		return modelo;
	}

	public Modelo create(Modelo modelo) {
		Modelo modeloSalvo = modeloRepository.save(modelo);
		return modeloSalvo;
	}

	public void delete(Long id) {
		modeloRepository.deleteById(id);
	}

	public Modelo update(Long id, Modelo modeloNovo) {
		modeloNovo.setId(id);
		Modelo modelo = modeloRepository.save(modeloNovo);
		return modelo;
	}

}
