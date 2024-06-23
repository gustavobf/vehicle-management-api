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

import br.com.batista.dto.ModelDTO;
import br.com.batista.entity.Model;
import br.com.batista.mapper.ModelMapper;
import br.com.batista.repository.CarRepository;
import br.com.batista.repository.ModelRepository;

public class ModelServiceTest {

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
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAll() {
		final List<Model> modelList = new ArrayList<>();
		final Model newModel = new Model(1l, "name");
		modelList.add(newModel);

		Pageable pageable = PageRequest.of(0, 10);

		when(repository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(modelList));

		final Page<ModelDTO> list = service.getAll(pageable);
		final ModelDTO ModelDTO1 = list.getContent().get(0);
		assertEquals(newModel.getName(), ModelDTO1.getName());
	}

	@Test
	public void testGetById() {
		final Model newModel = new Model(1l, "name");
		when(repository.findById(newModel.getId())).thenReturn(Optional.of(newModel));

		final ModelDTO modelDTO = service.getById(newModel.getId());
		assertEquals(newModel.getName(), modelDTO.getName());
	}

	@Test
	public void testCreate() {
		final ModelDTO modelDTO = new ModelDTO();
		modelDTO.setName("name");

		when(repository.save(modelCaptor.capture())).thenReturn(ModelMapper.mapToModel(modelDTO, new Model()));

		final ModelDTO returnedModel = service.create(modelDTO);

		assertEquals(modelDTO.getName(), returnedModel.getName());

		final Model savedModel = modelCaptor.getValue();
		assertEquals(modelDTO.getName(), savedModel.getName());
	}

	@Test
	public void testDelete() {

		Model model = new Model();
		model.setId(15l);

		when(repository.findById(any(Long.class))).thenReturn(Optional.of(model));

		service.delete(10l);
		verify(repository, times(1)).deleteById(modelIdCaptor.capture());
		assertEquals(10, modelIdCaptor.getValue(), 0);
	}

	@Test
	public void testUpdate() {
		final ModelDTO modelDTO = new ModelDTO();
		modelDTO.setName("name");

		when(repository.save(modelCaptor.capture())).thenReturn(ModelMapper.mapToModel(modelDTO, new Model()));

		final ModelDTO updatedModel = service.update(modelDTO);
		assertEquals(modelDTO.getName(), updatedModel.getName());

		final Model savedModel = modelCaptor.getValue();
		assertEquals(modelDTO.getName(), savedModel.getName());
	}

}
