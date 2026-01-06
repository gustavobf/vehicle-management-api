package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.model.request.*;
import br.com.batista.dto.model.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import jakarta.transaction.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private static final Logger logger = LoggerFactory.getLogger(ModelServiceImpl.class);

    private final ModelRepository modelRepository;
    private final CarRepository carRepository;

    public PageResponse<ModelResponse> getAll (Pageable pageable) {
        Page<Model> page = modelRepository.findAll(pageable);
        return new PageResponse<>(page.map(ModelMapper::mapToModelResponseDto));
    }

    public Model getEntityById (final Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", String.valueOf(id), "id"));
    }

    public ModelResponse getById (final Long id) {
        return ModelMapper.mapToModelResponseDto(this.getEntityById(id));
    }

    @Transactional
    public ModelResponse create (final CreateModelRequest modelDTO) {
        try {
            final Model model = ModelMapper.mapToModel(modelDTO);
            final Model savedModel = modelRepository.save(model);
            return ModelMapper.mapToModelResponseDto(savedModel);
        } catch (Exception e) {
            logger.error("Error creating model: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete (final Long id) {
        try {
            Model model = modelRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Model", String.valueOf(id), "id"));

            List<Car> cars = carRepository.findByModel(model);

            if (!cars.isEmpty()) {
                carRepository.deleteAll(cars);
            }

            modelRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting model with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public ModelResponse update (final UpdateModelRequest modelDTO) {
        try {
            final Model model = ModelMapper.mapToModel(modelDTO);
            final Model savedModel = modelRepository.save(model);
            return ModelMapper.mapToModelResponseDto(savedModel);
        } catch (Exception e) {
            logger.error("Error updating model: {}", e.getMessage());
            throw e;
        }
    }

}
