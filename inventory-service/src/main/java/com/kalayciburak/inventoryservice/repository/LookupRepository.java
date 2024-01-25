package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonpackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entity.Lookup;
import com.kalayciburak.inventoryservice.repository.custom.CustomLookupRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LookupRepository extends BaseRepository<Lookup, Long>, CustomLookupRepository {
    Optional<Lookup> findByKey(String key);

    Optional<Lookup> findByLabel(String label);
}
