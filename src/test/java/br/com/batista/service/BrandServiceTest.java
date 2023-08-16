package br.com.batista.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.batista.dto.BrandDTO;
import br.com.batista.model.Marca;
import br.com.batista.repositories.BrandRepository;

public class BrandServiceTest {

	@Mock
	BrandRepository repository;

	@InjectMocks
	BrandService service;

	@Captor
	ArgumentCaptor<Marca> brandCaptor;

	@Captor
	ArgumentCaptor<Long> brandIdCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAll() {
		final List<Marca> listBrands = new ArrayList<>();
		final Marca brand1 = new Marca(10l, "name", "country");
		listBrands.add(brand1);

		when(repository.findAll()).thenReturn(listBrands);

		final List<BrandDTO> list = service.getAll();
		final BrandDTO brandDTO1 = list.get(0);
		assertEquals(10, brandDTO1.getId(), 0);
		assertEquals(brand1.getNome(), brandDTO1.getNome());
		assertEquals(brand1.getPais(), brandDTO1.getPais());
	}

	@Test
	public void testGetById() {
		final Marca brand1 = new Marca(10l, "name", "country");
		when(repository.findById(brand1.getId())).thenReturn(Optional.of(brand1));

		final Optional<BrandDTO> brandReturned = service.getById(brand1.getId());
		final BrandDTO brandDTO1 = brandReturned.get();
		assertEquals(10, brandDTO1.getId(), 0);
		assertEquals(brand1.getNome(), brandDTO1.getNome());
		assertEquals(brand1.getPais(), brandDTO1.getPais());
	}

	@Test
	public void testCreate() {
		final BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(10l);
		brandDTO.setNome("");
		brandDTO.setPais("");

		when(repository.save(brandCaptor.capture())).thenReturn(service.convertToEntity(brandDTO));

		final BrandDTO brandReturned = service.create(brandDTO);

		assertEquals(10, brandReturned.getId(), 0);
		assertEquals(brandDTO.getNome(), brandReturned.getNome());
		assertEquals(brandDTO.getPais(), brandReturned.getPais());

		final Marca brandSaved = brandCaptor.getValue();
		assertEquals(10, brandSaved.getId(), 0);
		assertEquals(brandDTO.getNome(), brandSaved.getNome());
		assertEquals(brandDTO.getPais(), brandSaved.getPais());
	}

	@Test
	public void testDelete() {
		service.delete(15l);
		verify(repository, times(1)).deleteById(brandIdCaptor.capture());
		assertEquals(15, brandIdCaptor.getValue(), 0);
	}

}
