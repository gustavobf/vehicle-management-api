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

import br.com.batista.dto.DealershipDTO;
import br.com.batista.entity.Dealership;
import br.com.batista.mapper.DealershipMapper;
import br.com.batista.repository.CarRepository;
import br.com.batista.repository.DealershipRepository;

public class DealershipServiceTest {

	@InjectMocks
	DealershipService service;

	@Mock
	DealershipRepository repository;

	@Mock
	CarRepository carRepository;

	@Captor
	ArgumentCaptor<Dealership> dealershipCaptor;

	@Captor
	ArgumentCaptor<Long> dealershipIdCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAll() {
		final List<Dealership> dealershipList = new ArrayList<>();
		final Dealership newDealership = new Dealership(105l, "111", "name");
		dealershipList.add(newDealership);

		Pageable pageable = PageRequest.of(0, 10);

		when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(dealershipList));

		final Page<DealershipDTO> list = service.getAll(pageable);
		final DealershipDTO dealershipDTO = list.getContent().get(0);
		assertEquals(newDealership.getId(), dealershipDTO.getId(), 0);
		assertEquals(newDealership.getCnpj(), dealershipDTO.getCnpj());
		assertEquals(newDealership.getName(), dealershipDTO.getName());
	}

	@Test
	public void testGetById() {
		final Dealership newDealership = new Dealership(10l, "name", "country");
		when(repository.findById(newDealership.getId())).thenReturn(Optional.of(newDealership));

		final DealershipDTO dealershipDTO = service.getById(newDealership.getId());
		assertEquals(newDealership.getId(), dealershipDTO.getId(), 0);
		assertEquals(newDealership.getCnpj(), dealershipDTO.getCnpj());
		assertEquals(newDealership.getName(), dealershipDTO.getName());
	}

	@Test
	public void testCreate() {
		final DealershipDTO dealershipDTO = new DealershipDTO();
		dealershipDTO.setId(10l);
		dealershipDTO.setName("name");
		dealershipDTO.setCnpj("123");

		when(repository.save(dealershipCaptor.capture()))
		.thenReturn(DealershipMapper.mapToDealership(dealershipDTO, new Dealership()));

		final DealershipDTO returnedDealership = service.create(dealershipDTO);
		assertEquals(dealershipDTO.getId(), dealershipDTO.getId(), 0);
		assertEquals(dealershipDTO.getCnpj(), returnedDealership.getCnpj());
		assertEquals(dealershipDTO.getName(), returnedDealership.getName());

		final Dealership dealershipSaved = dealershipCaptor.getValue();
		assertEquals(dealershipDTO.getId(), dealershipSaved.getId(), 0);
		assertEquals(dealershipDTO.getCnpj(), dealershipSaved.getCnpj());
		assertEquals(dealershipDTO.getName(), dealershipSaved.getName());
	}

	@Test
	public void testDelete() {

		Dealership dealership = new Dealership();
		dealership.setId(15l);

		when(repository.findById(any(Long.class))).thenReturn(Optional.of(dealership));

		service.delete(15l);
		verify(repository, times(1)).deleteById(dealershipIdCaptor.capture());
		assertEquals(15, dealershipIdCaptor.getValue(), 0);
	}

	@Test
	public void testUpdate() {
		final DealershipDTO dealershipDTO = new DealershipDTO();
		dealershipDTO.setId(10l);
		dealershipDTO.setName("name");
		dealershipDTO.setCnpj("123");

		when(repository.save(dealershipCaptor.capture()))
		.thenReturn(DealershipMapper.mapToDealership(dealershipDTO, new Dealership()));

		final DealershipDTO updatedDealership = service.update(dealershipDTO);

		assertEquals(dealershipDTO.getId(), updatedDealership.getId(), 0);
		assertEquals(dealershipDTO.getCnpj(), updatedDealership.getCnpj());
		assertEquals(dealershipDTO.getName(), updatedDealership.getName());

		final Dealership savedDealership = dealershipCaptor.getValue();
		assertEquals(dealershipDTO.getId(), savedDealership.getId(), 0);
		assertEquals(dealershipDTO.getCnpj(), savedDealership.getCnpj());
		assertEquals(dealershipDTO.getName(), savedDealership.getName());
	}

}
