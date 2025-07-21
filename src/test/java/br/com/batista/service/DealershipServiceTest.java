package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
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
    public void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll () {
        final List<Dealership> dealershipList = new ArrayList<>();
        final Dealership newDealership = new Dealership(105l, "111", "name");
        dealershipList.add(newDealership);

        Pageable pageable = PageRequest.of(0, 10);

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(dealershipList));

        final PageResponse<DealershipResponse> list = service.getAll(pageable);
        final DealershipResponse dealershipDTO = list.getContent().get(0);
        assertEquals(newDealership.getId(), dealershipDTO.id(), 0);
        assertEquals(newDealership.getCnpj(), dealershipDTO.cnpj());
        assertEquals(newDealership.getName(), dealershipDTO.name());
    }

    @Test
    public void testGetById () {
        final Dealership newDealership = new Dealership(10l, "name", "country");
        when(repository.findById(newDealership.getId())).thenReturn(Optional.of(newDealership));

        final DealershipResponse dealershipDTO = service.getById(newDealership.getId());
        assertEquals(newDealership.getId(), dealershipDTO.id(), 0);
        assertEquals(newDealership.getCnpj(), dealershipDTO.cnpj());
        assertEquals(newDealership.getName(), dealershipDTO.name());
    }

    @Test
    public void testCreate () {
        final CreateDealershipRequest dealershipDTO = new CreateDealershipRequest();
        dealershipDTO.setName("name");
        dealershipDTO.setCnpj("123");

        when(repository.save(dealershipCaptor.capture())).thenReturn(DealershipMapper.mapToDealership(dealershipDTO));

        final DealershipResponse returnedDealership = service.create(dealershipDTO);
        assertEquals(dealershipDTO.getCnpj(), returnedDealership.cnpj());
        assertEquals(dealershipDTO.getName(), returnedDealership.name());

        final Dealership dealershipSaved = dealershipCaptor.getValue();
        assertEquals(dealershipDTO.getCnpj(), dealershipSaved.getCnpj());
        assertEquals(dealershipDTO.getName(), dealershipSaved.getName());
    }

    @Test
    public void testDelete () {

        Dealership dealership = new Dealership();
        dealership.setId(15l);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(dealership));

        service.delete(15l);
        verify(repository, times(1)).deleteById(dealershipIdCaptor.capture());
        assertEquals(15, dealershipIdCaptor.getValue(), 0);
    }

    @Test
    public void testUpdate () {
        final UpdateDealershipRequest dealershipDTO = new UpdateDealershipRequest();
        dealershipDTO.setId(10l);
        dealershipDTO.setName("name");
        dealershipDTO.setCnpj("123");

        when(repository.save(dealershipCaptor.capture())).thenReturn(DealershipMapper.mapToDealership(dealershipDTO));

        final DealershipResponse updatedDealership = service.update(dealershipDTO);

        assertEquals(dealershipDTO.getId(), updatedDealership.id(), 0);
        assertEquals(dealershipDTO.getCnpj(), updatedDealership.cnpj());
        assertEquals(dealershipDTO.getName(), updatedDealership.name());

        final Dealership savedDealership = dealershipCaptor.getValue();
        assertEquals(dealershipDTO.getId(), savedDealership.getId(), 0);
        assertEquals(dealershipDTO.getCnpj(), savedDealership.getCnpj());
        assertEquals(dealershipDTO.getName(), savedDealership.getName());
    }

}
