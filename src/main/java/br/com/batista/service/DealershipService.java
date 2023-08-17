package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.model.Dealership;
import br.com.batista.repositories.DealershipRepository;

@Service
public class DealershipService {

	@Autowired
	private DealershipRepository concessionariaRepository;

	public List<DealershipDTO> getAll() {
		final List<Dealership> lista = concessionariaRepository.findAll();
		return lista.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<DealershipDTO> getById(final Long id) {
		final Optional<Dealership> concessionaria = concessionariaRepository.findById(id);
		final DealershipDTO concessionariaDTO = convertToDTO(concessionaria.get());
		return Optional.of(concessionariaDTO);
	}

	public DealershipDTO create(final DealershipDTO concessionariaDTO) {
		final Dealership concessionaria = convertToEntity(concessionariaDTO);
		final Dealership concessionariaSalva = concessionariaRepository.save(concessionaria);
		final DealershipDTO dto = convertToDTO(concessionariaSalva);
		return dto;
	}

	public void delete(final Long id) {
		concessionariaRepository.deleteById(id);
	}

	public DealershipDTO update(final DealershipDTO concessionariaDTO) {
		final Dealership concessionaria = convertToEntity(concessionariaDTO);
		final Dealership concessionariaSalva = concessionariaRepository.save(concessionaria);
		final DealershipDTO dto = convertToDTO(concessionariaSalva);
		return dto;
	}

	public DealershipDTO convertToDTO(final Dealership concessionaria) {
		final DealershipDTO concessionariaDTO = new DealershipDTO();
		concessionariaDTO.setId(concessionaria.getId());
		concessionariaDTO.setCnpj(concessionaria.getCnpj());
		concessionariaDTO.setNome(concessionaria.getName());
		return concessionariaDTO;
	}

	public Dealership convertToEntity(final DealershipDTO concessionariaDTO) {
		final Dealership concessionaria = new Dealership();
		concessionaria.setId(concessionariaDTO.getId());
		concessionaria.setCnpj(concessionariaDTO.getCnpj());
		concessionaria.setName(concessionariaDTO.getNome());
		return concessionaria;
	}

}
