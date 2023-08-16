package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.CarDTO;
import br.com.batista.model.Carro;
import br.com.batista.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carroRepository;

	public List<CarDTO> getAll() {
		final List<Carro> lista = carroRepository.findAll();
		return lista.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<CarDTO> getById(final Long id) {
		final Optional<Carro> carro = carroRepository.findById(id);
		final CarDTO carroDTO = convertToDTO(carro.get());
		return Optional.of(carroDTO);
	}

	public CarDTO create(final CarDTO carroDTO) {
		final Carro carro = convertToEntity(carroDTO);
		final Carro carroSalvo = carroRepository.save(carro);
		final CarDTO dto = convertToDTO(carroSalvo);
		return dto;
	}

	public void delete(final Long id) {
		carroRepository.deleteById(id);
	}

	public CarDTO update(final Long id, final CarDTO carroDTO) {
		carroDTO.setId(id);
		final Carro carro = convertToEntity(carroDTO);
		final Carro carroSalvo = carroRepository.save(carro);
		final CarDTO dto = convertToDTO(carroSalvo);
		return dto;
	}

	private CarDTO convertToDTO(final Carro carro) {
		final CarDTO carroDTO = new CarDTO();
		carroDTO.setId(carro.getId());
		carroDTO.setAno(carro.getAno());
		carroDTO.setCor(carro.getCor());
		carroDTO.setNome(carro.getNome());
		carroDTO.setPlaca(carro.getPlaca());
		carroDTO.setPortas(carro.getPortas());
		carroDTO.setPotencia(carro.getPotencia());
		return carroDTO;
	}

	private Carro convertToEntity(final CarDTO carroDTO) {
		final Carro carro = new Carro();
		carro.setId(carroDTO.getId());
		carro.setAno(carroDTO.getAno());
		carro.setCor(carroDTO.getCor());
		carro.setNome(carroDTO.getNome());
		carro.setPlaca(carroDTO.getPlaca());
		carro.setPortas(carroDTO.getPortas());
		carro.setPotencia(carroDTO.getPotencia());
		return carro;
	}

}
