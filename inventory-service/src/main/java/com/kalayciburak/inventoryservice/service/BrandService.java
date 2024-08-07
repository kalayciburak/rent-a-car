package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.broker.kafka.producer.BaseProducer;
import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.commonpackage.util.event.inventory.BrandDeletedEvent;
import com.kalayciburak.commonpackage.util.event.inventory.BrandUpdatedEvent;
import com.kalayciburak.inventoryservice.advice.exception.BrandNotFoundException;
import com.kalayciburak.inventoryservice.model.dto.request.BrandRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.BrandResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.BrandWithModelsResponse;
import com.kalayciburak.inventoryservice.model.entity.Brand;
import com.kalayciburak.inventoryservice.model.entity.Model;
import com.kalayciburak.inventoryservice.repository.BrandRepository;
import com.kalayciburak.inventoryservice.util.mapper.BrandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Brand.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {
    private final BrandMapper mapper;
    private final BaseProducer producer;
    private final ModelService modelService;
    private final BrandRepository repository;

    public ResponseItem<BrandResponse> findById(Long id) {
        var brand = findByIdOrThrow(id);
        var data = mapper.toDto(brand);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<BrandWithModelsResponse> findByIdWithModels(Long id) {
        var brand = findByIdOrThrow(id);
        var data = mapper.toDtoWithModels(brand);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<BrandResponse>> findAll() {
        var brands = repository.findAll();
        if (brands.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = brands.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<List<BrandWithModelsResponse>> findAllWithModels() {
        var brands = repository.findAll();
        if (brands.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = brands.stream().map(mapper::toDtoWithModels).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<BrandResponse> create(BrandRequest request) {
        var brand = mapper.toEntity(request);
        var savedBrand = repository.save(brand);
        var data = mapper.toDto(savedBrand);

        return createSuccessResponse(data, SAVED);
    }

    public ResponseItem<BrandResponse> update(Long id, BrandRequest request) {
        var brand = findByIdOrThrow(id);
        mapper.updateEntity(request, brand);
        var savedBrand = repository.save(brand);
        var data = mapper.toDto(savedBrand);
        produceBrandUpdatedEvent(data);

        return createSuccessResponse(data, UPDATED);
    }

    public void delete(Long id) {
        var brand = findByIdOrThrow(id);
        deleteModelsAndAssociatedCars(brand.getModels());
        repository.softDeleteById(id);
        produceBrandDeletedEvent(id);
    }

    /**
     * <b>Verilen id değerine göre marka nesnesini bulur.</b>
     * <p>
     * Marka bulunamazsa EntityNotFoundException fırlatır.
     *
     * @param id id değeri.
     * @return Marka nesnesi.
     * @throws BrandNotFoundException Marka bulunamazsa fırlatılır.
     */
    private Brand findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new BrandNotFoundException(NOT_FOUND));
    }

    /**
     * <b>Verilen model listesindeki tüm modelleri ve ilişkili arabaları siler.</b>
     * Bu metod, her bir model için ModelService.delete metodunu çağırır.
     * Bu işlem, ilgili modellerle ilişkili tüm arabaların silinmesini de sağlar.
     *
     * @param models Silinecek modellerin listesi. Bu liste null ise, metod hiçbir işlem yapmaz.
     */
    private void deleteModelsAndAssociatedCars(List<Model> models) {
        if (models != null) models.forEach(model -> modelService.delete(model.getId()));
    }

    private void produceBrandUpdatedEvent(BrandResponse response) {
        var event = new BrandUpdatedEvent(response.id(), response.name());
        producer.sendMessage(event, "brand.updated");
    }

    private void produceBrandDeletedEvent(Long brandId) {
        var event = new BrandDeletedEvent(brandId);
        producer.sendMessage(event, "brand.deleted");
    }
}
