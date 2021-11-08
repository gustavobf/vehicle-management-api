package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.ConcessionariaDTO;
import br.com.batista.model.Concessionaria;
import br.com.batista.repositories.ConcessionariaRepository;

@Service
public class ConcessionariaService {

	@Autowired
	private ConcessionariaRepository concessionariaRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<ConcessionariaDTO> getAll() {
		List<Concessionaria> lista = concessionariaRepository.findAll();
		return lista.stream().map(this::convertToDTO).toList();
	}

	public Optional<ConcessionariaDTO> getById(Long id) {
		Optional<Concessionaria> concessionaria = concessionariaRepository.findById(id);
		ConcessionariaDTO concessionariaDTO = this.convertToDTO(concessionaria.get());
		return Optional.of(concessionariaDTO);
	}

	public ConcessionariaDTO create(ConcessionariaDTO concessionariaDTO) {
		Concessionaria concessionaria = this.convertToEntity(concessionariaDTO);
		Concessionaria concessionariaSalva = concessionariaRepository.save(concessionaria);
		ConcessionariaDTO dto = this.convertToDTO(concessionariaSalva);
		return dto;
	}

	public void delete(Long id) {
		concessionariaRepository.deleteById(id);
	}

	public ConcessionariaDTO update(Long id, ConcessionariaDTO concessionariaDTO) {
		concessionariaDTO.setId(id);
		Concessionaria concessionaria = this.convertToEntity(concessionariaDTO);
		Concessionaria concessionariaSalva = concessionariaRepository.save(concessionaria);
		ConcessionariaDTO dto = this.convertToDTO(concessionariaSalva);
		return dto;
	}

	public ConcessionariaDTO convertToDTO(Concessionaria concessionaria) {
		ConcessionariaDTO concessionariaDTO = modelMapper.map(concessionaria, ConcessionariaDTO.class);
		return concessionariaDTO;
	}

	public Concessionaria convertToEntity(ConcessionariaDTO concessionariaDTO) {
		Concessionaria concessionaria = modelMapper.map(concessionariaDTO, Concessionaria.class);
		return concessionaria;
	}

}
