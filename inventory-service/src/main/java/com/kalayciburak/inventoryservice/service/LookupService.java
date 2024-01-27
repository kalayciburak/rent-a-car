package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.commonpackage.util.constant.Messages;
import com.kalayciburak.inventoryservice.model.dto.response.basic.LookupResponse;
import com.kalayciburak.inventoryservice.model.entity.Lookup;
import com.kalayciburak.inventoryservice.repository.LookupRepository;
import com.kalayciburak.inventoryservice.util.mapper.LookupMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Entities.FOUND;
import static com.kalayciburak.commonpackage.util.constant.Messages.Entity.NOT_FOUND;
import static com.kalayciburak.inventoryservice.model.enums.LookupKey.*;

@Service
@RequiredArgsConstructor
public class LookupService {
    private final LookupMapper mapper;
    private final LookupRepository repository;

    public ResponseItem<LookupResponse> findById(Long id) {
        var lookup = findByIdOrThrow(id);
        var data = mapper.toDto(lookup);

        return createSuccessResponse(data, Messages.Entity.FOUND);
    }

    public ResponseItem<LookupResponse> findByKey(String key) {
        var lookup = repository.findByKey(key).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        var data = mapper.toDto(lookup);

        return createSuccessResponse(data, Messages.Entity.FOUND);
    }

    public ResponseItem<LookupResponse> findByLabel(String label) {
        var lookup = repository.findByLabel(label).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
        var data = mapper.toDto(lookup);

        return createSuccessResponse(data, Messages.Entity.FOUND);
    }

    public ResponseItem<List<LookupResponse>> findChildrenByParentId(Long parentId) {
        var lookups = repository.findChildrenByParentId(parentId);
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<LookupResponse>> findChildrenByParentKey(String parentKey) {
        var lookups = repository.findChildrenByParentKey(parentKey);
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<LookupResponse>> findChildrenByParentLabel(String parentLabel) {
        var lookups = repository.findChildrenByParentLabel(parentLabel);
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<LookupResponse>> findAll() {
        var lookups = repository.findAll();
        if (lookups.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = lookups.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    public Lookup findEntityById(Long id) {
        return findByIdOrThrow(id);
    }

    // * Dinamik Lookup Nesneleri
    // ? Yakıt Türleri
    public Long getGasolineId() {
        return findByKey(GASOLINE.getKey()).getData().id();
    }

    public Long getDieselId() {
        return findByKey(DIESEL.getKey()).getData().id();
    }

    public Long getElectricId() {
        return findByLabel(ELECTRIC.getKey()).getData().id();
    }

    public Long getHybridId() {
        return findByLabel(HYBRID.getKey()).getData().id();
    }

    public Long getLpgId() {
        return findByLabel(LPG.getKey()).getData().id();
    }

    // ? Vites Türleri
    public Long getAutomaticId() {
        return findByKey(AUTOMATIC.getKey()).getData().id();
    }

    public Long getManualId() {
        return findByKey(MANUAL.getKey()).getData().id();
    }

    public Long getSemiAutomaticId() {
        return findByKey(SEMI_AUTOMATIC.getKey()).getData().id();
    }

    // ? Renk Türleri
    public Long getWhiteId() {
        return findByKey(WHITE.getKey()).getData().id();
    }

    public Long getBlackId() {
        return findByKey(BLACK.getKey()).getData().id();
    }

    public Long getRedId() {
        return findByKey(RED.getKey()).getData().id();
    }

    public Long getBlueId() {
        return findByKey(BLUE.getKey()).getData().id();
    }

    public Long getGreenId() {
        return findByKey(GREEN.getKey()).getData().id();
    }

    public Long getYellowId() {
        return findByKey(YELLOW.getKey()).getData().id();
    }

    public Long getPinkId() {
        return findByKey(PINK.getKey()).getData().id();
    }

    public Long getOrangeId() {
        return findByKey(ORANGE.getKey()).getData().id();
    }

    public Long getPurpleId() {
        return findByKey(PURPLE.getKey()).getData().id();
    }

    public Long getGrayId() {
        return findByKey(GRAY.getKey()).getData().id();
    }

    // ? Araç Durumları
    public Long getAvailableId() {
        return findByKey(AVAILABLE.getKey()).getData().id();
    }

    public Long getRentedId() {
        return findByKey(RENTED.getKey()).getData().id();
    }

    public Long getUnderMaintenanceId() {
        return findByKey(UNDER_MAINTENANCE.getKey()).getData().id();
    }

    public Long getNotAvailableId() {
        return findByKey(NOT_AVAILABLE.getKey()).getData().id();
    }

    private Lookup findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
