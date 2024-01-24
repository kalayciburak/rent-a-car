package com.kalayciburak.inventoryservice.service.impl;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.BrandResponse;
import com.kalayciburak.inventoryservice.repository.BrandRepository;
import com.kalayciburak.inventoryservice.service.BrandService;
import com.kalayciburak.inventoryservice.util.mapper.BrandMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandMapper mapper;
    private final BrandRepository repository;

    @Override
    public ResponseItem<BrandResponse> findById(Long id) {
        var brand = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marka bulunamadı."));
        var data = mapper.toDto(brand);

        return createSuccessResponse(data, "Marka listelendi.");
    }

    @Override
    public ResponseItem<List<BrandResponse>> findAll() {
        var brands = repository.findAll();
        var data = brands.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, "Markalar listelendi.");
    }

}
