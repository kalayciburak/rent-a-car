package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonpackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entity.Location;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends BaseRepository<Location, Long> {
}
