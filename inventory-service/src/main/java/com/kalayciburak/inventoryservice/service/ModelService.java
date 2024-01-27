package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.ModelRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.ModelResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.ModelWithCarsResponse;
import com.kalayciburak.inventoryservice.model.entity.Model;
import com.kalayciburak.inventoryservice.repository.ModelRepository;
import com.kalayciburak.inventoryservice.service.helper.BrandHelperService;
import com.kalayciburak.inventoryservice.service.helper.CarHelperService;
import com.kalayciburak.inventoryservice.util.mapper.ModelMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Model.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ModelService {
    private final ModelMapper mapper;
    private final ModelRepository repository;
    private final BrandHelperService brandService;
    private final CarHelperService carHelperService;

    public ResponseItem<ModelResponse> findById(Long id) {
        var model = findByIdOrThrow(id);
        var data = mapper.toDto(model);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<ModelWithCarsResponse> findByIdWithCars(Long id) {
        var model = findByIdOrThrow(id);
        var data = mapper.toDtoWithCars(model);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<ModelResponse>> findAll() {
        var models = repository.findAll();
        if (models.isEmpty()) createNotFoundResponse(NOT_FOUND);
        var data = models.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<List<ModelWithCarsResponse>> findAllWithCars() {
        var models = repository.findAll();
        if (models.isEmpty()) createNotFoundResponse(NOT_FOUND);
        var data = models.stream().map(mapper::toDtoWithCars).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<ModelResponse> create(ModelRequest request) {
        var model = mapper.toEntity(request);
        var brand = brandService.findEntityById(request.brandId());
        model.setBrand(brand);
        var savedModel = repository.save(model);
        var data = mapper.toDto(savedModel);

        return createSuccessResponse(data, SAVED);
    }

    public ResponseItem<ModelResponse> update(Long id, ModelRequest request) {
        var model = findByIdOrThrow(id);
        mapper.updateEntity(request, model);
        var savedModel = repository.save(model);
        var data = mapper.toDto(savedModel);

        return createSuccessResponse(data, UPDATED);
    }

    public void delete(Long id) {
        var model = findByIdOrThrow(id);
        deleteAvailableCarsFromModel(model);
        repository.softDeleteById(id);
    }

    /**
     * <b>Verilen id değerine göre model nesnesini bulur.</b>
     * <p>
     * Model bulunamazsa EntityNotFoundException fırlatır.
     *
     * @param id id değeri.
     * @return Model nesnesi.
     * @throws EntityNotFoundException Model bulunamazsa fırlatılır.
     */
    private Model findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }

    /**
     * <b>Model nesnesini kontrol eder ve içerdiği araçları denetler ve siler.</b>
     *
     * @param model Model nesnesi, içerdiği araçları kontrol etmek ve silmek için kullanılır.
     */
    private void deleteAvailableCarsFromModel(Model model) {
        Optional.ofNullable(model.getCars())
                .ifPresent(cars -> cars.forEach(carHelperService::validateAvailabilityAndDeleteCar));
    }
}
