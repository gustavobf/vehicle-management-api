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

import br.com.batista.dto.CarDTO;
import br.com.batista.model.Car;
import br.com.batista.repositories.CarRepository;

public class CarServiceTest {

	@Mock
	CarRepository repository;

	@InjectMocks
	CarService service;

	@Captor
	ArgumentCaptor<Car> carCaptor;

	@Captor
	ArgumentCaptor<Long> carIdCaptor;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAll() {
		final List<Car> carList = new ArrayList<>();
		final Car newCar = new Car(50l, "color", 1500, 4, 1999, "pla", "name");
		carList.add(newCar);

		when(repository.findAll()).thenReturn(carList);

		final List<CarDTO> list = service.getAll();
		final CarDTO carDTO = list.get(0);
		assertEquals(newCar.getId(), carDTO.getId(), 0);
		assertEquals(newCar.getNome(), carDTO.getNome());
		assertEquals(newCar.getCor(), carDTO.getCor());
		assertEquals(newCar.getPotencia(), carDTO.getPotencia());
		assertEquals(newCar.getPortas(), carDTO.getPortas());
		assertEquals(newCar.getAno(), carDTO.getAno());
		assertEquals(newCar.getPlaca(), carDTO.getPlaca());
	}

	@Test
	public void testGetById() {
		final Car newCar = new Car(50l, "color", 1500, 4, 1999, "pla", "name");
		when(repository.findById(newCar.getId())).thenReturn(Optional.of(newCar));

		final Optional<CarDTO> returnedCar = service.getById(newCar.getId());
		final CarDTO carDTO = returnedCar.get();
		assertEquals(newCar.getId(), carDTO.getId(), 0);
		assertEquals(newCar.getNome(), carDTO.getNome());
		assertEquals(newCar.getCor(), carDTO.getCor());
		assertEquals(newCar.getPotencia(), carDTO.getPotencia());
		assertEquals(newCar.getPortas(), carDTO.getPortas());
		assertEquals(newCar.getAno(), carDTO.getAno());
		assertEquals(newCar.getPlaca(), carDTO.getPlaca());
	}

	@Test
	public void testCreate() {
		final CarDTO carDTO = new CarDTO();
		carDTO.setId(70l);
		carDTO.setNome("");
		carDTO.setAno(1999);
		carDTO.setPlaca("a2");
		carDTO.setPortas(2);
		carDTO.setCor("color");
		carDTO.setPotencia(2500);

		when(repository.save(carCaptor.capture())).thenReturn(service.convertToEntity(carDTO));

		final CarDTO returnedCar = service.create(carDTO);

		assertEquals(carDTO.getId(), returnedCar.getId(), 0);
		assertEquals(carDTO.getId(), returnedCar.getId(), 0);
		assertEquals(carDTO.getNome(), returnedCar.getNome());
		assertEquals(carDTO.getCor(), returnedCar.getCor());
		assertEquals(carDTO.getPotencia(), returnedCar.getPotencia());
		assertEquals(carDTO.getPortas(), returnedCar.getPortas());
		assertEquals(carDTO.getAno(), returnedCar.getAno());
		assertEquals(carDTO.getPlaca(), returnedCar.getPlaca());

		final Car savedCar = carCaptor.getValue();
		assertEquals(carDTO.getId(), savedCar.getId(), 0);
		assertEquals(carDTO.getNome(), savedCar.getNome());
		assertEquals(carDTO.getCor(), savedCar.getCor());
		assertEquals(carDTO.getPotencia(), savedCar.getPotencia());
		assertEquals(carDTO.getPortas(), savedCar.getPortas());
		assertEquals(carDTO.getAno(), savedCar.getAno());
		assertEquals(carDTO.getPlaca(), savedCar.getPlaca());
	}

	@Test
	public void testDelete() {
		service.delete(15l);
		verify(repository, times(1)).deleteById(carIdCaptor.capture());
		assertEquals(15, carIdCaptor.getValue(), 0);
	}

	@Test
	public void testUpdate() {
		final CarDTO carDTO = new CarDTO();
		carDTO.setId(70l);
		carDTO.setNome("");
		carDTO.setAno(1999);
		carDTO.setPlaca("a25");
		carDTO.setPortas(2);
		carDTO.setCor("color");
		carDTO.setPotencia(2500);

		when(repository.save(carCaptor.capture())).thenReturn(service.convertToEntity(carDTO));

		final CarDTO updatedCar = service.update(carDTO);
		assertEquals(carDTO.getId(), updatedCar.getId(), 0);
		assertEquals(carDTO.getId(), updatedCar.getId(), 0);
		assertEquals(carDTO.getNome(), updatedCar.getNome());
		assertEquals(carDTO.getCor(), updatedCar.getCor());
		assertEquals(carDTO.getPotencia(), updatedCar.getPotencia());
		assertEquals(carDTO.getPortas(), updatedCar.getPortas());
		assertEquals(carDTO.getAno(), updatedCar.getAno());
		assertEquals(carDTO.getPlaca(), updatedCar.getPlaca());

		final Car savedCar = carCaptor.getValue();
		assertEquals(carDTO.getId(), savedCar.getId(), 0);
		assertEquals(carDTO.getId(), savedCar.getId(), 0);
		assertEquals(carDTO.getNome(), savedCar.getNome());
		assertEquals(carDTO.getCor(), savedCar.getCor());
		assertEquals(carDTO.getPotencia(), savedCar.getPotencia());
		assertEquals(carDTO.getPortas(), savedCar.getPortas());
		assertEquals(carDTO.getAno(), savedCar.getAno());
		assertEquals(carDTO.getPlaca(), savedCar.getPlaca());
	}

}
