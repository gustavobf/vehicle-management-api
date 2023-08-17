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

import br.com.batista.dto.ModelDTO;
import br.com.batista.model.Model;
import br.com.batista.repositories.ModelRepository;

public class ModelServiceTest {

	@Mock
	ModelRepository repository;

	@InjectMocks
	ModelService service;

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

		when(repository.findAll()).thenReturn(modelList);

		final List<ModelDTO> list = service.getAll();
		final ModelDTO ModelDTO1 = list.get(0);
		assertEquals(newModel.getNome(), ModelDTO1.getName());
	}

	@Test
	public void testGetById() {
		final Model newModel = new Model(1l, "name");
		when(repository.findById(newModel.getId())).thenReturn(Optional.of(newModel));

		final Optional<ModelDTO> returnedModel = service.getById(newModel.getId());
		final ModelDTO modelDTO = returnedModel.get();
		assertEquals(newModel.getNome(), modelDTO.getName());
	}

	@Test
	public void testCreate() {
		final ModelDTO modelDTO = new ModelDTO();
		modelDTO.setName("name");

		when(repository.save(modelCaptor.capture())).thenReturn(service.convertToEntity(modelDTO));

		final ModelDTO returnedModel = service.create(modelDTO);

		assertEquals(modelDTO.getName(), returnedModel.getName());

		final Model savedModel = modelCaptor.getValue();
		assertEquals(modelDTO.getName(), savedModel.getNome());
	}

	@Test
	public void testDelete() {
		service.delete(10l);
		verify(repository, times(1)).deleteById(modelIdCaptor.capture());
		assertEquals(10, modelIdCaptor.getValue(), 0);
	}

	@Test
	public void testUpdate() {
		final ModelDTO modelDTO = new ModelDTO();
		modelDTO.setName("name");

		when(repository.save(modelCaptor.capture())).thenReturn(service.convertToEntity(modelDTO));

		final ModelDTO updatedModel = service.update(modelDTO);
		assertEquals(modelDTO.getName(), updatedModel.getName());

		final Model savedModel = modelCaptor.getValue();
		assertEquals(modelDTO.getName(), savedModel.getNome());
	}

}
