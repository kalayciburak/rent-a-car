package com.kalayciburak.inventoryservice.service.helper;

import com.kalayciburak.inventoryservice.advice.exception.ModelNotFoundException;
import com.kalayciburak.inventoryservice.model.entity.Model;
import com.kalayciburak.inventoryservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Model.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ModelHelperService {
    private final ModelRepository repository;

    /**
     * <b>Verilen id'ye ait modelin olup olmadığını kontrol eder.</b>
     *
     * @param id Model id değeri.
     * @throws ModelNotFoundException Model bulunamazsa fırlatılır.
     */
    public Model findEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ModelNotFoundException(NOT_FOUND));
    }
}
