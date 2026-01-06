package br.com.batista.service.impl;

import br.com.batista.dto.ad.request.*;
import br.com.batista.dto.ad.response.*;
import br.com.batista.dto.api.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

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

    public AdResponse create (CreateAdRequest adDTO) {
        Car car = carService.getEntityById(adDTO.getCarId());
        User user = userService.getById(adDTO.getUserId());
        Ad savedAd = adRepository.save(Ad.create(adDTO, car, user));
        return AdMapper.mapToAdResponseDto(savedAd);
    }

    public AdResponse update (UpdateAdRequest adDTO) {
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
    }

    public void delete (Long id) {
        Ad ad = adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ad", String.valueOf(id), "id"));
        adRepository.delete(ad);
    }
}