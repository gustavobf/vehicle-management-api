package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.entity.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import org.junit.*;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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
    public void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll () {
        final List<Car> carList = new ArrayList<>();
        final Car newCar = new Car(50l, "color", 1500, 4, 1999, "pla", "name");
        carList.add(newCar);

        Pageable pageable = PageRequest.of(0, 10);

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(carList));

        final PageResponse<CarResponse> list = service.getAll(pageable);
        final CarResponse carDTO = list.getContent().get(0);
        assertEquals(newCar.getId(), carDTO.id(), 0);
        assertEquals(newCar.getName(), carDTO.name());
        assertEquals(newCar.getColor(), carDTO.color());
        assertEquals(newCar.getPower(), carDTO.power());
        assertEquals(newCar.getDoor(), carDTO.door());
        assertEquals(newCar.getManufacturing(), carDTO.manufacturing());
        assertEquals(newCar.getPlate(), carDTO.plate());
    }

    @Test
    public void testGetById () {
        final Car newCar = new Car(50l, "color", 1500, 4, 1999, "pla", "name");
        when(repository.findById(newCar.getId())).thenReturn(Optional.of(newCar));

        final CarResponse carDTO = service.getById(newCar.getId());
        assertEquals(newCar.getId(), carDTO.id(), 0);
        assertEquals(newCar.getName(), carDTO.name());
        assertEquals(newCar.getColor(), carDTO.color());
        assertEquals(newCar.getPower(), carDTO.power());
        assertEquals(newCar.getDoor(), carDTO.door());
        assertEquals(newCar.getManufacturing(), carDTO.manufacturing());
        assertEquals(newCar.getPlate(), carDTO.plate());
    }

    @Test
    public void testCreate () {
        final CreateCarRequest carDTO = new CreateCarRequest();
        carDTO.setName("");
        carDTO.setManufacturing(1999);
        carDTO.setPlate("a2");
        carDTO.setDoor(2);
        carDTO.setColor("color");
        carDTO.setPower(2500);
        carDTO.setBrandId(1l);
        carDTO.setDealershipId(1l);
        carDTO.setModelId(1l);

        when(repository.save(carCaptor.capture())).thenReturn(CarMapper.mapToCar(carDTO));
        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.of(new Brand()));
        when(modelRepository.findById(any(Long.class))).thenReturn(Optional.of(new Model()));
        when(dealershipRepository.findById(any(Long.class))).thenReturn(Optional.of(new Dealership()));

        final CarResponse returnedCar = service.create(carDTO);

        assertEquals(carDTO.getName(), returnedCar.name());
        assertEquals(carDTO.getColor(), returnedCar.color());
        assertEquals(carDTO.getPower(), returnedCar.power());
        assertEquals(carDTO.getDoor(), returnedCar.door());
        assertEquals(carDTO.getManufacturing(), returnedCar.manufacturing());
        assertEquals(carDTO.getPlate(), returnedCar.plate());

        final Car savedCar = carCaptor.getValue();
        assertEquals(carDTO.getName(), savedCar.getName());
        assertEquals(carDTO.getColor(), savedCar.getColor());
        assertEquals(carDTO.getPower(), savedCar.getPower());
        assertEquals(carDTO.getDoor(), savedCar.getDoor());
        assertEquals(carDTO.getManufacturing(), savedCar.getManufacturing());
        assertEquals(carDTO.getPlate(), savedCar.getPlate());
    }

    @Test
    public void testDelete () {

        Car car = new Car();
        car.setId(15l);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(car));

        service.delete(15l);
        verify(repository, times(1)).deleteById(carIdCaptor.capture());
        assertEquals(15, carIdCaptor.getValue(), 0);
    }

    @Test
    public void testUpdate () {
        final UpdateCarRequest carDTO = new UpdateCarRequest();
        carDTO.setId(70l);
        carDTO.setName("");
        carDTO.setManufacturing(1999);
        carDTO.setPlate("a25");
        carDTO.setDoor(2);
        carDTO.setColor("color");
        carDTO.setPower(2500);

        when(repository.save(carCaptor.capture())).thenReturn(CarMapper.mapToCar(carDTO));

        final CarResponse updatedCar = service.update(carDTO);
        assertEquals(carDTO.getId(), updatedCar.id(), 0);
        assertEquals(carDTO.getName(), updatedCar.name());
        assertEquals(carDTO.getColor(), updatedCar.color());
        assertEquals(carDTO.getPower(), updatedCar.power());
        assertEquals(carDTO.getDoor(), updatedCar.door());
        assertEquals(carDTO.getManufacturing(), updatedCar.manufacturing());
        assertEquals(carDTO.getPlate(), updatedCar.plate());

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
