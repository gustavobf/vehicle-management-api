package br.com.batista.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.batista.dto.BrandDTO;
import br.com.batista.entity.Brand;
import br.com.batista.mapper.BrandMapper;
import br.com.batista.repository.BrandRepository;
import br.com.batista.repository.CarRepository;

public class BrandServiceTest {

	@InjectMocks
	BrandService service;

	@Mock
	BrandRepository repository;

	@Mock
	CarRepository carRepository;

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

		Pageable pageable = PageRequest.of(0, 10);

		when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandList));

		final Page<BrandDTO> list = service.getAll(pageable);
		final BrandDTO brandDTO = list.getContent().get(0);
		assertEquals(newBrand.getId(), brandDTO.getId(), 0);
		assertEquals(newBrand.getName(), brandDTO.getName());
		assertEquals(newBrand.getCountry(), brandDTO.getCountry());
	}

	@Test
	public void testGetById() {
		final Brand newBrand = new Brand(10l, "name", "country");
		when(repository.findById(newBrand.getId())).thenReturn(Optional.of(newBrand));

		final BrandDTO brandDTO = service.getById(newBrand.getId());
		assertEquals(newBrand.getId(), brandDTO.getId(), 0);
		assertEquals(newBrand.getName(), brandDTO.getName());
		assertEquals(newBrand.getCountry(), brandDTO.getCountry());
	}

	@Test
	public void testCreate() {
		final BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(10l);
		brandDTO.setName("name");
		brandDTO.setCountry("country");

		when(repository.save(brandCaptor.capture())).thenReturn(BrandMapper.mapToBrand(brandDTO, new Brand()));

		final BrandDTO returnedBrand = service.create(brandDTO);

		assertEquals(brandDTO.getId(), returnedBrand.getId(), 0);
		assertEquals(brandDTO.getName(), returnedBrand.getName());
		assertEquals(brandDTO.getCountry(), returnedBrand.getCountry());

		final Brand savedBrand = brandCaptor.getValue();
		assertEquals(brandDTO.getId(), savedBrand.getId(), 0);
		assertEquals(brandDTO.getName(), savedBrand.getName());
		assertEquals(brandDTO.getCountry(), savedBrand.getCountry());
	}

	@Test
	public void testDelete() {

		Brand brand = new Brand();
		brand.setId(15l);

		when(repository.findById(any(Long.class))).thenReturn(Optional.of(brand));

		service.delete(15l);
		verify(repository, times(1)).deleteById(brandIdCaptor.capture());
		assertEquals(15, brandIdCaptor.getValue(), 0);
	}

	@Test
	public void testUpdate() {
		final BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(15l);
		brandDTO.setName("name");
		brandDTO.setCountry("country");

		when(repository.save(brandCaptor.capture())).thenReturn(BrandMapper.mapToBrand(brandDTO, new Brand()));

		final BrandDTO updatedBrand = service.update(brandDTO);

		assertEquals(brandDTO.getId(), updatedBrand.getId(), 0);
		assertEquals(brandDTO.getName(), updatedBrand.getName());
		assertEquals(brandDTO.getCountry(), updatedBrand.getCountry());

		final Brand savedBrand = brandCaptor.getValue();
		assertEquals(brandDTO.getId(), savedBrand.getId(), 0);
		assertEquals(brandDTO.getName(), savedBrand.getName());
		assertEquals(brandDTO.getCountry(), savedBrand.getCountry());
	}

}
