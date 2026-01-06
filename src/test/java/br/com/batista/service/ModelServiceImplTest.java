package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.model.request.*;
import br.com.batista.dto.model.response.*;
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

public class ModelServiceImplTest {

    @InjectMocks
    ModelService service;

    @Mock
    ModelRepository repository;

    @Mock
    CarRepository carRepository;

    @Captor
    ArgumentCaptor<Model> modelCaptor;

    @Captor
    ArgumentCaptor<Long> modelIdCaptor;

    @Before
    public void setUp () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll () {
        final List<Model> modelList = new ArrayList<>();
        final Model newModel = new Model(1l, "name");
        modelList.add(newModel);

        Pageable pageable = PageRequest.of(0, 10);

        when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(modelList));

        final PageResponse<ModelResponse> list = service.getAll(pageable);
        final ModelResponse modelDTO1 = list.getContent().get(0);
        assertEquals(newModel.getName(), modelDTO1.name());
    }

    @Test
    public void testGetById () {
        final Model newModel = new Model(1l, "name");
        when(repository.findById(newModel.getId())).thenReturn(Optional.of(newModel));

        final ModelResponse modelDTO = service.getById(newModel.getId());
        assertEquals(newModel.getName(), modelDTO.name());
    }

    @Test
    public void testCreate () {
        final CreateModelRequest modelDTO = new CreateModelRequest();
        modelDTO.setName("name");

        when(repository.save(modelCaptor.capture())).thenReturn(ModelMapper.mapToModel(modelDTO));

        final ModelResponse returnedModel = service.create(modelDTO);

        assertEquals(modelDTO.getName(), returnedModel.name());

        final Model savedModel = modelCaptor.getValue();
        assertEquals(modelDTO.getName(), savedModel.getName());
    }

    @Test
    public void testDelete () {

        Model model = new Model();
        model.setId(15l);

        when(repository.findById(any(Long.class))).thenReturn(Optional.of(model));

        service.delete(10l);
        verify(repository, times(1)).deleteById(modelIdCaptor.capture());
        assertEquals(10, modelIdCaptor.getValue(), 0);
    }

    @Test
    public void testUpdate () {
        final UpdateModelRequest modelDTO = new UpdateModelRequest();
        modelDTO.setName("name");

        when(repository.save(modelCaptor.capture())).thenReturn(ModelMapper.mapToModel(modelDTO));

        final ModelResponse updatedModel = service.update(modelDTO);
        assertEquals(modelDTO.getName(), updatedModel.name());

        final Model savedModel = modelCaptor.getValue();
        assertEquals(modelDTO.getName(), savedModel.getName());
    }

}
