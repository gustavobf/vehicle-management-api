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
		assertEquals(newCar.getName(), carDTO.getName());
		assertEquals(newCar.getColor(), carDTO.getColor());
		assertEquals(newCar.getPower(), carDTO.getPower());
		assertEquals(newCar.getDoor(), carDTO.getDoor());
		assertEquals(newCar.getManufacturing(), carDTO.getManufacturing());
		assertEquals(newCar.getPlate(), carDTO.getPlate());
	}

	@Test
	public void testGetById() {
		final Car newCar = new Car(50l, "color", 1500, 4, 1999, "pla", "name");
		when(repository.findById(newCar.getId())).thenReturn(Optional.of(newCar));

		final Optional<CarDTO> returnedCar = service.getById(newCar.getId());
		final CarDTO carDTO = returnedCar.get();
		assertEquals(newCar.getId(), carDTO.getId(), 0);
		assertEquals(newCar.getName(), carDTO.getName());
		assertEquals(newCar.getColor(), carDTO.getColor());
		assertEquals(newCar.getPower(), carDTO.getPower());
		assertEquals(newCar.getDoor(), carDTO.getDoor());
		assertEquals(newCar.getManufacturing(), carDTO.getManufacturing());
		assertEquals(newCar.getPlate(), carDTO.getPlate());
	}

	@Test
	public void testCreate() {
		final CarDTO carDTO = new CarDTO();
		carDTO.setId(70l);
		carDTO.setName("");
		carDTO.setManufacturing(1999);
		carDTO.setPlate("a2");
		carDTO.setDoor(2);
		carDTO.setColor("color");
		carDTO.setPower(2500);

		when(repository.save(carCaptor.capture())).thenReturn(service.convertToEntity(carDTO));

		final CarDTO returnedCar = service.create(carDTO);

		assertEquals(carDTO.getId(), returnedCar.getId(), 0);
		assertEquals(carDTO.getId(), returnedCar.getId(), 0);
		assertEquals(carDTO.getName(), returnedCar.getName());
		assertEquals(carDTO.getColor(), returnedCar.getColor());
		assertEquals(carDTO.getPower(), returnedCar.getPower());
		assertEquals(carDTO.getDoor(), returnedCar.getDoor());
		assertEquals(carDTO.getManufacturing(), returnedCar.getManufacturing());
		assertEquals(carDTO.getPlate(), returnedCar.getPlate());

		final Car savedCar = carCaptor.getValue();
		assertEquals(carDTO.getId(), savedCar.getId(), 0);
		assertEquals(carDTO.getName(), savedCar.getName());
		assertEquals(carDTO.getColor(), savedCar.getColor());
		assertEquals(carDTO.getPower(), savedCar.getPower());
		assertEquals(carDTO.getDoor(), savedCar.getDoor());
		assertEquals(carDTO.getManufacturing(), savedCar.getManufacturing());
		assertEquals(carDTO.getPlate(), savedCar.getPlate());
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
		carDTO.setName("");
		carDTO.setManufacturing(1999);
		carDTO.setPlate("a25");
		carDTO.setDoor(2);
		carDTO.setColor("color");
		carDTO.setPower(2500);

		when(repository.save(carCaptor.capture())).thenReturn(service.convertToEntity(carDTO));

		final CarDTO updatedCar = service.update(carDTO);
		assertEquals(carDTO.getId(), updatedCar.getId(), 0);
		assertEquals(carDTO.getName(), updatedCar.getName());
		assertEquals(carDTO.getColor(), updatedCar.getColor());
		assertEquals(carDTO.getPower(), updatedCar.getPower());
		assertEquals(carDTO.getDoor(), updatedCar.getDoor());
		assertEquals(carDTO.getManufacturing(), updatedCar.getManufacturing());
		assertEquals(carDTO.getPlate(), updatedCar.getPlate());

		final Car savedCar = carCaptor.getValue();
		assertEquals(carDTO.getId(), savedCar.getId(), 0);
		assertEquals(carDTO.getName(), savedCar.getName());
		assertEquals(carDTO.getColor(), savedCar.getColor());
		assertEquals(carDTO.getPower(), savedCar.getPower());
		assertEquals(carDTO.getDoor(), savedCar.getDoor());
		assertEquals(carDTO.getManufacturing(), savedCar.getManufacturing());
		assertEquals(carDTO.getPlate(), savedCar.getPlate());
	}

}
