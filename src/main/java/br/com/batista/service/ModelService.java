package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.model.*;
import br.com.batista.dto.model.request.*;
import br.com.batista.dto.model.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.entity.Car;
import br.com.batista.entity.Model;
import br.com.batista.exception.ResourceNotFoundException;
import br.com.batista.mapper.ModelMapper;
import br.com.batista.repository.CarRepository;
import br.com.batista.repository.ModelRepository;

@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final CarRepository carRepository;

    @Autowired
    public ModelService (ModelRepository modelRepository, CarRepository carRepository) {
        this.modelRepository = modelRepository;
        this.carRepository = carRepository;
    }

    public PageResponse<ModelResponse> getAll (Pageable pageable) {
        Page<Model> page = modelRepository.findAll(pageable);
        return new PageResponse<>(page.map(ModelMapper::mapToModelResponseDto));
    }

    public ModelResponse getById (final Long id) {
        final Optional<Model> model = modelRepository.findById(id);
        return ModelMapper.mapToModelResponseDto(model.get());
    }

    public ModelResponse create (final CreateModelRequest modelDTO) {
        final Model model = ModelMapper.mapToModel(modelDTO);
        final Model savedModel = modelRepository.save(model);
        return ModelMapper.mapToModelResponseDto(savedModel);
    }

    public void delete (final Long id) {

        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", String.valueOf(id), "id"));

        List<Car> cars = carRepository.findByModel(model);

        if (!cars.isEmpty()) {
            carRepository.deleteAll(cars);
        }

        modelRepository.deleteById(id);
    }

    public ModelResponse update (final UpdateModelRequest modelDTO) {
        final Model model = ModelMapper.mapToModel(modelDTO);
        final Model savedModel = modelRepository.save(model);
        return ModelMapper.mapToModelResponseDto(savedModel);
    }

}
