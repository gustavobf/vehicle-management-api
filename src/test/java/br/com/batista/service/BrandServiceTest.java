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
import br.com.batista.model.Brand;
import br.com.batista.repositories.BrandRepository;

public class BrandServiceTest {

	@Mock
	BrandRepository repository;

	@InjectMocks
	BrandService service;

	@Captor
	ArgumentCaptor<Brand> brandCaptor;

	@Captor
	ArgumentCaptor<Long> brandIdCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAll() {
		final List<Brand> brandList = new ArrayList<>();
		final Brand newBrand = new Brand(10l, "name", "country");
		brandList.add(newBrand);

		when(repository.findAll()).thenReturn(brandList);

		final List<BrandDTO> list = service.getAll();
		final BrandDTO brandDTO = list.get(0);
		assertEquals(newBrand.getId(), brandDTO.getId(), 0);
		assertEquals(newBrand.getName(), brandDTO.getNome());
		assertEquals(newBrand.getCountry(), brandDTO.getPais());
	}

	@Test
	public void testGetById() {
		final Brand newBrand = new Brand(10l, "name", "country");
		when(repository.findById(newBrand.getId())).thenReturn(Optional.of(newBrand));

		final Optional<BrandDTO> returnedBrand = service.getById(newBrand.getId());
		final BrandDTO brandDTO = returnedBrand.get();
		assertEquals(newBrand.getId(), brandDTO.getId(), 0);
		assertEquals(newBrand.getName(), brandDTO.getNome());
		assertEquals(newBrand.getCountry(), brandDTO.getPais());
	}

	@Test
	public void testCreate() {
		final BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(10l);
		brandDTO.setNome("name");
		brandDTO.setPais("country");

		when(repository.save(brandCaptor.capture())).thenReturn(service.convertToEntity(brandDTO));

		final BrandDTO returnedBrand = service.create(brandDTO);

		assertEquals(brandDTO.getId(), returnedBrand.getId(), 0);
		assertEquals(brandDTO.getNome(), returnedBrand.getNome());
		assertEquals(brandDTO.getPais(), returnedBrand.getPais());

		final Brand savedBrand = brandCaptor.getValue();
		assertEquals(brandDTO.getId(), savedBrand.getId(), 0);
		assertEquals(brandDTO.getNome(), savedBrand.getName());
		assertEquals(brandDTO.getPais(), savedBrand.getCountry());
	}

	@Test
	public void testDelete() {
		service.delete(15l);
		verify(repository, times(1)).deleteById(brandIdCaptor.capture());
		assertEquals(15, brandIdCaptor.getValue(), 0);
	}

	@Test
	public void testUpdate() {
		final BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(15l);
		brandDTO.setNome("name");
		brandDTO.setPais("country");

		when(repository.save(brandCaptor.capture())).thenReturn(service.convertToEntity(brandDTO));

		final BrandDTO updatedBrand = service.update(brandDTO);

		assertEquals(brandDTO.getId(), updatedBrand.getId(), 0);
		assertEquals(brandDTO.getNome(), updatedBrand.getNome());
		assertEquals(brandDTO.getPais(), updatedBrand.getPais());

		final Brand savedBrand = brandCaptor.getValue();
		assertEquals(brandDTO.getId(), savedBrand.getId(), 0);
		assertEquals(brandDTO.getNome(), savedBrand.getName());
		assertEquals(brandDTO.getPais(), savedBrand.getCountry());
	}

}
