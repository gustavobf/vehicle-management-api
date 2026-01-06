package br.com.batista.service.impl;

import br.com.batista.dto.ad.request.*;
import br.com.batista.dto.ad.response.*;
import br.com.batista.dto.api.response.*;
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

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private static final Logger logger = LoggerFactory.getLogger(AdServiceImpl.class);

    private final AdRepository adRepository;
    private final CarService carService;
    private final UserService userService;

    public PageResponse<AdResponse> getAll (Pageable pageable) {
        Page<Ad> page = adRepository.findAll(pageable);
        return new PageResponse<>(page.map(AdMapper::mapToAdResponseDto));
    }

    public PageResponse<AdResponse> getAllActive (Pageable pageable) {
        Page<Ad> page = adRepository.findAllByActiveTrue(pageable);
        return new PageResponse<>(page.map(AdMapper::mapToAdResponseDto));
    }

    public Ad getEntityById (Long id) {
        return adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ad", String.valueOf(id), "id"));
    }

    public AdResponse getById (Long id) {
        return AdMapper.mapToAdResponseDto(this.getEntityById(id));
    }

    @Transactional
    public AdResponse create (CreateAdRequest adDTO) {
        try {
            Car car = carService.getEntityById(adDTO.getCarId());
            User user = userService.getById(adDTO.getUserId());
            Ad savedAd = adRepository.save(Ad.create(adDTO, car, user));
            return AdMapper.mapToAdResponseDto(savedAd);
        } catch (Exception e) {
            logger.error("Error creating Ad: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public AdResponse update (UpdateAdRequest adDTO) {
        try {
            Ad existingAd = this.getEntityById(adDTO.getId());
            Car car = carService.getEntityById(adDTO.getCarId());
            User user = userService.getById(adDTO.getUserId());

            existingAd.setActive(adDTO.isActive());
            existingAd.setDescription(adDTO.getDescription());
            existingAd.setPrice(adDTO.getPrice());
            existingAd.setCar(car);
            existingAd.setUser(user);

            Ad updatedAd = adRepository.save(existingAd);
            return AdMapper.mapToAdResponseDto(updatedAd);
        } catch (Exception e) {
            logger.error("Error updating Ad with id {}: {}", adDTO.getId(), e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete (Long id) {
        try {
            Ad ad = adRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Ad", String.valueOf(id), "id"));
            adRepository.delete(ad);
        } catch (Exception e) {
            logger.error("Error deleting Ad with id {}: {}", id, e.getMessage());
            throw e;
        }
    }
}