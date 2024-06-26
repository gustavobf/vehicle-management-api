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

import br.com.batista.dto.CarDTO;
import br.com.batista.dto.RequestCarDTO;
import br.com.batista.dto.ResponseCarDTO;
import br.com.batista.entity.Brand;
import br.com.batista.entity.Car;
import br.com.batista.entity.Dealership;
import br.com.batista.entity.Model;
import br.com.batista.mapper.CarMapper;
import br.com.batista.repository.BrandRepository;
import br.com.batista.repository.CarRepository;
import br.com.batista.repository.DealershipRepository;
import br.com.batista.repository.ModelRepository;

public class CarServiceTest {

	@InjectMocks
	CarService service;

	@Mock
	CarRepository repository;

	@Mock
	BrandRepository brandRepository;

	@Mock
	ModelRepository modelRepository;

	@Mock
	DealershipRepository dealershipRepository;

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

		Pageable pageable = PageRequest.of(0, 10);

		when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(carList));

		final Page<ResponseCarDTO> list = service.getAll(pageable);
		final ResponseCarDTO carDTO = list.getContent().get(0);
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

		final CarDTO carDTO = service.getById(newCar.getId());
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
		final RequestCarDTO carDTO = new RequestCarDTO();
		carDTO.setId(70l);
		carDTO.setName("");
		carDTO.setManufacturing(1999);
		carDTO.setPlate("a2");
		carDTO.setDoor(2);
		carDTO.setColor("color");
		carDTO.setPower(2500);
		carDTO.setBrandId(1l);
		carDTO.setDealershipId(1l);
		carDTO.setModelId(1l);

		when(repository.save(carCaptor.capture())).thenReturn(CarMapper.mapToCar(carDTO, new Car()));
		when(brandRepository.findById(any(Long.class))).thenReturn(Optional.of(new Brand()));
		when(modelRepository.findById(any(Long.class))).thenReturn(Optional.of(new Model()));
		when(dealershipRepository.findById(any(Long.class))).thenReturn(Optional.of(new Dealership()));

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

		Car car = new Car();
		car.setId(15l);

		when(repository.findById(any(Long.class))).thenReturn(Optional.of(car));

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

		when(repository.save(carCaptor.capture())).thenReturn(CarMapper.mapToCar(carDTO, new Car()));

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
