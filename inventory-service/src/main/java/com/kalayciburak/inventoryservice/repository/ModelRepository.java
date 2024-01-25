package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonpackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entity.Model;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends BaseRepository<Model, Long> {
}
