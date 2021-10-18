package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.model.Carro;
import br.com.batista.repositories.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;
	
	public List<Carro> getAll() {
		List<Carro> lista = carroRepository.findAll();
		return lista;
	}
	
	public Optional<Carro> getById(Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		return carro;
	}

	public Carro create(Carro carro) {
		Carro carroSalvo = carroRepository.save(carro);
		return carroSalvo;
	}
	
	public void delete(Long id) {
		carroRepository.deleteById(id);
	}
	
	public Carro update(Long id, Carro carroNovo) {
		carroNovo.setId(id);
		Carro carro = carroRepository.save(carroNovo);
		return carro;
	}


}
