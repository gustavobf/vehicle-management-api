package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.CarroDTO;
import br.com.batista.model.Carro;
import br.com.batista.repositories.CarroRepository;

@Service
public class CarroService {

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<CarroDTO> getAll() {
		List<Carro> lista = carroRepository.findAll();
		return lista.stream().map(this::convertToDTO).toList();
	}

	public Optional<CarroDTO> getById(Long id) {
		Optional<Carro> carro = carroRepository.findById(id);
		CarroDTO carroDTO = this.convertToDTO(carro.get());
		return Optional.of(carroDTO);
	}

	public CarroDTO create(CarroDTO carroDTO) {
		Carro carro = this.convertToEntity(carroDTO);
		Carro carroSalvo = carroRepository.save(carro);
		CarroDTO dto = this.convertToDTO(carroSalvo);
		return dto;
	}

	public void delete(Long id) {
		carroRepository.deleteById(id);
	}

	public CarroDTO update(Long id, CarroDTO carroDTO) {
		carroDTO.setId(id);
		Carro carro = this.convertToEntity(carroDTO);
		Carro carroSalvo = carroRepository.save(carro);
		CarroDTO dto = this.convertToDTO(carroSalvo);
		return dto;
	}

	private CarroDTO convertToDTO(Carro carro) {
		CarroDTO carroDTO = modelMapper.map(carro, CarroDTO.class);
		return carroDTO;
	}

	private Carro convertToEntity(CarroDTO carroDTO) {
		Carro carro = modelMapper.map(carroDTO, Carro.class);
		return carro;
	}

}
