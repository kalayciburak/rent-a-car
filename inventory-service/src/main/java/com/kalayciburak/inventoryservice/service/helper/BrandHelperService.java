package com.kalayciburak.inventoryservice.service.helper;

import com.kalayciburak.inventoryservice.model.entity.Brand;
import com.kalayciburak.inventoryservice.repository.BrandRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Brand.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BrandHelperService {
    private final BrandRepository repository;

    public Brand findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
