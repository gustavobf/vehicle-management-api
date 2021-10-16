package br.com.batista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.model.Carro;
import br.com.batista.repositories.CarroRepository;

@Service
public class CarroService {

	@Autowired
	CarroRepository carroRepository;

	public Carro saveCar(Carro carro) {
		Carro carroSalvo = carroRepository.save(carro);
		return carroSalvo;
	}

	public List<Carro> getAll() {
		List<Carro> lista = carroRepository.findAll();
		return lista;
	}

}
