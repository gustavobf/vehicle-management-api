package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
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
    public void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll () {
        final List<Brand> brandList = new ArrayList<>();
        final Brand newBrand = new Brand(10l, "name", "country");
        brandList.add(newBrand);

        Pageable pageable = PageRequest.of(0, 10);

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(brandList));

        final PageResponse<BrandResponse> list = service.getAll(pageable);
        final BrandResponse brandDTO = list.getContent().get(0);
        assertEquals(newBrand.getId(), brandDTO.id(), 0);
        assertEquals(newBrand.getName(), brandDTO.name());
        assertEquals(newBrand.getCountry(), brandDTO.country());
    }

    @Test
    public void testGetById () {
        final Brand newBrand = new Brand(10l, "name", "country");
        when(repository.findById(newBrand.getId())).thenReturn(Optional.of(newBrand));

        final BrandResponse brandDTO = service.getById(newBrand.getId());
        assertEquals(newBrand.getId(), brandDTO.id(), 0);
        assertEquals(newBrand.getName(), brandDTO.name());
        assertEquals(newBrand.getCountry(), brandDTO.country());
    }

    @Test
    public void testCreate () {
        final CreateBrandRequest brandDTO = new CreateBrandRequest();
        brandDTO.setName("name");
        brandDTO.setCountry("country");

        when(repository.save(brandCaptor.capture())).thenReturn(BrandMapper.mapToBrand(brandDTO));

        final BrandResponse returnedBrand = service.create(brandDTO);

        assertEquals(brandDTO.getName(), returnedBrand.name());
        assertEquals(brandDTO.getCountry(), returnedBrand.country());

        final Brand savedBrand = brandCaptor.getValue();
        assertEquals(brandDTO.getName(), savedBrand.getName());
        assertEquals(brandDTO.getCountry(), savedBrand.getCountry());
    }

    @Test
    public void testDelete () {

        Brand brand = new Brand();
        brand.setId(15l);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(brand));

        service.delete(15l);
        verify(repository, times(1)).deleteById(brandIdCaptor.capture());
        assertEquals(15, brandIdCaptor.getValue(), 0);
    }

    @Test
    public void testUpdate () {
        final UpdateBrandRequest brandDTO = new UpdateBrandRequest();
        brandDTO.setId(15l);
        brandDTO.setName("name");
        brandDTO.setCountry("country");

        when(repository.save(brandCaptor.capture())).thenReturn(BrandMapper.mapToBrand(brandDTO));

        final BrandResponse updatedBrand = service.update(brandDTO);

        assertEquals(brandDTO.getId(), updatedBrand.id(), 0);
        assertEquals(brandDTO.getName(), updatedBrand.name());
        assertEquals(brandDTO.getCountry(), updatedBrand.country());

        final Brand savedBrand = brandCaptor.getValue();
        assertEquals(brandDTO.getId(), savedBrand.getId(), 0);
        assertEquals(brandDTO.getName(), savedBrand.getName());
        assertEquals(brandDTO.getCountry(), savedBrand.getCountry());
    }

}
