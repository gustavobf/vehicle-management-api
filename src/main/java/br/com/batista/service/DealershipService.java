package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.model.Concessionaria;
import br.com.batista.repositories.DealershipRepository;

@Service
public class DealershipService {

	@Autowired
	private DealershipRepository concessionariaRepository;

	public List<DealershipDTO> getAll() {
		final List<Concessionaria> lista = concessionariaRepository.findAll();
		return lista.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<DealershipDTO> getById(final Long id) {
		final Optional<Concessionaria> concessionaria = concessionariaRepository.findById(id);
		final DealershipDTO concessionariaDTO = convertToDTO(concessionaria.get());
		return Optional.of(concessionariaDTO);
	}

	public DealershipDTO create(final DealershipDTO concessionariaDTO) {
		final Concessionaria concessionaria = convertToEntity(concessionariaDTO);
		final Concessionaria concessionariaSalva = concessionariaRepository.save(concessionaria);
		final DealershipDTO dto = convertToDTO(concessionariaSalva);
		return dto;
	}

	public void delete(final Long id) {
		concessionariaRepository.deleteById(id);
	}

	public DealershipDTO update(final DealershipDTO concessionariaDTO) {
		final Concessionaria concessionaria = convertToEntity(concessionariaDTO);
		final Concessionaria concessionariaSalva = concessionariaRepository.save(concessionaria);
		final DealershipDTO dto = convertToDTO(concessionariaSalva);
		return dto;
	}

	public DealershipDTO convertToDTO(final Concessionaria concessionaria) {
		final DealershipDTO concessionariaDTO = new DealershipDTO();
		concessionariaDTO.setId(concessionaria.getId());
		concessionariaDTO.setCnpj(concessionaria.getCnpj());
		concessionariaDTO.setNome(concessionaria.getNome());
		return concessionariaDTO;
	}

	public Concessionaria convertToEntity(final DealershipDTO concessionariaDTO) {
		final Concessionaria concessionaria = new Concessionaria();
		concessionaria.setId(concessionariaDTO.getId());
		concessionaria.setCnpj(concessionariaDTO.getCnpj());
		concessionaria.setNome(concessionariaDTO.getNome());
		return concessionaria;
	}

}
